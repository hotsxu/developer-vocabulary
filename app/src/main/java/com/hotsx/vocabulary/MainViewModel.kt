package com.hotsx.vocabulary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hotsx.api.Service
import com.hotsx.app.log
import com.hotsx.constant.SAVE_PATH
import com.hotsx.constant.TRANSITION_MAP
import com.hotsx.constant.TRANSITION_TEXT
import com.hotsx.db.AppDatabase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import saveAudio

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
        val map = TRANSITION_MAP.apply { put(TRANSITION_TEXT, text) }
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
                Service.audio.audio(text)
                        .execute().body()
                        ?.let {
                            saveAudio(it, "$SAVE_PATH$text.mp3")
                        }
            }
        }
    }


}