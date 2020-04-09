package com.cheise_proj.translator_ui_challenge.source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cheise_proj.translator_ui_challenge.model.BioEntity

@Dao
interface BioDao {
    @Insert
    fun insertBio(bio: BioEntity)

    @Query("SELECT * FROM bio")
    fun getBio(): LiveData<BioEntity>
}