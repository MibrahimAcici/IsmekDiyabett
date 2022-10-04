package com.diyabet.diyabetgunlugum.model.response


import com.google.gson.annotations.SerializedName

data class CreateTokenResponseModel(
    @SerializedName("token")
    var token: String? = null
)