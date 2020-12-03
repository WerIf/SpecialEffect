package com.werif.specialeffect.recyclerview_anim

import android.content.Context
import android.util.TypedValue
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardLayoutManager(var context: Context) : RecyclerView.LayoutManager() {

    private var verticalScrollOffSet = 0
    private var totalHeight = 0

    var maxShow = 3
    var SCALE_GAP = 0.05

//     TRANS_Y_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, context.getResources().getDisplayMetrics());
//        TRANS_Z_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, context.getResources().getDisplayMetrics());
//

    var TRANS_Y_GAP = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        15f,
        context.getResources().getDisplayMetrics()
    )

    var TRANS_Z_GAP = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        0.5f,
        context.getResources().getDisplayMetrics()
    );

    override fun canScrollVertically() = true


    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
//        super.onLayoutChildren(recycler, state)

        detachAndScrapAttachedViews(recycler!!)

        if (itemCount < 1)
            return

        var bottomPosition = 0

        //如果不足3条
        if (itemCount < maxShow) {
            //那么最底层就是最后一条数据对应的position
            bottomPosition = itemCount - 1
        } else {
            bottomPosition = maxShow - 1
        }

        //开始绘制
        for (i in bottomPosition downTo 0) {
            var view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            var widthSpace = width - getDecoratedMeasuredWidth(view)
            var heightSpace = height - getDecoratedMeasuredHeight(view)
            layoutDecorated(
                view,
                widthSpace / 2,
                200,
                widthSpace / 2 + getDecoratedMeasuredWidth(view),
                getDecoratedMeasuredHeight(view) + 200
            )

            if (i > 0) {
                view.scaleX = (1 - SCALE_GAP * i).toFloat()
                if (i < maxShow - 1) {
                    view.scaleY = (1 - SCALE_GAP * i).toFloat()
                    view.translationY = 1 - TRANS_Y_GAP * i
                    view.translationZ = TRANS_Z_GAP * (maxShow - 1 - i)
                } else {
                    view.scaleY = (1 - SCALE_GAP * (i - 1)).toFloat()
                    view.translationY = 1 - TRANS_Y_GAP * (i - 1)
                    view.translationZ = TRANS_Z_GAP * (maxShow - 1 - (i - 1))
                }
            } else {
                view.translationZ = TRANS_Z_GAP * (maxShow - 1)
            }
        }
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        //dy 滑动的距离
        var travel = dy

        if (verticalScrollOffSet + dy < 0) {
            //滑动到顶部
            travel = -verticalScrollOffSet
        } else if (verticalScrollOffSet + dy > totalHeight - getVerticalSpace()) {
            //滑动到底部
            travel = totalHeight - getVerticalSpace() - verticalScrollOffSet
        }

        //将竖直方向的偏移量 + travel
        verticalScrollOffSet += travel

        //平移容器内的item
        offsetChildrenVertical(-travel)

        return travel
    }

    private fun getVerticalSpace() = height - paddingBottom - paddingTop
}