package com.cheise_proj.translator_ui_challenge.source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity

@Dao
interface LanguageDao {

    @Insert
    suspend fun saveLanguage(language: LanguageEntity)

    @Query("SELECT * FROM language")
    fun getSaveLanguages(): LiveData<List<LanguageEntity>>

    @Query("SELECT * FROM language WHERE id = :identifier")
    fun getSaveLanguage(identifier: Int): LiveData<LanguageEntity>

    @Update
    suspend fun updateLanguage(language: LanguageEntity)

    @Delete
    suspend fun deleteLanguage(language: LanguageEntity)

}