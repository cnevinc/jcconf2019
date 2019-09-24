package com.cnevinchen.hello

import com.cnevinchen.Result
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.hello() {

    get("/hello") {
        val name = call.request.queryParameters["name"]
        call.respond(Result(1, "Hello $name"))
    }
}