package com.nochita

import android.app.Application
import android.content.Context
import com.nochita.shabbatCandles.data.AppDatabase

class ShabbatCandlesApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ShabbatCandlesApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        lateinit var db : AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = AppDatabase.getInstance(this)
        val context: Context = ShabbatCandlesApplication.applicationContext()
    }
}