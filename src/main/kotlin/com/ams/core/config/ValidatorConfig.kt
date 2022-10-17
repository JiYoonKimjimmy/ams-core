package com.ams.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono
import javax.validation.Validator

@Configuration
class ValidatorConfig(
    val validator: Validator
) {

    fun <BODY> requireValidation(request: ServerRequest, bodyClass: Class<BODY>): Mono<BODY> =
        request
            .bodyToMono(bodyClass)
            .flatMap {
                val validation = validator.validate(it)
                if (validation.isEmpty()) {
                    Mono.just(it!!)
                } else {
                    val result = validation.first()
                    val message = "${result.message}. [${result.propertyPath}]"
                    Mono.error(IllegalArgumentException(message))
                }
            }

}