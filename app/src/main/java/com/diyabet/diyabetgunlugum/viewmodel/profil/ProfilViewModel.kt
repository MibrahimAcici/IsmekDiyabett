package com.diyabet.diyabetgunlugum.viewmodel.profil

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyabet.diyabetgunlugum.model.request.CreateTokenRequestModel
import com.diyabet.diyabetgunlugum.model.response.CreateTokenResponseModel
import com.diyabet.diyabetgunlugum.repository.DiyabetRepository
import com.diyabet.diyabetgunlugum.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(private val repository: DiyabetRepository) : ViewModel(){

    val tokenResponse = MutableLiveData<CreateTokenResponseModel?>()
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun createToken(createTokenRequestModel: CreateTokenRequestModel) {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.createToken(createTokenRequestModel)
            when(result){
                is Resource.Success -> {
                    tokenResponse.value = result.data
                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                else ->{
                    errorMessage.value = result.message!!
                }
            }
        }
    }
}