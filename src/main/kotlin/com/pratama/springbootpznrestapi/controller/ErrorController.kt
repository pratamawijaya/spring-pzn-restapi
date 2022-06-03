package com.pratama.springbootpznrestapi.controller

import com.pratama.springbootpznrestapi.model.WebResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationHandler(ex: MethodArgumentNotValidException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = ex.localizedMessage
        )
    }
}