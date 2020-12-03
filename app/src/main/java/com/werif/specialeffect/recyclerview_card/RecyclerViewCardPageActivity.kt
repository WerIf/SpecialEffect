package com.werif.specialeffect.recyclerview_card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.werif.specialeffect.R
import com.werif.specialeffect.recyclerview_anim.CardLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view_card_page.*

class RecyclerViewCardPageActivity : AppCompatActivity() {

    private var cardList= mutableListOf<String>(
        "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1819216937,2118754409&fm=26&gp=0.jpg",
        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=697401579,323262224&fm=11&gp=0.jpg",
        "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1355153719,3297569375&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3782644332,3207850232&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1733173127,567261295&fm=26&gp=0.jpg",
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1203857127,2498149002&fm=26&gp=0.jpg"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_card_page)

        cardPageHome.adapter=CardPageAdapter(cardList)
        cardPageHome.layoutManager=CardLayoutManager(this)

    }
}