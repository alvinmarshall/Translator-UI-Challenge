package com.cheise_proj.translator_ui_challenge.ui.add_language

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity
import com.cheise_proj.translator_ui_challenge.repository.LanguageRepositoryImpl
import kotlinx.coroutines.launch

class AddLanguageViewModel(application: Application) : AndroidViewModel(application) {
    private val languageRepositoryImpl: LanguageRepositoryImpl = LanguageRepositoryImpl(application)

    fun getLanguage(identifier: Int): LiveData<LanguageEntity> =
        languageRepositoryImpl.get(identifier)

    fun saveLanguage(language: LanguageEntity) {
        viewModelScope.launch { languageRepositoryImpl.save(language) }
    }

    fun updateLanguage(language: LanguageEntity) {
        viewModelScope.launch { languageRepositoryImpl.update(language) }
    }

    fun deleteLanguage(language: LanguageEntity) {
        viewModelScope.launch { languageRepositoryImpl.delete(language) }
    }

}
