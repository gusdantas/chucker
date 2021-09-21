package com.chuckerteam.chucker.internal.data.har.log.entry

import com.google.gson.annotations.SerializedName

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#headers
internal data class Header(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String,
    @SerializedName("comment") val comment: String? = null
)
