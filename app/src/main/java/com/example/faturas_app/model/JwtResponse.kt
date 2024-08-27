package com.example.faturas_app.model

data class JwtResponse(
    var refreshToken: String,
    var id: Long,
    var username: String,
    var email: String,
    var roles: Array<String>,
    var accessToken: String,
    var tokenType: String
) {
    // Métodos adicionais podem ser adicionados conforme necessário
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JwtResponse

        if (refreshToken != other.refreshToken) return false
        if (id != other.id) return false
        if (username != other.username) return false
        if (email != other.email) return false
        if (!roles.contentEquals(other.roles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = refreshToken.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + roles.contentHashCode()
        return result
    }
}
