package com.levandowski.imovieshare

import android.app.Application
import com.levandowski.imovieshare.di.DaggerApplicationComponent

class IMovieShareApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
            .builder()
            .build()
    }
}
