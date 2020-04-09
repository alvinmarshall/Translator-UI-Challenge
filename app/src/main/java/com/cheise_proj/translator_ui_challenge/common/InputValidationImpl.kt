package com.cheise_proj.translator_ui_challenge.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.cheise_proj.translator_ui_challenge.R

class InputValidationImpl : InputValidation {
    override fun isEditTextFilled(view: View, message: String?): Boolean {
        val editText = view as EditText
        val value = editText.text.toString().trim()
        if (value.isEmpty()) {
            editText.error = message ?: view.context.getString(R.string.error_empty_input)
            return false
        }

        return true
    }

    override fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }


}