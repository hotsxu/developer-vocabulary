package com.hotsx.api

import com.hotsx.entity.TranslateResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Translation {

    @GET("single")
    fun translation(@QueryMap map: Map<String, String>): Call<TranslateResult>
}