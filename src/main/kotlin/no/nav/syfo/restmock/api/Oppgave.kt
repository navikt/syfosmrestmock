package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import no.nav.syfo.restmock.model.OpprettOppgave
import no.nav.syfo.restmock.model.OpprettOppgaveResponse
import java.time.LocalDateTime
import java.util.*

fun Routing.registerOppgaveApi() {
    post("/v1/oppgaver") {
        val oppgave = call.receive<OpprettOppgave>()

        call.respond(OpprettOppgaveResponse(
            tildeltEnhetsnr = oppgave.tildeltEnhetsnr,
            opprettetAvEnhetsnr = oppgave.opprettetAvEnhetsnr,
            aktoerId = oppgave.aktoerId,
            journalpostId = oppgave.journalpostId,
            journalpostkilde = oppgave.journalpostkilde,
            behandlesAvApplikasjon = oppgave.behandlesAvApplikasjon,
            saksreferanse = oppgave.saksreferanse,
            orgnr = oppgave.orgnr,
            bnr = oppgave.bnr,
            samhandlernr = oppgave.samhandlernr,
            tilordnetRessurs = oppgave.tilordnetRessurs,
            beskrivelse = oppgave.beskrivelse,
            temagruppe = oppgave.temagruppe,
            tema = oppgave.tema,
            behandlingstema = oppgave.behandlingstema,
            oppgavetype = oppgave.oppgavetype,
            behandlingstype = oppgave.behandlingstype,
            mappeId = oppgave.mappeId,
            aktivDato = oppgave.aktivDato,
            fristFerdigstillelse = oppgave.fristFerdigstillelse,
            prioritet = oppgave.prioritet,
            metadata = oppgave.metadata,

            id = UUID.randomUUID().toString(),
            opprettetTidspunkt = LocalDateTime.now(),
            opprettetAv = "srvsyfosmoppgave",
            endretAv = "srvsyfosmoppgave",
            ferdigstiltTidspunkt = null,
            endretTidspunkt = LocalDateTime.now(),
            status = "UNDER_BEHANDLING"
        ))
    }
}
