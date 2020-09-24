package com.werif.specialeffect.head_move2

import android.os.Bundle
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.werif.specialeffect.R
import kotlinx.android.synthetic.main.activity_head_move2.*

class HeadMove2Activity : AppCompatActivity() {

    // STATE_EXPANDED 展开状态，显示完整布局
    // STATE_COLLAPSED 折叠状态，显示peekHeigth 的高度，如果peekHeight为0，则全部隐藏,与STATE_HIDDEN效果一样
    // STATE_DRAGGING 拖拽时的状态
    // STATE_HIDDEN 隐藏时的状态
    // STATE_SETTLING 释放时的状态
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_head_move2)
//        setSupportActionBar(findViewById(R.id.toolbar))

        var layout=findViewById<LinearLayout>(R.id.design_bottom_sheet1)
        val bottomSheetBehavior=BottomSheetBehavior.from(layout)
        bottomSheetBehavior.state=BottomSheetBehavior.STATE_HIDDEN
        clickOpen.setOnClickListener {
            if(bottomSheetBehavior.state==BottomSheetBehavior.STATE_COLLAPSED){
                bottomSheetBehavior.state=BottomSheetBehavior.STATE_HIDDEN
            }else if(bottomSheetBehavior.state==BottomSheetBehavior.STATE_HIDDEN){
                bottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }
}