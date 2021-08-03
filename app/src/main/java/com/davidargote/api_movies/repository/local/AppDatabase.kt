package com.davidargote.api_movies.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davidargote.api_movies.application.Constants
import com.davidargote.api_movies.model.local.MovieDetailEntity
import com.davidargote.api_movies.model.local.MovieEntity

@Database(entities = [MovieEntity::class, MovieDetailEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                Constants.NAME_LOCAL_DB
            ).fallbackToDestructiveMigration().build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}