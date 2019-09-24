package com.cnevinchen


import com.cnevinchen.hello.hello
import com.cnevinchen.news.news
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

//fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)
fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

val clientSettings = OAuthServerSettings.OAuth2ServerSettings(
    name = "fxa",
    authorizeUrl = "https://oauth-stable.dev.lcip.org/v1/authorization", // OAuth authorization endpoint
    accessTokenUrl = "https://oauth-stable.dev.lcip.org/v1/token", // OAuth token endpoint
    clientId = "1eb7617275323e17",
    clientSecret = "435e6af7724932e42a3c9785cb88e853401bdef42abb10efe212b079d5120e97",
    accessTokenRequiresBasicAuth = false, // basic auth compile is not "OAuth style" so falling back to post body
    requestMethod = HttpMethod.Post, // must POST to token endpoint
    defaultScopes = listOf("profile") // what scopes to request
)

fun Application.module() {
    install(io.ktor.features.ContentNegotiation) {
        jackson {
        }
    }

    install(Authentication) {
        oauth("fxa") {
            client = HttpClient(Apache)
            providerLookup = { clientSettings }
            urlProvider = { "https://jcconf2019.appspot.com/login" }
        }
    }

    routing {
        hello()

        authenticate("fxa") {
            get("/login") {
                val principal = call.authentication.principal<OAuthAccessTokenResponse.OAuth2>()

                call.respondText("Access Token = ${principal?.accessToken}")
            }
        }

        news()
    }
}


class Result(
    val transactionId: Int,
    val message: String
)