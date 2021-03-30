package com.akinci.matchscores.features.scores.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Extra (
    val iddaa_code : Int,
    val iddaa_live : Boolean
)