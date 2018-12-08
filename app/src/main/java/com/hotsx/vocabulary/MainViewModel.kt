package com.hotsx.vocabulary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hotsx.api.Service
import com.hotsx.app.log
import com.hotsx.db.AppDatabase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db by lazy { AppDatabase.getInstance(application) }

    private lateinit var translationResultLiveData: MutableLiveData<String>
    fun getTranslation() = if (!::translationResultLiveData.isInitialized) {
        translationResultLiveData = MutableLiveData()
        translationResultLiveData
    } else {
        translationResultLiveData
    }

    fun translation(text: String) {
        val map = mapOf("client" to "gtx",
                "dj" to "1",
                "tl" to "zh-CN",
                "dt" to "t",
                "sl" to "en",
                "q" to text)
        GlobalScope.launch(Default) {
            Service.translation
                    .translation(map)
                    .execute().body()?.let {
                        log(it)
                        db.vocabularyDao().insertAll(it.sentences)
                    }
            Service.audio.audio(text)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                        }

                    })
        }
    }
}