package com.diyabet.diyabetgunlugum.viewmodel.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyabet.diyabetgunlugum.model.request.LoginRequestModel
import com.diyabet.diyabetgunlugum.model.response.LoginSuccessResponseModel
import com.diyabet.diyabetgunlugum.repository.DiyabetRepository
import com.diyabet.diyabetgunlugum.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: DiyabetRepository) : ViewModel() {

    val loginResponse = MutableLiveData<LoginSuccessResponseModel?>()
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun login(loginRequestModel: LoginRequestModel) {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.loginProcess(loginRequestModel)
            when(result){
                is Resource.Success -> {
                    loginResponse.value = result.data
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