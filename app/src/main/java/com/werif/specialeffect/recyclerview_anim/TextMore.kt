package com.werif.specialeffect.recyclerview_anim

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.werif.specialeffect.tools.more.LoadCallback
import com.werif.specialeffect.tools.more.LoadInitialParams
import com.werif.specialeffect.tools.more.Test
import com.werif.specialeffect.tools.more.ToLoad

class TextMore : ToLoad<String>() {

    private lateinit var recyclerView: RecyclerView
    private var sourceList: MutableList<String>? = null
    fun load(recyclerView: RecyclerView, sourceDataList: MutableList<String>) {
        this.recyclerView = recyclerView
        sourceList=sourceDataList
        initRecycler(recyclerView)
    }


    override fun onInitial(callBack: LoadInitialParams<String>) {
        if(sourceList==null)return
        for (i in 0..20) {
            sourceList!!.add("当前是第 $i 项数据")
        }
        recyclerView.adapter?.notifyDataSetChanged()
        callBack.onInitialResult("haveNext", sourceList!!)

    }

    override fun onAfter(callBack: LoadCallback<String>) {
//        super.onAfter(callBack)
        for (i in 21..30) {
            sourceList!!.add("当前是第 $i 项数据")
        }
        recyclerView.adapter?.notifyDataSetChanged()
        callBack.onAfterResult("haveNext", sourceList!!)
    }
}


