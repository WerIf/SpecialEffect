package com.werif.specialeffect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.werif.specialeffect.head_move.HeadMoveActivity
import com.werif.specialeffect.head_move2.HeadMove2Activity
import com.werif.specialeffect.recyclerview_anim.RecyclerViewAnimatorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dataList= mutableListOf<String>(
        "Behavior",
        "RecyclerView Animator",
        "HeadMove2"
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
            }
        }
        specialEffectsList.adapter=specialAdapter
        specialEffectsList.layoutManager=LinearLayoutManager(this)
    }
}