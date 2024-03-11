package ru.playzone

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import ru.playzone.features.games.configureGamesRouting
import ru.playzone.features.login.configureLoginRouting
import ru.playzone.features.register.configureRegisterRouting
import ru.playzone.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/arslan.a", driver = "org.postgresql.Driver", "arslan.a", password = "123456")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureRegisterRouting()
    configureLoginRouting()
    configureGamesRouting()
}
