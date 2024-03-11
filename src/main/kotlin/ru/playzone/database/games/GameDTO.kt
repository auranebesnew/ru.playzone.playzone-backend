package ru.playzone.database.games

import ru.playzone.features.games.models.CreateGameRequest
import ru.playzone.features.games.models.CreateGameResponse
import java.util.*

data class GameDTO (
        val gameId: String,
        val title: String,
        val description: String,
        val version: String,
        val size: Double
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
        GameDTO(
                gameId = UUID.randomUUID().toString(),
                title = title,
                description = description,
                version = version,
                size = size
        )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
        CreateGameResponse(
                gameID = gameId,
                title = title,
                description = description,
                version = version,
                size = size
        )