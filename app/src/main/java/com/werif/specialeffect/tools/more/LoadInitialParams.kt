package com.werif.specialeffect.tools.more

interface  LoadInitialParams<T> {
     fun onInitialResult(pageKey:String,data:List<T>)
}