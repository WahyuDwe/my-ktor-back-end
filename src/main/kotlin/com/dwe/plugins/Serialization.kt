package com.dwe.plugins

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson { }
    }
    routing {
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
        get("dwe/api") {
            val firstName = call.request.queryParameters["firstName"]
            val lastName = call.request.queryParameters["lastName"]

            if (!firstName.isNullOrBlank() && !lastName.isNullOrBlank()) {
                call.respond(
                    mapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "message" to "Hello, $firstName $lastName!"
                    )
                )
            } else {
                call.respond(
                    mapOf(
                        "code" to 400,
                        "error" to "Invalid request",
                        "message" to "Please provide both 'firstName' and 'lastName' query parameters"
                    )
                )
            }
        }
    }
}
