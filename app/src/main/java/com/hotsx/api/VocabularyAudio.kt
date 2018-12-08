package com.hotsx.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Streaming

interface VocabularyAudio {

    @GET
    @Streaming
    fun audio(@Field("audio") text: String): Call<ResponseBody>
}