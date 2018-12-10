package com.hotsx.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface VocabularyAudio {

    @Streaming
    @GET("/dictvoice")
    fun audio(@Query("audio") text: String): Call<ResponseBody>
}