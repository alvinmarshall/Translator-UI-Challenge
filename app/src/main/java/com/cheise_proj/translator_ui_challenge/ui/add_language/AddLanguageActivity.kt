package com.cheise_proj.translator_ui_challenge.ui.add_language

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cheise_proj.translator_ui_challenge.R

class AddLanguageActivity : AppCompatActivity() {
    companion object {
        fun newInstance(context: Context): Intent = Intent(context, AddLanguageActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_language)
    }
}
