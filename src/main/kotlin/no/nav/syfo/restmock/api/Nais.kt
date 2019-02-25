package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.registerNaisSelftests() {
    get("/is_alive") {
        call.respondText("I', alive!")
    }

    get("/is_ready") {
        call.respondText("I'm ready!")
    }
}
