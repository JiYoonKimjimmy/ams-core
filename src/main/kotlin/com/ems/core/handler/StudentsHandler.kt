package com.ems.core.handler

import com.ems.core.entity.Student
import com.ems.core.model.GetStudentsResponse
import com.ems.core.model.PageableModel
import com.ems.core.repository.StudentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class StudentsHandler(
    val studentsRepository: StudentsRepository
) {

    fun getOne(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .findById(request.pathVariable("id").toLong())
            .flatMap { ok().body(fromValue(it)) }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .findAllBy(PageableModel.toPageRequest(request))
            .collectList()
            .zipWith(studentsRepository.count())
            .flatMap { ok().body(fromValue(GetStudentsResponse.of(request, it.t1, it.t2))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .saveAll(request.bodyToMono(Student::class.java))
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun update(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .saveAll(request.bodyToMono(Student::class.java).flatMap {
                studentsRepository.findById(it.id!!)
                    .flatMap { old ->
                        old.id = it.id
                        old.name = it.name
                        old.mobileNumber = it.mobileNumber
                        old.dateOfBirth = it.dateOfBirth
                        old.gender = it.gender
                        old.school = it.school
                        old.grade = it.grade
                        old.status = it.status
                        Mono.just(old)
                    }
            })
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .deleteById(request.queryParam("id").orElseThrow().toLong())
            .flatMap { ok().build() }

}