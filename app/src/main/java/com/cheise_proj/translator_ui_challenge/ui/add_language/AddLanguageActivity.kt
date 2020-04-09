package com.cheise_proj.translator_ui_challenge.ui.add_language

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.translator_ui_challenge.R
import com.cheise_proj.translator_ui_challenge.common.DELAY_MILLI
import com.cheise_proj.translator_ui_challenge.common.InputValidationImpl
import com.cheise_proj.translator_ui_challenge.common.LANGUAGE_IDENTIFIER_EXTRA
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_language.*

class AddLanguageActivity : AppCompatActivity() {
    private lateinit var viewModel: AddLanguageViewModel
    private var language: LanguageEntity? = null
    private lateinit var inputValidationImpl: InputValidationImpl


    companion object {
        fun newInstance(context: Context): Intent = Intent(context, AddLanguageActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_language)

        btn_save.setOnClickListener { subscribeSaveObserver() }
        val extras = intent.extras

        extras?.let { bundle ->
            if (bundle.containsKey(LANGUAGE_IDENTIFIER_EXTRA)) {
                language = extras.getParcelable(LANGUAGE_IDENTIFIER_EXTRA)
                initView(language)
            }
        }

        inputValidationImpl = InputValidationImpl()
        configViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                subscribeDeleteObserver(language)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

    private fun initView(language: LanguageEntity?) {
        btn_save.text = getString(R.string.label_update)
        btn_save.setOnClickListener {
            subscribeUpdateObserver(language)
        }
        et_item_1.setText(language?.itemOne)
        et_item_2.setText(language?.itemTwo)
    }


    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[AddLanguageViewModel::class.java]

    }

    private fun subscribeSaveObserver() {
        if (!inputValidationImpl.isEditTextFilled(et_item_1)) return
        if (!inputValidationImpl.isEditTextFilled(et_item_2)) return

        val itemOne = et_item_1.text.toString()
        val itemTwo = et_item_2.text.toString()

        viewModel.saveLanguage(LanguageEntity(itemOne, itemTwo))
        Snackbar.make(root, "Saved successfully", Snackbar.LENGTH_SHORT).show()
        resetField()
    }

    private fun subscribeUpdateObserver(language: LanguageEntity?) {
        if (!inputValidationImpl.isEditTextFilled(et_item_1)) return
        if (!inputValidationImpl.isEditTextFilled(et_item_2)) return

        val itemOne = et_item_1.text.toString()
        val itemTwo = et_item_2.text.toString()
        language?.apply {
            this.itemOne = itemOne
            this.itemTwo = itemTwo
        }
        viewModel.updateLanguage(language!!)

        Snackbar.make(root, "Updated successfully", Snackbar.LENGTH_SHORT).show()
        resetField()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ onBackPressed() }, DELAY_MILLI)
    }

    private fun subscribeDeleteObserver(language: LanguageEntity?) {
        language?.let { data ->
            viewModel.deleteLanguage(data)
            Snackbar.make(root, "Delete item successfully", Snackbar.LENGTH_SHORT).show()
            val handler = Handler(Looper.getMainLooper())
            resetField()
            handler.postDelayed({ onBackPressed() }, DELAY_MILLI)
        }

    }

    private fun resetField() {
        et_item_1.text?.clear()
        et_item_2.text?.clear()
    }
}
