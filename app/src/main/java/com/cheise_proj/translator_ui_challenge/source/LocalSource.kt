package com.cheise_proj.translator_ui_challenge.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cheise_proj.translator_ui_challenge.common.getBioInfo
import com.cheise_proj.translator_ui_challenge.common.getDefaultAlphabet
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
                    Thread(Runnable {
                        populateBio(getInstance(context))
                        populateInitialLanguage(getInstance(context))
                    }).start()
                }
            })
            .build()

        private fun populateInitialLanguage(db: LocalSource) {
            db.runInTransaction {
                db.languageDao().saveLanguageList(getDefaultAlphabet())
            }
        }

        private fun populateBio(db: LocalSource) {
            println("populate bio")
            db.bioDao().insertBio(getBioInfo())
        }


    }
}