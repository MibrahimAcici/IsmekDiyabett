package com.diyabet.diyabetgunlugum.Interceptor

import com.diyabet.diyabetgunlugum.util.Constant
import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {

    var token : String = Hawk.get(Constant.TOKEN, "")

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if(request.header("No-Authentication") == null){
            if(token.isNotEmpty())
            {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }

        return chain.proceed(request)
    }
}