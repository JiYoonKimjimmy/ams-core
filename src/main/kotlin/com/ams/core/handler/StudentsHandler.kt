package com.ams.core.handler

import com.ams.core.infra.config.ValidatorConfig
import com.ams.core.model.PageableModel
import com.ams.core.model.StudentModel
import com.ams.core.repository.StudentsRepository
import com.ams.core.service.ParentsService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class StudentsHandler(
    val studentsRepository: StudentsRepository,
    val parentsService: ParentsService,
    val validatorConfig: ValidatorConfig
) {

    fun findOne(request: ServerRequest): Mono<ServerResponse> =
        request
            .pathVariable("id")
            .toLong()
            .let { studentsRepository.findById(it) }
            .zipWhen { parentsService.findAllByStudent(studentId = it.id!!).collectList() }
            .flatMap { ok().body(fromValue(StudentModel.of(tuple = it))) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        PageableModel
            .toPageRequest(request = request)
            .let { studentsRepository.findAllBy(pageable = it).collectList() }
            .zipWith(studentsRepository.count())
            .flatMap { ok().body(fromValue(StudentModel.of(request = request, tuple = it))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(StudentModel::class.java)
            .map { it.toEntity()}
            .flatMap { studentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }

    fun saveWithValidation(request: ServerRequest): Mono<ServerResponse> =
        validatorConfig
            .requireValidation(request = request, bodyClass = StudentModel::class.java)
            .map { it.toEntity()}
            .flatMap { studentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(StudentModel::class.java)
            .zipWhen { studentsRepository.findById(it.id) }
            .flatMap { it.t2.update(it.t1) }
            .flatMap { studentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        request
            .queryParam("id")
            .orElseThrow()
            .toLong()
            .let { studentsRepository.deleteById(it) }
            .flatMap { ok().build() }

}
