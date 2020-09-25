package com.werif.specialeffect.custom_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.Scroller
import androidx.annotation.ColorInt
import kotlin.math.abs

class RulerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    @ColorInt
    var startColor = Color.parseColor("#ff3415b0")

    @ColorInt
    var endColor = Color.parseColor("#ffcd0074")

    /*
    * 指示器颜色
    * */
    @ColorInt
    var indicatorColor = startColor

    /**
     *  控件宽高
     */
    var calibrationWidth = 0f
    var calibrationHeight = 0f

    /**
     * 文字画笔
     */
    var textPaint = Paint()

    /**
     * 控件画笔
     */
    var paint = Paint()

    /**
     * 线条默认宽度
     */
    var lineWidth = 22

    /**
     * 长中短刻度高度
     */
    var maxLineHeight = 0f
    var midLineHeight = 0f
    var minLineHeight = 0f

    /**
     * 指示器半径
     */
    var indicatorRadius = lineWidth * 2 / 5f

    /**
     * 刻度尺的开始，结束数字
     */
    var startNum = 0
    var endNum = 40

    /**
     * 每个刻度的数字单位
     */
    var unitNum = 1

    /**
     * 刻度间距
     */
    var calibrationSpace = 3 * lineWidth

    /**
     * 第一刻度位置距离当前位置的偏移量 小于0
     */
    var offSetStart = 0f

    /**
     * 辅助计算滑动 用户惯性计算
     */
    var scroller = Scroller(context)

    /**
     * 手指滑动速度计算
     */
    var velocityTracker: VelocityTracker = VelocityTracker.obtain()

    /**
     * 定义惯性的最小速度
     */
    var minVelocityX = 0

    /**
     * 刻度文字大小
     */
    var calibrationTextSize = 76f

    /**
     * 文字高度
     */
    var calibrationTextHeight = 0

    /**
     * 当前选中的文字
     */
    var currentSelectNum = 0

    /**
     * 当前手指移动的距离
     */
    var movedX = 0.0f

    /**
     * 手指按下控件滑动时的起始坐标
     */
    var downX = 0.0f

    lateinit var onSelectNumSelectListener: OnNumSelectListener

    init {
        paint.color = startColor
        paint.style = Paint.Style.FILL

        textPaint.color = startColor
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = calibrationTextSize
        textPaint.typeface = Typeface.DEFAULT_BOLD  //粗体

        //速度追踪器
        // scaledMaximumFlingVelocity  获得允许执行一个fling手势动作的最大速度值
        // scaledTouchSlop  获得能够进行手势滑动的距离
        minVelocityX =
            ViewConfiguration.get(context).scaledMinimumFlingVelocity  //获得允许执行一个fling手势动作的最小速度值
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //layout之后获取控件实际宽高
        calibrationWidth = w.toFloat()
        calibrationHeight = h.toFloat()
        //最长刻度默认为控件的2/3
        maxLineHeight = calibrationHeight * 2 / 3
        midLineHeight = maxLineHeight * 4 / 5
        minLineHeight = maxLineHeight * 3 / 5
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        //绘制刻度
        for (i in 0..(endNum - startNum) / unitNum) {
            var lineHeight = minLineHeight
            if (i % 10 == 0) {
                lineHeight = maxLineHeight
            } else if (i % 5 == 0) {
                lineHeight = midLineHeight
            }

            var lineLeft =
                offSetStart + movedX + calibrationWidth / 2 - lineWidth / 2 + i * calibrationSpace

            var lineRight = lineLeft + lineWidth

            var rectF = RectF(lineLeft, 4f * indicatorRadius, lineRight, lineHeight.toFloat())
            paint.color = ColorUtils.getColor(
                startColor,
                endColor,
                i / ((endNum - startNum).toFloat() / unitNum)
            )
            canvas.drawRoundRect(rectF, lineWidth / 2f, lineWidth / 2f, paint)

            if (i % 10 == 0) {
                textPaint.color = ColorUtils.getColor(
                    startColor,
                    endColor,
                    i / ((endNum - startNum).toFloat() / unitNum)
                )
                canvas.drawText(
                    "$i",
                    lineLeft + lineWidth / 2 - textPaint.measureText("$i") / 2,
                    lineHeight +  getTextHeight()*1.5f,
                    textPaint
                )
            }
        }

        //绘制指示器
        var indicatorX = calibrationWidth / 2f
        var indicatorY = indicatorRadius

        indicatorColor = ColorUtils.getColor(
            startColor,
            endColor,
            abs((offSetStart + movedX) / (calibrationSpace * ((endNum - startNum).toFloat() / unitNum)))
        )
        paint.color = indicatorColor
        canvas.drawCircle(indicatorX, indicatorY, indicatorRadius, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)
        velocityTracker.addMovement(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //让所有正在进行的动画都恢复原状，也就是停用所有动画
                scroller.forceFinished(true)
                downX = event.x
                movedX = 0f
            }
            MotionEvent.ACTION_MOVE -> {
                movedX = event.x - downX
                if (offSetStart + movedX > 0) {
                    movedX = 0f
                    offSetStart = 0f
                } else if (offSetStart + movedX < -((endNum - startNum) / unitNum) * calibrationSpace) {
                    offSetStart = -((endNum - startNum) / unitNum) * calibrationSpace.toFloat()
                    movedX = 0f
                }

                if (::onSelectNumSelectListener.isInitialized)
                    onSelectNumSelectListener.onNumSelect(getSelectedNum().toInt())

                postInvalidate()
            }
            MotionEvent.ACTION_UP -> {
                //当前指示器 指向中间刻度
                if (offSetStart + movedX <= 0 && offSetStart + movedX >= -((endNum - startNum) / unitNum * calibrationSpace)) {
                    offSetStart += movedX
                    movedX = 0f
                    //防止指示器指向刻度间隙
                    offSetStart =
                        ((offSetStart / calibrationSpace).toInt()) * calibrationSpace.toFloat()
                } else if (offSetStart + movedX > 0) {
                    offSetStart = 0f
                    movedX = 0f
                } else {
                    offSetStart = -((endNum - startNum) / unitNum * calibrationSpace).toFloat()
                    movedX = 0f
                }

                if (::onSelectNumSelectListener.isInitialized)
                    onSelectNumSelectListener.onNumSelect(getSelectedNum().toInt())

                //计算当前手指放开时的滑动速率
                velocityTracker.computeCurrentVelocity(500)
                var velocityX = velocityTracker.xVelocity
                if (abs(velocityX) > minVelocityX) {
                    scroller.fling(0, 0, velocityX.toInt(), 0, Int.MIN_VALUE, Int.MAX_VALUE, 0, 0)
                }
                postInvalidate()
            }
        }
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        //滑动结束
        if (scroller.computeScrollOffset()) {
            //当前位置 是否等于 最总停止的位置
            if (scroller.currX == scroller.finalX) {
                if (offSetStart + movedX <= 0 && offSetStart + movedX >= -((endNum - startNum) / unitNum) * calibrationSpace) {
                    //手指松开时需要磁吸效果
                    offSetStart += movedX
                    movedX = 0f
                    offSetStart =
                        ((offSetStart / calibrationSpace).toInt()) * calibrationSpace.toFloat()
                } else if (offSetStart + movedX > 0) {
                    movedX = 0f;
                    offSetStart = 0f
                } else {
                    offSetStart = -((endNum - startNum) / unitNum) * calibrationSpace.toFloat()
                    movedX = 0f
                }
            } else {
                //继续惯性滑动
                movedX = scroller.currX - scroller.startX.toFloat()
                if (offSetStart + movedX > 0) {
                    movedX = 0f
                    offSetStart = 0f
                    scroller.forceFinished(true)
                } else if (offSetStart + movedX < -((endNum - startNum) / unitNum) * calibrationSpace) {
                    offSetStart = -((endNum - startNum) / unitNum) * calibrationSpace.toFloat()
                    movedX = 0f
                    scroller.forceFinished(true)
                }
            }
        } else {
            if (offSetStart + movedX > 0) {
                movedX = 0f
                offSetStart = 0f
            }
        }

        if (::onSelectNumSelectListener.isInitialized)
            onSelectNumSelectListener.onNumSelect(getSelectedNum().toInt())

        postInvalidate()
    }

    fun initListener(selectListener: OnNumSelectListener) {
        onSelectNumSelectListener = selectListener
    }

    private fun getSelectedNum() = abs((offSetStart + movedX) / calibrationSpace)


    private fun getTextHeight():Int {
        if (calibrationTextHeight == 0){
            var rect = Rect()
            textPaint.getTextBounds("0", 0, 1, rect)
            calibrationTextHeight=rect.height()
        }
       return calibrationTextHeight
    }
}