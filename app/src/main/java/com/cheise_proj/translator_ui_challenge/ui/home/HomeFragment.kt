package com.cheise_proj.translator_ui_challenge.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cheise_proj.translator_ui_challenge.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switch_keyboard.setOnCheckedChangeListener { _, _ ->
            switchKeyboard()
        }
    }

    private fun switchKeyboard() {
        val inputIntent = Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS)
        activity?.startActivity(inputIntent)
    }


}
