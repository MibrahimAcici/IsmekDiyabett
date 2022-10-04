package com.diyabet.diyabetgunlugum.repository

import com.diyabet.diyabetgunlugum.model.request.CreateTokenRequestModel
import com.diyabet.diyabetgunlugum.model.request.LoginRequestModel
import com.diyabet.diyabetgunlugum.model.response.CreateTokenResponseModel
import com.diyabet.diyabetgunlugum.model.response.LoginSuccessResponseModel
import com.diyabet.diyabetgunlugum.service.DiyabetAPI
import com.diyabet.diyabetgunlugum.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DiyabetRepository  @Inject constructor(private val api: DiyabetAPI){

    suspend fun createToken(createTokenRequestModel: CreateTokenRequestModel) : Resource<CreateTokenResponseModel> {
        val response = try {
            api.createToken(createTokenRequestModel)
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun loginProcess(loginRequestModel: LoginRequestModel) : Resource<LoginSuccessResponseModel> {
        val response = try {
            api.login(loginRequestModel)
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

}