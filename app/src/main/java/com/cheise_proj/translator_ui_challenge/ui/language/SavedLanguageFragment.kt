package com.cheise_proj.translator_ui_challenge.ui.language

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.translator_ui_challenge.ItemClickListener

import com.cheise_proj.translator_ui_challenge.R
import com.cheise_proj.translator_ui_challenge.common.DELAY_MILLI
import com.cheise_proj.translator_ui_challenge.common.LANGUAGE_IDENTIFIER_EXTRA
import com.cheise_proj.translator_ui_challenge.common.LanguageACTION
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity
import com.cheise_proj.translator_ui_challenge.ui.add_language.AddLanguageActivity
import com.cheise_proj.translator_ui_challenge.ui.language.adapter.LanguageAdapter
import kotlinx.android.synthetic.main.saved_language_fragment.*
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import java.util.*

class SavedLanguageFragment : Fragment() {

    private lateinit var viewModel: SavedLanguageViewModel
    private lateinit var adapter: LanguageAdapter
    private lateinit var textToSpeech: TextToSpeech

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        configViewModel()
        initTextToSpeech()
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(requireContext()) {
            if (it == TextToSpeech.SUCCESS) {
                Timber.i("Initialize success")
            }
        }
        textToSpeech.language = Locale.FRENCH
    }

    private fun initRecyclerView() {
        adapter = LanguageAdapter().apply {
            setCallBack(object : ItemClickListener<Pair<LanguageEntity, LanguageACTION>> {
                override fun data(data: Pair<LanguageEntity, LanguageACTION>) {
                    when (data.second) {
                        LanguageACTION.VIEW -> {
                            navigateToAddActivity(data.first)

                        }
                        LanguageACTION.PLAY -> {
                            playText(data.first)

                        }
                    }
                }

            })
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun playText(entity: LanguageEntity) {
        val status = textToSpeech.speak(entity.itemTwo, TextToSpeech.QUEUE_FLUSH, null, null)
        if (status == TextToSpeech.ERROR) {
            toast("can't convert text to speech")
            Timber.e("Error converting text")
        }
    }

    private fun navigateToAddActivity(data: LanguageEntity) {
        val bundle = Bundle()
        bundle.putParcelable(LANGUAGE_IDENTIFIER_EXTRA, data)
        context?.startActivity(AddLanguageActivity.newInstance(requireContext()).putExtras(bundle))
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[SavedLanguageViewModel::class.java]
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ subscribeObserver() }, DELAY_MILLI)

    }

    private fun subscribeObserver() {
        viewModel.getLanguages().observe(viewLifecycleOwner, Observer { data ->
            println("data: $data")
            adapter.submitList(data)
            recycler_view.adapter = adapter
            hideProgress(progressBar)
        })
    }

    private fun hideProgress(view: View) {
        view.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        textToSpeech.stop()
    }


}
