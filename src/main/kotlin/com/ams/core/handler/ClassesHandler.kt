package com.ams.core.handler

import com.ams.core.common.model.PageableModel
import com.ams.core.model.ClassSchedulesModel
import com.ams.core.model.ClassesModel
import com.ams.core.repository.ClassesRepository
import com.ams.core.service.ClassSchedulesService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class ClassesHandler(
    val classesRepository: ClassesRepository,
    val classSchedulesService: ClassSchedulesService,
    val classSchedulesHandler: ClassSchedulesHandler
) {

    fun findOne(request: ServerRequest): Mono<ServerResponse> =
        request
            .pathVariable("id")
            .toLong()
            .let { classesRepository.findById(it) }
            .zipWhen { classSchedulesService.findAllByClassId(classId = it.id!!).collectList() }
            .flatMap { ok().body(fromValue(ClassesModel.of(tuple = it))) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        PageableModel
            .toPageRequest(request = request)
            .let { classesRepository.findAllBy(pageable = it) }
            .collectList()
            .zipWith(classesRepository.count())
            .flatMap { ok().body(fromValue(ClassesModel.of(request = request, tuple = it))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ClassesModel::class.java)
            .map { it.toEntity() }
            .flatMap { classesRepository.save(it) }
            .flatMap { ok().body(fromValue(ClassesModel.of(entity = it))) }

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ClassesModel::class.java)
            .zipWhen { classesRepository.findById(it.id) }
            .flatMap { it.t2.update(request = it.t1) }
            .flatMap { classesRepository.save(it) }
            .flatMap { ok().body(fromValue(ClassesModel.of(entity = it))) }

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        request
            .queryParam("id")
            .orElseThrow()
            .toLong()
            .let { classesRepository .deleteById(it) }
            .flatMap { ok().build() }

    fun saveSchedules(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ClassSchedulesModel::class.java)
            .flatMap { classesRepository.findById(it.id) }
            .flatMap { classSchedulesHandler.saveAll(classes = it).collectList() }
            .flatMap { ok().body(fromValue(it)) }

    fun updateSchedules(request: ServerRequest): Mono<ServerResponse> =
        classSchedulesHandler
            .update(request = request)
            .flatMap { ok().body(fromValue(it)) }

}