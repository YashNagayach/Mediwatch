package com.mediwatch.core.pagination.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mediwatch.core.domain.model.AssociatedDrug
import com.mediwatch.core.pagination.dao.HomeDao


@Database(version = 1, entities = [AssociatedDrug::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getHomeDao(): HomeDao

    companion object {

        private const val HOME_DB = "home.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, HOME_DB)
                .build()
    }

}