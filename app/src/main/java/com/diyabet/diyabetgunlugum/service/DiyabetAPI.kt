package com.diyabet.diyabetgunlugum.service

import com.diyabet.diyabetgunlugum.model.request.CreateTokenRequestModel
import com.diyabet.diyabetgunlugum.model.request.LoginRequestModel
import com.diyabet.diyabetgunlugum.model.response.CreateTokenResponseModel
import com.diyabet.diyabetgunlugum.model.response.LoginSuccessResponseModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DiyabetAPI {

    companion object {
        private const val CREATE_TOKEN = "token/CreateToken"
        private const val LOGIN = "users/login"
    }

    @Headers("Content-Type: application/json")
    @POST(CREATE_TOKEN)
    suspend fun createToken(@Body createTokenModel: CreateTokenRequestModel) : CreateTokenResponseModel

    @Headers("Content-Type: application/json")
    @POST(LOGIN)
    suspend fun login(@Body loginRequestModel: LoginRequestModel) : LoginSuccessResponseModel

}

// 1- Request ile response oluştururum.
// 2- API ye istek atılacak method eklenmeli -> MethodType, extURL, requestModel, responseModel
//
