package no.nav.syfo.restmock.model

data class MottaInngaandeForsendelseResultat(
    val journalpostId: String,
    val journalTilstand: String,
    val dokumentIdList: List<String>
)
