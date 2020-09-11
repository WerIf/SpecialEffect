package com.werif.specialeffect

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.werif.specialeffect.databinding.LayoutSpecialEffectsItemBinding

class SpecialEffectsAdapter(
    var dataList: MutableList<String>,
    var onItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<SpecialEffectsAdapter.SpecialEffectsHolder>() {

    inner class SpecialEffectsHolder(var binding: LayoutSpecialEffectsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialEffectsHolder {
        var inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var specialHolderBinding = DataBindingUtil.inflate<LayoutSpecialEffectsItemBinding>(
            inflater,
            R.layout.layout_special_effects_item,
            parent,
            false
        )
        return SpecialEffectsHolder(specialHolderBinding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: SpecialEffectsHolder, position: Int) {
        holder.binding.setVariable(BR.specialName, dataList[position])

        holder.binding.root.setOnClickListener {
            onItemClickListener(dataList[position])
        }
    }
}