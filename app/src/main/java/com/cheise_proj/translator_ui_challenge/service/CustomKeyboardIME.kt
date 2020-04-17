package com.cheise_proj.translator_ui_challenge.service

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.Keyboard.*
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputConnection
import com.cheise_proj.translator_ui_challenge.R

class CustomKeyboardIME : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    private var keyboardView: KeyboardView? = null
    private var keyboard: Keyboard? = null
    private var caps = false

    override fun swipeRight() {
    }

    override fun onPress(primaryCode: Int) {
    }

    override fun onRelease(primaryCode: Int) {
    }

    override fun swipeLeft() {
    }

    override fun swipeUp() {
    }

    override fun swipeDown() {
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val inputConnection: InputConnection? = currentInputConnection
        inputConnection?.let {
            when (primaryCode) {
                KEYCODE_DELETE -> {
                    val selectedText = inputConnection.getSelectedText(0)
                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0)
                    } else {
                        inputConnection.commitText("", 1)
                    }
                }
                KEYCODE_SHIFT -> {
                    caps = !caps
                    keyboard?.isShifted = caps
                    keyboardView?.invalidateAllKeys()
                }
                KEYCODE_DONE -> {
                    inputConnection.sendKeyEvent(KeyEvent(ACTION_DOWN, KEYCODE_ENTER))
                }
                else -> {
                    val code: Char = primaryCode.toChar()
                    inputConnection.commitText("$code", 1)
                }
            }

        } ?: return

    }

    override fun onText(text: CharSequence?) {
    }

    override fun onCreateInputView(): View {
        val viewGroup: ViewGroup? = null
        keyboardView = layoutInflater.inflate(R.layout.keyboard, viewGroup) as KeyboardView?
        keyboard = Keyboard(this, R.xml.french_qwerty)
        keyboardView?.keyboard = keyboard
        keyboardView?.setOnKeyboardActionListener(this)
        return keyboardView as KeyboardView
    }


}