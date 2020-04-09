package com.cheise_proj.translator_ui_challenge.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cheise_proj.translator_ui_challenge.model.BioEntity
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity

@Database(entities = [BioEntity::class, LanguageEntity::class], version = 1, exportSchema = false)
abstract class LocalSource : RoomDatabase() {
    abstract fun languageDao(): LanguageDao
    abstract fun bioDao(): BioDao

    companion object {
        private const val DATABASE_NAME = "translator_db"

        @Volatile
        private var INSTANCE: LocalSource? = null
        fun getInstance(context: Context): LocalSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context, LocalSource::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Thread(Runnable { populateBio(getInstance(context)) }).start()
                }
            })
            .build()

        private fun populateBio(db: LocalSource) {
            println("populate bio")
            db.bioDao().insertBio(
                BioEntity(
                    "Kelvin Birikorang",
                    "Mobile Application Engineer",
                    "https://github.com/Bik-Krlvn",
                    "https://twitter.com/bik_cheise",
                    "http://linkedin.com/in/kelvin-birikorang-699289179",
                    "https://avatars3.githubusercontent.com/u/21101827?s=460&u=0513c8ae79e89892eef2c293d4b72ca588affb85&v=4",
                    "Hey developers, I'm Kelvin from Ghana working at Infordas Ghana Limited"
                )
            )
        }


    }
}