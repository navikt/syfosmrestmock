package no.nav.syfo.restmock.model

import java.time.ZonedDateTime

data class OpprettSakResponse(
    val id: Long,
    val tema: String,
    val aktoerId: String,
    val orgnr: String?,
    val fagsakNr: String,
    val applikasjon: String,
    val opprettetAv: String,
    val opprettetTidspunkt: ZonedDateTime
)
