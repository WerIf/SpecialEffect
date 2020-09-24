package com.werif.specialeffect.custom_view

import android.graphics.Color

object ColorUtils {
    fun getColor(startColor:Int,endColor:Int,radio:Float):Int{
        var redStart= Color.red(startColor)
        var blueStart=Color.blue(startColor)
        var greenStart=Color.green(startColor)
        var redEnd=Color.red(endColor)
        var blueEnd=Color.blue(endColor)
        var greenEnd=Color.green(endColor)
        var red=(redStart+(redEnd-redStart)*radio+0.5).toInt()
        var blue=(blueStart+(blueEnd-blueStart)*radio+0.5).toInt()
        var green=(greenStart+(greenEnd-greenStart)*radio+0.5).toInt()
        return Color.argb(255,red,green,blue)
    }
}