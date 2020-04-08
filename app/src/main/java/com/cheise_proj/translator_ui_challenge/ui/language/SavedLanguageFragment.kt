package com.cheise_proj.translator_ui_challenge.ui.language

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cheise_proj.translator_ui_challenge.R

class SavedLanguageFragment : Fragment() {

    companion object {
        fun newInstance() =
            SavedLanguageFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saved_language_fragment, container, false)
    }



}
