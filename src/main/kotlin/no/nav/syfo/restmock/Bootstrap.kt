package no.nav.syfo.restmock

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import no.nav.syfo.restmock.api.registerDokmotApi
import no.nav.syfo.restmock.api.registerNaisSelftests
import no.nav.syfo.restmock.api.registerSakApi
import java.util.concurrent.TimeUnit

fun main() {
    val httpServer = embeddedServer(Netty) {
        install(ContentNegotiation) {
            jackson {
                registerKotlinModule()
                registerModule(JavaTimeModule())
            }
        }
        routing {
            registerNaisSelftests()
            registerSakApi()
            registerDokmotApi()
        }
    }.start(wait = false)

    Runtime.getRuntime().addShutdownHook(Thread {
        httpServer.stop(10, 10, TimeUnit.SECONDS)
    })
}
