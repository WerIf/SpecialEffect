package com.werif.specialeffect.tools.more

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener: RecyclerView.OnScrollListener(){

    var isSlidingUpward=false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        var manager=recyclerView.layoutManager as LinearLayoutManager
        if(newState==RecyclerView.SCROLL_STATE_IDLE){
            var lastItemPosition=manager.findLastCompletelyVisibleItemPosition()
            var itemCount=manager.itemCount
            if(lastItemPosition==(itemCount-1) && isSlidingUpward){
                onLoadMore()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        isSlidingUpward=dy>0
    }

    abstract fun onLoadMore()
}