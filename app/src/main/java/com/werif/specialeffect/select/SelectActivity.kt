package com.werif.specialeffect.select

import android.content.Context
import android.graphics.Color.green
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.werif.specialeffect.R
import kotlinx.android.synthetic.main.activity_select.*

class SelectActivity : AppCompatActivity() {

    private lateinit var dataAdapter: SelectAdapter

    private var dataProvider = mutableListOf<String>(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        instantiationList(this, dataProvider)

        instantiationSelectText("请选择")
    }


    private fun instantiationSelectText(title: String) {
        if(tabTitle.childCount>0){
            (tabTitle.getChildAt(tabTitle.childCount-1) as TextView).setTextColor(ContextCompat.getColor(this,R.color.myGray))
        }
        var selectTitle = TextView(this)
        selectTitle.text = title
        selectTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        var textParam = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 60)
        selectTitle.layoutParams = textParam
//        selectTitle.setPadding(10, 0, 0, 10)
        selectTitle.setTextColor(ContextCompat.getColor(this,R.color.myGreen))
        tabTitle.addView(selectTitle)
    }

    private fun instantiationList(context: Context, dataList: MutableList<String>) {
        if (!::dataAdapter.isInitialized) {
            dataAdapter = SelectAdapter(dataList) { deep, clickData ->
                var backData = mutableListOf<String>()
                for (i in 0..10) {
                    backData.add("$clickData  第 $i 项数据")
                }
                instantiationSelectText(clickData)
                backData
            }
            tabData.adapter = dataAdapter
            tabData.layoutManager = LinearLayoutManager(context)
            var decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.decollator)!!)
            tabData.addItemDecoration(decoration)
        }
    }
}