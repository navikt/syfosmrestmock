package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.registerSakApi() {
    post("/api/v1/sak") {
        call.respond(HttpStatusCode.OK, "Everything is OK")
    }
}
