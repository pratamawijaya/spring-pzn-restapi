package com.pratama.springbootpznrestapi.config

import com.pratama.springbootpznrestapi.entity.ApiKey
import com.pratama.springbootpznrestapi.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

/*
Akan dijalankan pertama kali saat springboot running
 */

@Component
class ApiKeyConfiguration(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {

    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if (!apiKeyRepository.existsById(apiKey)) {
            val key = ApiKey(id = apiKey)
            apiKeyRepository.save(key)
        }
    }
}