package com.small.main

import android.app.Application
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatDelegate
import com.google.gson.GsonBuilder
import com.small.main.data.local.database.AppDatabase
import com.small.main.data.remote.repository.EventRepository
import com.small.main.data.remote.repository.EventRepositoryImpl
import com.small.main.data.remote.service.ApiService
import com.small.main.ui.favoritematch.FavoriteMatchPresenter
import com.small.main.ui.nextmatch.NextMatchPresenter
import com.small.main.ui.previousmatch.PrevMatchPresenter
import com.small.main.ui.detailmatch.MatchDetailPresenter
import com.small.main.ui.teams.TeamsFragment
import com.small.main.ui.teams.TeamsPresenter
import com.small.main.util.CoroutinesContextProvider
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseApplication : Application() {

    private val module: Module = module {
        single<ApiService> {
            val okHttpClient = OkHttpClient.Builder().build()
            val gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            retrofit.create(ApiService::class.java)
        }

        // Single instance of EventRepository
        single<EventRepository> { EventRepositoryImpl(get()) }
        single { CoroutinesContextProvider() }

        // Single instance of Database
        single {
            Room.databaseBuilder(get(), AppDatabase::class.java,
                    "football-db").build()
        }

        // Simple Presenter Factory
        factory { PrevMatchPresenter(get(), get()) }
        factory { NextMatchPresenter(get(), get()) }
        factory { FavoriteMatchPresenter(get(), get(), get()) }
        factory { MatchDetailPresenter(get(), get(), get()) }
        factory { TeamsPresenter(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        startKoin(this, listOf(module))
    }

}