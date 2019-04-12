package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import no.nav.syfo.restmock.model.OppgaveResponse
import no.nav.syfo.restmock.model.OpprettOppgave
import java.util.*

fun Routing.registerOppgaveApi() {
    post("/api/v1/oppgaver") {
        val oppgave = call.receive<OpprettOppgave>()

        call.respond(OppgaveResponse(
            id = Random().nextInt(1000000))
        )
    }
}
