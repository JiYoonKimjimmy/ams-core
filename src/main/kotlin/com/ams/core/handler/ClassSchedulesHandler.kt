package com.ams.core.handler

import com.ams.core.common.base.BaseHandler
import com.ams.core.common.base.BaseResponse
import com.ams.core.common.enumerate.DayOfWeekEnum
import com.ams.core.common.getDisplayDayOfWeek
import com.ams.core.common.toPageRequest
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import com.ams.core.model.ClassSchedulesModel
import com.ams.core.model.DayOfWeekModel
import com.ams.core.repository.ClassSchedulesRepository
import me.moallemi.tools.daterange.localdate.rangeTo
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ClassSchedulesHandler(
    val classSchedulesRepository: ClassSchedulesRepository
) : BaseHandler {

    override fun findOne(request: ServerRequest): Mono<ServerResponse> =
        request
            .pathVariable("id")
            .toLong()
            .let { classSchedulesRepository.findById(it) }
            .flatMap { BaseResponse.ok(body = ClassSchedulesModel.of(entity = it)) }

    override fun findAll(request: ServerRequest): Mono<ServerResponse> =
        request
            .toPageRequest()
            .let { classSchedulesRepository.findAllBy(pageable = it).collectList() }
            .zipWith(classSchedulesRepository.count())
            .flatMap { BaseResponse.ok(body = ClassSchedulesModel.of(request = request, tuple = it)) }

    override fun save(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ClassSchedulesModel::class.java)
            .map { it.toEntity() }
            .flatMap { classSchedulesRepository.save(it) }
            .flatMap { BaseResponse.ok(body = ClassSchedulesModel.of(entity = it)) }

    override fun update(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ClassSchedulesModel::class.java)
            .zipWhen { classSchedulesRepository.findById(it.id) }
            .flatMap { it.t2.update(model = it.t1) }
            .flatMap { classSchedulesRepository.save(it) }
            .flatMap { BaseResponse.ok(body = ClassSchedulesModel.of(entity = it)) }

    override fun delete(request: ServerRequest): Mono<ServerResponse> =
        request
            .queryParam("id")
            .orElseThrow()
            .toLong()
            .let { classSchedulesRepository.deleteById(it) }
            .flatMap { BaseResponse.ok() }

    fun saveAll(classes: Classes): Flux<ClassSchedules> =
        classes
            .getDayOfWeek()
            .let { dayOfWeeks ->
                (classes.startDate..classes.endDate)
                    .filter { dayOfWeeks.map(DayOfWeekModel::day).contains(DayOfWeekEnum.valueOf(it.getDisplayDayOfWeek())) }
                    .map {
                        dayOfWeeks
                            .find { c -> c.day.name == it.getDisplayDayOfWeek() }!!
                            .let { c -> it.atTime(c.hour.toInt(), c.minute.toInt())
                            }
                    }
                    .map { ClassSchedules.toReady(classes, it) }
            }
            .let { classSchedulesRepository.saveAll(it) }

}
