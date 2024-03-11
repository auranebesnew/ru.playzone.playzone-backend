package ru.playzone.database.tokens
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object Tokens: Table("tokens") {
    private val id = Tokens.varchar("id", 100)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 100)

    fun insert(tokensDTO: TokensDTO) {
        transaction {
            Tokens.insert {
                it[id] = tokensDTO.rowId
                it[login] = tokensDTO.login
                it[token] = tokensDTO.token
            }
        }
    }

    fun fetchTokens(): List<TokensDTO> {
        return try {
            transaction {
                Tokens.selectAll().toList()
                    .map {
                        TokensDTO(
                            rowId = it[Tokens.id],
                            token = it[Tokens.token],
                            login = it[Tokens.login]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}