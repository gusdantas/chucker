package com.chuckerteam.chucker.internal.data.har.log.entry

import com.chuckerteam.chucker.internal.data.har.log.entry.cache.SecondaryRequest
import com.google.gson.annotations.SerializedName

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#cache
internal data class Cache(
    @SerializedName("beforeRequest") val beforeRequest: SecondaryRequest? = null,
    @SerializedName("afterRequest") val afterRequest: SecondaryRequest? = null,
    @SerializedName("comment") val comment: String? = null
) {
//    todo gustavo: constructor
//    constructor(transaction: HttpTransaction) : this()
}