package com.hotsx.entity

/**
 * 翻译API返回结果
 */
data class TranslateResult(var sentences: List<Vocabulary>,
                           var src: String)