package com.cheise_proj.translator_ui_challenge.ui.language.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.translator_ui_challenge.ItemClickListener
import com.cheise_proj.translator_ui_challenge.R
import com.cheise_proj.translator_ui_challenge.common.LanguageACTION
import com.cheise_proj.translator_ui_challenge.model.LanguageEntity
import kotlinx.android.synthetic.main.item_language.view.*

class LanguageAdapter :
    ListAdapter<LanguageEntity, LanguageAdapter.LanguageVh>(LanguageDiff()) {
    private var itemClickListener: ItemClickListener<Pair<LanguageEntity, LanguageACTION>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageVh {
        return LanguageVh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LanguageVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    internal fun setCallBack(callback: ItemClickListener<Pair<LanguageEntity, LanguageACTION>>) {
        itemClickListener = callback
    }

    inner class LanguageVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: LanguageEntity?,
            itemClickListener: ItemClickListener<Pair<LanguageEntity, LanguageACTION>>?
        ) {
            itemView.apply {
                tv_header.text = item?.itemOne
                tv_description.text = item?.itemTwo
                btn_play.setOnClickListener {
                    itemClickListener?.data(Pair(item!!, LanguageACTION.PLAY))
                }
                setOnClickListener {
                    itemClickListener?.data(Pair(item!!, LanguageACTION.VIEW))
                }
            }
        }
    }
}

internal class LanguageDiff : DiffUtil.ItemCallback<LanguageEntity>() {
    override fun areItemsTheSame(oldItem: LanguageEntity, newItem: LanguageEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LanguageEntity, newItem: LanguageEntity): Boolean {
        return oldItem == newItem
    }
}