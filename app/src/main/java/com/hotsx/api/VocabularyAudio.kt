package com.hotsx.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface VocabularyAudio {

    @GET
    fun audio(@Field("audio") text: String): Call<ResponseBody>
}