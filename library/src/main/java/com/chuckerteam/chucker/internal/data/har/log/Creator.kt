package com.chuckerteam.chucker.internal.data.har.log

import com.google.gson.annotations.SerializedName

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#creator
internal data class Creator(
    @SerializedName("name") val name: String = "BuildConfig.LIBRARY_PACKAGE_NAME",
    @SerializedName("version") val version: String = "BuildConfig.VERSION_NAME",
    @SerializedName("comment") val comment: String? = null
)
