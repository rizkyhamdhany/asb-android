package com.asb.core.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenData(
    @SerializedName("refresh_token")
    var refresh_token: String
)