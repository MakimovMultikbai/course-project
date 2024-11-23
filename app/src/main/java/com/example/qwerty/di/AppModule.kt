package com.example.qwerty.di

import android.app.Application
import android.content.Context
import com.example.qwerty.data.data_source.TokensStorage
import com.example.qwerty.data.remote.AuthApi
import com.example.qwerty.data.repository.AuthRepositoryImpl
import com.example.qwerty.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun GetAuthApi(): AuthApi{
          val retrofit = Retrofit.Builder()
            //.baseUrl("http://192.168.43.15:8000")             /*Радик*/
            .baseUrl("http://192.168.0.163:8000")               /*Общежитие*/

            .addConverterFactory(GsonConverterFactory.create()).build()

        return  retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun GetTokensStorage(context: Application): TokensStorage{
        return TokensStorage(context)
    }

    @Provides
    @Singleton
    fun AuthRepositoryProvide(api: AuthApi, localStorage: TokensStorage): AuthRepository{
        return AuthRepositoryImpl(api, localStorage)
    }

}