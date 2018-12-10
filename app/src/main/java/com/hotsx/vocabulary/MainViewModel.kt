package com.hotsx.vocabulary

import android.Manifest
import android.app.Activity
import android.app.Application
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hotsx.api.Service
import com.hotsx.app.log
import com.hotsx.db.AppDatabase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import saveAudio
import java.io.File
import java.io.FileOutputStream

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
        GlobalScope.launch(Main) {
            withContext(Default) {
                Service.translation
                        .translation(map)
                        .execute().body()
                        ?.let {
                            log(it)
                            db.vocabularyDao()
                                    .insertAll(it.sentences)
                        }
            }
            withContext(Default) {
                Service.audio
                        .audio(text)
                        .execute().body()
                        ?.let {
                            saveAudio(it, text)
                        }
            }
        }
    }


}