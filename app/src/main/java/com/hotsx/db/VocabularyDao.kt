package com.hotsx.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hotsx.entity.Vocabulary

/**
 * Vicabulary数据库操作类
 */
@Dao
interface VocabularyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vocabularies: List<Vocabulary>)

    @Query("SELECT * FROM vocabularies ORDER BY id")
    fun queryAll(): LiveData<List<Vocabulary>>
}