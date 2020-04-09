package com.cheise_proj.translator_ui_challenge.ui.language

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity
import com.cheise_proj.translator_ui_challenge.repository.LanguageRepositoryImpl

class SavedLanguageViewModel(application: Application) : AndroidViewModel(application) {
    private val languageRepositoryImpl: LanguageRepositoryImpl = LanguageRepositoryImpl(application)

    fun getLanguages(): LiveData<List<LanguageEntity>> = languageRepositoryImpl.get()
}
