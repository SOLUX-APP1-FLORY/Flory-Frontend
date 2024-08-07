package com.solux.flory.app.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.solux.flory.BuildConfig.FLORY_BASE_URL
import com.solux.flory.app.interceptor.TokenInterceptor
import com.solux.flory.data.datasource.UserPreferencesDataSource
import com.solux.flory.data.datasourceimpl.UserPreferencesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @AccessToken tokenInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesUserPreferencesDataSource(dataStore: UserPreferencesDataSourceImpl): UserPreferencesDataSource =
        dataStore

    @Provides
    @Singleton
    @AccessToken
    fun provideAuthInterceptor(interceptor: TokenInterceptor): Interceptor = interceptor

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() -> Timber.d(JSONObject(message).toString(4))
                message.isJsonArray() -> Timber.d(JSONArray(message).toString(4))
                else -> Timber.d("CONNECTION INFO -> $message")
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    @FloryRetrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .baseUrl(FLORY_BASE_URL)
        .build()
}