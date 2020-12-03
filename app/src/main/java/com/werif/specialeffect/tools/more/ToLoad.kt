package com.werif.specialeffect.tools.more
import androidx.recyclerview.widget.RecyclerView

open class ToLoad<T> : LoadingListener<T>(), LoadInitialParams<T>, LoadCallback<T> {

    private var nextPageKey: String = ""

    private var haveMoreData = true

    fun initRecycler(targetRecyclerView: RecyclerView) {
        targetRecyclerView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                if (haveMoreData)
                    onAfter(this@ToLoad)
            }
        })

        onInitial(this)

    }

    override fun onInitial(callBack: LoadInitialParams<T>) {
    }

     override fun onAfter(callBack: LoadCallback<T>) {
    }


    override fun onInitialResult(pageKey: String, data: List<T>) {
        if (pageKey.isNotEmpty())
            nextPageKey = pageKey
    }

    override fun onAfterResult(pageKey: String, data: List<T>) {
        if (pageKey.isNotEmpty() && pageKey != nextPageKey)
            nextPageKey = pageKey
        else if (pageKey == nextPageKey) {
            haveMoreData = false
        }
    }

}