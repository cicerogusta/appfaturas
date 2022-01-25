package com.example.faturas_app.model.apiModel

import java.time.Instant

class RefreshToken(
    val id: Long? = null,
    val accessToken: String? = null,
    val expiryDate: Instant? = null
) {

}
