package com.hotsx.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hotsx.entity.Vocabulary

@Database(entities = [Vocabulary::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vocabularyDao(): VocabularyDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, AppDatabase::class.java, "tiny")
                        .build()
            }
        }
    }
}