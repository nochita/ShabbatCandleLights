package com.nochita.shabbatCandles.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nochita.shabbatCandles.model.ShabbatCandlesData


@Database(entities = [ShabbatCandlesData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "Shabbat-candles-db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context : Context): AppDatabase {
            return instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

    abstract fun dataDao(): ShabbatCandlesDao
}