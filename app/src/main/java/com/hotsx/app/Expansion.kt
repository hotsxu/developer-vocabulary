package com.hotsx.app

import android.util.Log

fun Any?.log(msg: Any?) {
    Log.v("hotsx", msg.toString())
}