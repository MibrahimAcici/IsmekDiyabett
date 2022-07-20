package com.diyabet.diyabetgunlugum.repository

import com.diyabet.diyabetgunlugum.service.DiyabetAPI
import com.diyabet.diyabetgunlugum.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DiyabetRepository  @Inject constructor(private val api: DiyabetAPI){

}