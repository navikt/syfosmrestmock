package no.nav.syfo.restmock.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import no.nav.syfo.restmock.model.OpprettSakResponse
import java.time.ZonedDateTime
import kotlin.random.Random

fun Routing.registerSakApi() {
    post("/api/v1/saker") {
        call.respond(HttpStatusCode.OK, OpprettSakResponse(
            id = Random.nextLong(),
            tema = "SYM",
            aktoerId = "TODO",
            orgnr = "TODO",
            fagsakNr = "TODO",
            applikasjon = "syfosmmottak",
            opprettetAv = "srvsyfosmsak",
            opprettetTidspunkt = ZonedDateTime.now()
        ))
    }
}
