package com.example.animelookup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {
    fun<T: Any> ioWork(worK: suspend (() -> T?), callback: ((T?) -> Unit)){
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                worK()
            }
        }
    }
}