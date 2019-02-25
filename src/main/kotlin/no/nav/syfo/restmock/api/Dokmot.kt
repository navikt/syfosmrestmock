package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import net.logstash.logback.argument.StructuredArguments.keyValue
import no.nav.syfo.restmock.StepType
import no.nav.syfo.restmock.addStepFor
import no.nav.syfo.restmock.model.MottaInngaaendeForsendelse
import no.nav.syfo.restmock.model.MottaInngaandeForsendelseResultat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

val log: Logger = LoggerFactory.getLogger("dokmot-mock")

fun Routing.registerDokmotApi() {
    post("/rest/mottaInngaaendeForsendelse") {
        val journalpost = call.receive<MottaInngaaendeForsendelse>()

        val smId = journalpost.forsendelseInformasjon.kanalReferanseId
        log.info("Received a request for persisting incoming document with {}", keyValue("smId", smId))
        addStepFor(smId, StepType.JOURNAL_INCOMING)

        call.respond(MottaInngaandeForsendelseResultat(
            UUID.randomUUID().toString(), "ENDELIG", listOf()
        ))
    }
}
