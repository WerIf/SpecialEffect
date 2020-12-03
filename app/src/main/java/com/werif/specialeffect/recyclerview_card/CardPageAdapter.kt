package com.werif.specialeffect.recyclerview_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.werif.specialeffect.R
import com.werif.specialeffect.databinding.CardPageItemBinding

class CardPageAdapter(var imagePageList: MutableList<String>) :
    RecyclerView.Adapter<CardPageHolder>() {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPageHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        var imagePageBinding = DataBindingUtil.inflate<CardPageItemBinding>(
            inflater,
            R.layout.card_page_item, parent, false
        )
        return CardPageHolder((imagePageBinding))
    }

    override fun getItemCount() = imagePageList.size

    override fun onBindViewHolder(holder: CardPageHolder, position: Int) {
//            Glide.with
        Glide.with(holder.cardBinding.root.context).load(imagePageList[position])
            .into(holder.cardBinding.cardImage);
    }
}