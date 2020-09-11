package com.werif.specialeffect.recyclerview_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.werif.specialeffect.R
import com.werif.specialeffect.SpecialEffectsAdapter
import kotlinx.android.synthetic.main.activity_recycler_view_animator.*
import java.lang.Exception

class RecyclerViewAnimatorActivity : AppCompatActivity() {

    var dataList= mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_animator)

        var specialAdapter= SpecialEffectsAdapter(dataList){

        }
        animatorList.layoutManager= LinearLayoutManager(this)

        var animation=AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_one)
        recyclerViewBar.setNavigationOnClickListener {
            finish()
        }

        recyclerViewBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.one->{
                    animation=AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_one)
                    animatorList.layoutAnimation=animation
                    animatorList.adapter=specialAdapter
                }
                R.id.two->{
                    animation=AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_two)
                    animatorList.layoutAnimation=animation
                    animatorList.adapter=specialAdapter
                }
            }
            return@setOnMenuItemClickListener false
        }

        for(i in 0..20){
            dataList.add("第$i 项内容")
        }

        animatorList.layoutAnimation=animation
        animatorList.adapter=specialAdapter

    }

    override fun onPreparePanel(featureId: Int, view: View?, menu: Menu): Boolean {

        if(menu::class.java==MenuBuilder::class.java){
            try{
                var method=menu::class.java.getDeclaredMethod("setOptionalIconsVisible",Boolean::class.java)
                method.isAccessible=true
                method.invoke(menu,true)
            }catch (e:Exception){

            }
        }

        return super.onPreparePanel(featureId, view, menu)
    }
}