package ru.playzone.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.playzone.cache.InMemoryCache
import ru.playzone.cache.TokenCache
import ru.playzone.database.tokens.Tokens
import ru.playzone.database.tokens.TokensDTO
import ru.playzone.database.users.UserModel
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive(LoginRecieveRemote::class)
        val userModel = UserModel.fetchUser(receive.login)

        if (userModel == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userModel.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokensDTO(
                        rowId = UUID.randomUUID().toString(),
                        login = userModel.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid Password")
            }
        }
    }
}