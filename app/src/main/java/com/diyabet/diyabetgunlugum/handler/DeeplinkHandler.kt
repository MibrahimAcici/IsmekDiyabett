package com.diyabet.diyabetgunlugum.handler

import android.view.View
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun goToDeepLink(url: String, view: View) {
    url.let {
        CoroutineScope(Dispatchers.Main).launch {
            Navigation.findNavController(view).navigate(R.id.profileFragment)
        }
    }
}