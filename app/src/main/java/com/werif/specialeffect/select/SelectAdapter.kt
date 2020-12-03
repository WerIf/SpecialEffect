package com.werif.specialeffect.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.werif.specialeffect.R
import com.werif.specialeffect.databinding.SelectItemBinding

class SelectAdapter(
    var dataList: MutableList<String>,
    var onItemClick: (deep: Int, clickData: String) -> MutableList<String>
) : RecyclerView.Adapter<SelectViewHolder>() {

    private var originalShallowData = mutableListOf<String>()
    private var originalMiddleData = mutableMapOf<String, MutableList<String>>()
    private var originalDeepData = mutableMapOf<String, MutableList<String>>()

    private var optionDataList = mutableListOf<String>()

    private var currentDeep = 0

    private var currentSelectTitle = ""

    init {
        optionDataList.clear()
        originalShallowData.clear()
        originalMiddleData.clear()
        originalDeepData.clear()
        originalShallowData.addAll(dataList)

        optionDataList.addAll(dataList)
    }


    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        var dataBinding = DataBindingUtil.inflate<SelectItemBinding>(
            inflater,
            R.layout.select_item,
            parent,
            false
        )
        return SelectViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        when (currentDeep) {
            0 -> {
                return originalShallowData.size
            }
            1 -> {
                return originalMiddleData[currentSelectTitle]?.size ?: 0
            }
            2 -> {
                return originalDeepData[currentSelectTitle]?.size ?: 0
            }
            else -> {
                return 0
            }
        }
    }

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.dataBinding.title.text = dataList[position]
        holder.dataBinding.title.setOnClickListener {
            currentDeep += 1
            currentSelectTitle=dataList[position]
            when (currentDeep) {
                1 -> {
                    originalMiddleData[dataList[position]] =
                        onItemClick(currentDeep, dataList[position])
                    notifyDataSetChanged()
                }
                2 -> {
                    originalDeepData[dataList[position]] =
                        onItemClick(currentDeep, dataList[position])
                    notifyDataSetChanged()
                }
            }
        }
    }

}