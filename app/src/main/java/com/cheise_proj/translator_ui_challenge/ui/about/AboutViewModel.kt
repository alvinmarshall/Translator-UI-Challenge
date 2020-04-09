package com.cheise_proj.translator_ui_challenge.ui.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cheise_proj.translator_ui_challenge.model.BioEntity
import com.cheise_proj.translator_ui_challenge.repository.BioRepository

class AboutViewModel(application: Application) : AndroidViewModel(application) {
    private val bioRepository = BioRepository(application)
    fun getBioData(): LiveData<BioEntity> = bioRepository.getBio()
}
