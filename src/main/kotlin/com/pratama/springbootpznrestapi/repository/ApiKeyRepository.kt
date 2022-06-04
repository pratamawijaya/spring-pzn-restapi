package com.pratama.springbootpznrestapi.repository

import com.pratama.springbootpznrestapi.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String> {
}