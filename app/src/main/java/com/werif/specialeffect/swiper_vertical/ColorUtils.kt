package com.werif.specialeffect.swiper_vertical

import android.graphics.Color

object ColorUtils {
    fun getColor(startColor:Int,endColor:Int,radio:Float):Int{
        var redStart= Color.red(startColor)
        var blueStart=Color.blue(startColor)
        var greenStart=Color.green(startColor)
        var redEnd=Color.red(endColor)
        var blueEnd=Color.blue(endColor)
        var greenEnd=Color.green(endColor)
        var red=(redStart+(redStart-redEnd)*radio+0.5).toInt()
        var blue=(blueStart+(redStart-blueEnd)*radio+0.5).toInt()
        var green=(greenStart+(greenStart-greenEnd)*radio+0.5).toInt()
        return Color.argb(255,red,green,blue)
    }
}