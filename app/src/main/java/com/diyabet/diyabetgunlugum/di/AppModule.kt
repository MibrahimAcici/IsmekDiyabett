package com.diyabet.diyabetgunlugum.di

import com.diyabet.diyabetgunlugum.Interceptor.ServiceInterceptor
import com.diyabet.diyabetgunlugum.repository.DiyabetRepository
import com.diyabet.diyabetgunlugum.service.DiyabetAPI
import com.diyabet.diyabetgunlugum.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun diyabetRepository(apiDiyabet: DiyabetAPI) = DiyabetRepository(apiDiyabet)

    @Singleton
    @Provides
    fun provideDiyabetApi() : DiyabetAPI {
        val client = OkHttpClient.Builder()
            .addInterceptor(ServiceInterceptor())
            .build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
            .create(DiyabetAPI::class.java)
    }
}