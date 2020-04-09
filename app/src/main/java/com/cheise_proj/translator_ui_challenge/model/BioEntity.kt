package com.cheise_proj.translator_ui_challenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bio")
data class BioEntity(
    val name: String,
    val jobTitle: String,
    val github: String,
    val twitter: String,
    val linkedIn: String,
    val avatar: String,
    val bio: String
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1
    constructor():this("","","","","","","")
}