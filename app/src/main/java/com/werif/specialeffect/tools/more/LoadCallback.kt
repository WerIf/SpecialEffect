package com.werif.specialeffect.tools.more


interface  LoadCallback<T> {

     fun onAfterResult(pageKey:String,data:List<T>)
}