package com.ams.core.handler

import com.ams.core.model.PageableModel
import com.ams.core.model.StudentModel
import com.ams.core.repository.ParentsRepository
import com.ams.core.repository.StudentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class StudentsHandler(
    val studentsRepository: StudentsRepository,
    val parentsRepository: ParentsRepository
) {

    fun getOne(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .findById(request.pathVariable("id").toLong())
            .zipWith(parentsRepository.findAllByStudentId(request.pathVariable("id").toLong()).collectList())
            .flatMap { ok().body(fromValue(StudentModel.of(it.t1, it.t2))) }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .findAllBy(PageableModel.toPageRequest(request))
            .map(StudentModel::of)
            .collectList()
            .zipWith(studentsRepository.count())
            .flatMap { ok().body(fromValue(StudentModel.of(request, it.t1, it.t2))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .saveAll(request.bodyToMono(StudentModel::class.java).map { it.toEntity() })
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(StudentModel::class.java)
            .flatMap { studentsRepository.findById(it.id).flatMap { old -> old.update(it) } }
            .flatMap { studentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .deleteById(request.queryParam("id").orElseThrow().toLong())
            .flatMap { ok().build() }

}