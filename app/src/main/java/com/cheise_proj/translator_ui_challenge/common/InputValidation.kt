package com.cheise_proj.translator_ui_challenge.common

import android.view.View

interface InputValidation {
    fun isEditTextFilled(view: View, message: String? = null): Boolean
    fun hideKeyboard(view: View)
}