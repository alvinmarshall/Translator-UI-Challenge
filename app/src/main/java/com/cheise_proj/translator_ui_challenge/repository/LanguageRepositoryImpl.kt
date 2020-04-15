package com.cheise_proj.translator_ui_challenge.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity
import com.cheise_proj.translator_ui_challenge.source.IRepository
import com.cheise_proj.translator_ui_challenge.source.LanguageDao
import com.cheise_proj.translator_ui_challenge.source.LocalSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LanguageRepositoryImpl constructor(application: Application) :
    IRepository<LanguageEntity>, CoroutineScope {
    private var languageDao: LanguageDao = LocalSource.getInstance(application).languageDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun get(): LiveData<List<LanguageEntity>> = languageDao.getSaveLanguages()

    override fun get(identifier: Int): LiveData<LanguageEntity> =
        languageDao.getSaveLanguage(identifier)


    override suspend fun save(data: LanguageEntity) {
        withContext(Dispatchers.IO) {
            println("saving data...")
            languageDao.saveLanguage(data)
        }
    }

    override suspend fun update(data: LanguageEntity) {
        withContext(Dispatchers.IO) {
            println("updating data...")
            languageDao.updateLanguage(data)
        }
    }

    override suspend fun delete(data: LanguageEntity) {
        withContext(Dispatchers.IO) {
            println("deleting data...")
            languageDao.deleteLanguage(data)
        }
    }

    override suspend fun saveList(dataList: List<LanguageEntity>) {
        withContext(Dispatchers.IO){
            println("saving dataList...")
            languageDao.saveLanguageList(dataList)
        }
    }


}