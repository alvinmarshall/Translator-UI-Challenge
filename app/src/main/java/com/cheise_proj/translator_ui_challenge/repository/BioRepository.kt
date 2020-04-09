package com.cheise_proj.translator_ui_challenge.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.cheise_proj.translator_ui_challenge.model.BioEntity
import com.cheise_proj.translator_ui_challenge.source.LocalSource

class BioRepository constructor(application: Application) {
    private val bioDao = LocalSource.getInstance(application).bioDao()

    fun getBio():LiveData<BioEntity> = bioDao.getBio()
}