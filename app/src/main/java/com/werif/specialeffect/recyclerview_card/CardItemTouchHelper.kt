package com.werif.specialeffect.recyclerview_card

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.werif.specialeffect.recyclerview_anim.CardLayoutManager

class CardItemTouchHelper<T>(
    var mRecyclerView: RecyclerView,
    var cardAdapter: CardPageAdapter,
    var dataList: MutableList<T>
) :
    ItemTouchHelper.Callback() {

    /**
     * 是否开启长按拖拽
     * true，开启
     * false,不开启长按退拽
     *
     * @return
     */
    override fun isLongPressDragEnabled() = false

    /**
     * 是否开启滑动
     * true，开启
     * false,不开启长按退拽
     *
     * @return
     */
    override fun isItemViewSwipeEnabled() = true

    /**
     * ItemTouchHelper支持设置事件方向，并且必须重写当前getMovementFlags来指定支持的方向
     * dragFlags  表示拖拽的方向，有六个类型的值：LEFT、RIGHT、START、END、UP、DOWN
     * swipeFlags 表示滑动的方向，有六个类型的值：LEFT、RIGHT、START、END、UP、DOWN
     * 最后要通过makeMovementFlags（dragFlag，swipe）创建方向的Flag
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        var swipeFlag = 0
        var dragFlag = 0

        if (recyclerView.layoutManager is CardLayoutManager) {
            swipeFlag =
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        }
        return makeMovementFlags(dragFlag, swipeFlag)

    }

    /**
     * 长按item就可以拖动，然后拖动到其他item的时候触发onMove
     * 这里我们不需要
     *
     * @param recyclerView
     * @param viewHolder   拖动的viewholder
     * @param target       目标位置的viewholder
     * @return
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    /**
     * 把item滑走(飞出屏幕)的时候调用
     *
     * @param viewHolder 滑动的viewholder
     * @param direction  滑动的方向
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            4 -> {

            }
            8 -> {

            }
        }
        //移除这条数据
        var remove = dataList.removeAt(viewHolder.layoutPosition)
        /** 这个位置可以用来加载数据,当滑到还剩4个或者多少个时可以在后面加载数据，添加到mDatas中*/
        //这里就为了方便，直接循环了，把移除的元素再添加到末尾
        dataList.add(dataList.size, remove)
        cardAdapter.notifyDataSetChanged()
        viewHolder.itemView.rotation = 0f
        if(viewHolder is CardPageHolder){

        }
    }
}