package com.pratama.springbootpznrestapi.auth

import com.pratama.springbootpznrestapi.error.UnAuthorizedException
import com.pratama.springbootpznrestapi.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository) : WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key") ?: throw UnAuthorizedException()

        if (!apiKeyRepository.existsById(apiKey)) {
            throw UnAuthorizedException()
        }

    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
    }
}