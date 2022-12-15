package com.android.picart_thibaut_tpandroid.architecture

import android.app.Application
import androidx.room.Room

class CustomApplication: Application() {
    companion object{
        lateinit var instance: CustomApplication
    }

    val mApplicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomRoomDatabase::class.java,
            "Pokemon2"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}