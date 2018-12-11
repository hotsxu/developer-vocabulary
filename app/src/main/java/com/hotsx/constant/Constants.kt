package com.hotsx.constant

import android.os.Environment

val SAVE_PATH: String by lazy { Environment.getExternalStorageDirectory().path + "/vocabulary/" }

val TRANSITION_MAP = mapOf("client" to "gtx",
        "dj" to "1",
        "tl" to "zh-CN",
        "dt" to "t",
        "sl" to "en",
        "text" to "google").toMutableMap()

const val TRANSITION_TEXT = "text"