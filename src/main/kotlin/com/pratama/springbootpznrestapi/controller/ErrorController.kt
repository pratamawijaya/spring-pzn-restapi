package com.pratama.springbootpznrestapi.controller

import com.pratama.springbootpznrestapi.model.WebResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    val logger: Logger = LoggerFactory.getLogger(ErrorController::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationHandler(ex: MethodArgumentNotValidException): WebResponse<String> {
        logger.info(ex.toString())
        val data = ex.bindingResult.fieldErrors
        logger.info("data binding result : ${data.size} : $data")
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = "${data[0].field} ${data[0].defaultMessage}"
        )
    }
}