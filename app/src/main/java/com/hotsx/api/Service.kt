package com.hotsx.api

import com.hotsx.retrofit.FileConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {

    val translation: Translation by lazy {
        Retrofit.Builder()
                .baseUrl("http://translate.google.cn/translate_a/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Translation::class.java)
    }

    val audio: VocabularyAudio by lazy {
        Retrofit.Builder()
                .baseUrl("http://dict.youdao.com")
                .addConverterFactory(FileConverterFactory.create())
                .build().create(VocabularyAudio::class.java)
    }
}