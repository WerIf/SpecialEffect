package com.werif.specialeffect.tools.more

abstract class LoadingListener<T> {
    abstract  fun onInitial(callBack: LoadInitialParams<T>)

    abstract fun onAfter(callback: LoadCallback<T>)
}