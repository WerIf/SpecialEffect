package com.werif.specialeffect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.werif.specialeffect.custom_view.RulerActivity
import com.werif.specialeffect.down_apk.DownActivity
import com.werif.specialeffect.head_move.HeadMoveActivity
import com.werif.specialeffect.head_move2.HeadMove2Activity
import com.werif.specialeffect.recyclerview_anim.RecyclerViewAnimatorActivity
import com.werif.specialeffect.recyclerview_card.RecyclerViewCardPageActivity
import com.werif.specialeffect.select.SelectActivity
import com.werif.specialeffect.to_top.ToTopActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dataList= mutableListOf<String>(
        "Behavior",
        "RecyclerView Animator",
        "HeadMove2",
        "Ruler",
        "DownApk",
        "ToTop",
        "Select",
        "CardPage"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpecialList()
    }

    private fun initSpecialList() {
        var specialAdapter=SpecialEffectsAdapter(dataList){
            when(it){
                "Behavior"->{
                    startActivity(Intent(this,HeadMoveActivity::class.java))
                }
                "RecyclerView Animator"->{
                    startActivity(Intent(this,RecyclerViewAnimatorActivity::class.java))
                }
                "HeadMove2"->{
                    startActivity(Intent(this, HeadMove2Activity::class.java))
                }
                "Ruler"->{
                    startActivity(Intent(this, RulerActivity::class.java))
                }
                "DownApk"->{
                    startActivity(Intent(this, DownActivity::class.java))
                }
                "ToTop"->{
                    startActivity(Intent(this, ToTopActivity::class.java))
                }
                "Select"->{
                    startActivity(Intent(this, SelectActivity::class.java))
                }
                "CardPage"->{
                    startActivity(Intent(this, RecyclerViewCardPageActivity::class.java))

                }
            }
        }
        specialEffectsList.adapter=specialAdapter
        specialEffectsList.layoutManager=LinearLayoutManager(this)
    }
}