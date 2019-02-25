package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import no.nav.syfo.restmock.StepType
import no.nav.syfo.restmock.addStepFor
import no.nav.syfo.restmock.model.MottaInngaandeForsendelseResultat
import java.util.*

fun Routing.registerDokmotApi() {
    post("/rest/mottaInngaaendeForsendelse") {
        val smId = "TODO" // forsendelseInformasjon -> kanalReferanseId
        addStepFor(smId, StepType.JOURNAL_INCOMING)
        call.respond(MottaInngaandeForsendelseResultat(
            UUID.randomUUID().toString(), "ENDELIG", listOf()
        ))
    }
}
