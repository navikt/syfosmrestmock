package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.post
import no.nav.syfo.restmock.StepType
import no.nav.syfo.restmock.addStepFor

fun Routing.registerMockStatus() {
    post("/api/v1/status") {
        val step = call.receive<Status>()

        addStepFor(step.smId, step.step)
        call.respondText("OK", status = HttpStatusCode.OK)
    }
}

data class Status(
    val smId: String,
    val step: StepType
)
