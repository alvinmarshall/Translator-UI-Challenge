package com.cheise_proj.translator_ui_challenge.common

import com.cheise_proj.translator_ui_challenge.model.BioEntity
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity

enum class LanguageACTION {
    VIEW, PLAY
}

const val LANGUAGE_IDENTIFIER_EXTRA = "identifier_key"
const val DELAY_MILLI: Long = 500

fun getBioInfo(): BioEntity {
    return BioEntity(
        "Kelvin Birikorang",
        "Mobile Application Engineer",
        "https://github.com/Bik-Krlvn",
        "https://twitter.com/bik_cheise",
        "http://linkedin.com/in/kelvin-birikorang-699289179",
        "https://avatars3.githubusercontent.com/u/21101827?s=460&u=0513c8ae79e89892eef2c293d4b72ca588affb85&v=4",
        "Hey developers, I'm Kelvin from Ghana working at Infordas Ghana Limited"
    )
}

fun getDefaultAlphabet():ArrayList<LanguageEntity>{
    return  arrayListOf(
        LanguageEntity("A","a"),
        LanguageEntity("B","bé"),
        LanguageEntity("C","cé"),
        LanguageEntity("D","dé"),
        LanguageEntity("E","e"),
        LanguageEntity("F","effe"),
        LanguageEntity("G","gé"),
        LanguageEntity("H","ache"),
        LanguageEntity("I","i"),
        LanguageEntity("J","ji"),
        LanguageEntity("K","ka"),
        LanguageEntity("L","elle"),
        LanguageEntity("M","emme"),
        LanguageEntity("L","enne"),
        LanguageEntity("O","o"),
        LanguageEntity("P","pé"),
        LanguageEntity("Q","qu"),
        LanguageEntity("R","erre"),
        LanguageEntity("S","esse"),
        LanguageEntity("T","té"),
        LanguageEntity("U","u"),
        LanguageEntity("V","vé"),
        LanguageEntity("W","double vé"),
        LanguageEntity("X","ixe"),
        LanguageEntity("Y","i grec"),
        LanguageEntity("Z","zède")
    )

}