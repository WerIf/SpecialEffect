package com.werif.specialeffect.down_apk

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.werif.specialeffect.R

class WaveView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var wavePaint= Paint()

    init {

        wavePaint.style= Paint.Style.FILL
        wavePaint.color=ContextCompat.getColor(context, R.color.theme)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(canvas==null)return





    }
}