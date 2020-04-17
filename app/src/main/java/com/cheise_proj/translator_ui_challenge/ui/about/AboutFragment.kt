package com.cheise_proj.translator_ui_challenge.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.translator_ui_challenge.R
import com.cheise_proj.translator_ui_challenge.common.DELAY_MILLI
import com.cheise_proj.translator_ui_challenge.common.GlideApp
import com.cheise_proj.translator_ui_challenge.model.BioEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.about_fragment.*

class AboutFragment : Fragment() {
    private lateinit var viewModel: AboutViewModel
    private lateinit var bio: BioEntity
    private var urlLink = ""

    companion object {
        fun newInstance() =
            AboutFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bio = BioEntity()
        configViewModel()

        initView()
    }

    private fun initView() {
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            setMargins(5, 5, 5, 5)
        }


        val textInfo = TextView(requireContext()).apply {
            setLayoutParams(layoutParams)
            setOnClickListener {
                intentBrowser()
                setPadding(5, 5, 5, 5)
            }
        }
        btn_link_1.setOnClickListener { setUIElement(bio.github, textInfo) }

        btn_link_2.setOnClickListener { setUIElement(bio.twitter, textInfo) }

        btn_link_3.setOnClickListener { setUIElement(bio.linkedIn, textInfo) }
    }

    private fun setUIElement(
        info: String,
        textView: TextView
    ) {
        ll_container.removeAllViews()
        textView.text = info
        urlLink = info
        ll_container.addView(textView)
        val snackBar = Snackbar.make(root, "Open in browser", Snackbar.LENGTH_LONG)
        snackBar.setAction("open") { intentBrowser() }
        snackBar.show()
    }

    private fun intentBrowser() {
        activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlLink)))
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[AboutViewModel::class.java]
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({subscribeObserver()}, DELAY_MILLI)
    }

    private fun subscribeObserver() {
        viewModel.getBioData().observe(viewLifecycleOwner, Observer { bio ->
            initBioView(bio)
        })
    }

    private fun initBioView(bio: BioEntity) {
        this.bio = bio
        tv_name.text = bio.name
        tv_description.text = bio.bio
        tv_job_title.text = bio.jobTitle
        GlideApp.with(requireContext()).load(bio.avatar).circleCrop().into(avatar)
        GlideApp.with(requireContext()).load(bio.avatar).centerCrop().into(img_1)
    }


}
