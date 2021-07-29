package com.davidargote.api_movies.repository.service

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*

object ClientService {

    private var INSTANCE: HttpClient? = null

    fun getClientInstance() : HttpClient {
        if (INSTANCE == null){
            INSTANCE = HttpClient(CIO) {
                install(DefaultRequest) {
                    headers.append("Content-Type", "application/json")
                }
                install(JsonFeature) {
                    serializer = GsonSerializer()
                }
            }
        }
        return INSTANCE as HttpClient
    }

}
