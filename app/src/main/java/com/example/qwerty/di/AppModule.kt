package com.example.qwerty.di

import android.app.Application
import com.example.qwerty.data.data_source.TokensStorage
import com.example.qwerty.data.remote.ApplicationAPI
import com.example.qwerty.data.repository.ApplicationRepositoryImpl
import com.example.qwerty.domain.repository.ApplicationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUnsafeOkHttpClient(): OkHttpClient {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()
    }
    @Provides
    @Singleton
    fun GetAuthApi(okHttpClient: OkHttpClient): ApplicationAPI{
          val retrofit = Retrofit.Builder()
            //.baseUrl("https://192.168.43.15:8001")                 /*Радик*/
            //.baseUrl("https://192.168.0.142:8001")                 /*Общежитие*/
            .baseUrl("https://147.45.184.52:8081")                  /*Сервер*/

            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

        return  retrofit.create(ApplicationAPI::class.java)
    }

    @Provides
    @Singleton
    fun GetTokensStorage(context: Application): TokensStorage{
        return TokensStorage(context)
    }

    @Provides
    @Singleton
    fun AuthRepositoryProvide(api: ApplicationAPI, localStorage: TokensStorage): ApplicationRepository{
        return ApplicationRepositoryImpl(api, localStorage)
    }

}