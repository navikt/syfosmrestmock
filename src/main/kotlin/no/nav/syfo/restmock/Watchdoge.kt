package no.nav.syfo.restmock

import net.logstash.logback.argument.StructuredArguments.keyValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.write

val state = mutableMapOf<String, Timed<MutableList<Timed<StepType>>>>()
val stateLock = ReentrantReadWriteLock()

val log: Logger = LoggerFactory.getLogger("restmock")

fun runWatchdoge() {
    state.filter { (_, timed) ->
        (timed.timestamp + 1000 * 60 * 5) > System.currentTimeMillis()
    }.forEach { (id, steps) ->
        state.remove(id)

        val missingSteps = StepType.values().filter { stepType ->
            !steps.data.any { it.data == stepType }
        }

        if (missingSteps.any { it.required }) {
            log.error("Watchdoge cleared a entry with id {} where there was missing required step(s) {}",
                keyValue("id", id), keyValue("missingSteps", missingSteps))
        } else if (!missingSteps.isEmpty()) {
            log.warn("Watchdoge cleared a entry with id {} where there was missing non-required step(s) {}",
                keyValue("id", id), keyValue("missingSteps", missingSteps))
        }
    }
}

fun addStepFor(smId: String, stepType: StepType) {
    val step = Timed(timestamp = System.currentTimeMillis(), data = stepType)
    stateLock.write {
        val currentState = state[smId]
        if (currentState != null) {
            if (StepType.values().all { stepType -> (currentState.data + listOf(stepType)).any { it == stepType } }) {
                log.info("Successfully finished all steps for {}", keyValue("id", smId))
            }
        }

        when {
            currentState == null -> state[smId] = Timed(timestamp = System.currentTimeMillis(), data = mutableListOf(step))
            currentState.data.any { it.data == stepType } -> log.warn("Got a duplicate step of type {}", keyValue("step", step))
            else -> currentState.data.add(step)
        }

        runWatchdoge()
    }
}

data class Timed<T>(
    val timestamp: Long,
    val data: T
)

enum class StepType(val required: Boolean = false) {
    JOURNAL_INCOMING(required = true)
}
