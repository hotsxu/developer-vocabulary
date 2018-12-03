package com.hotsx.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabularies")
data class Vocabulary(
        @PrimaryKey
        val id: Int,
        val trans: String,
        val orig: String,
        var backend: Int,
        var audio: String?)