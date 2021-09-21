package com.chuckerteam.chucker.internal.data.har.log.entry.request

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.log.entry.request.postdata.Params
import com.google.gson.annotations.SerializedName

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#postdata
// text and params fields are mutually exclusive.
internal data class PostData(
    @SerializedName("mimeType") val mimeType: String,
    @SerializedName("params") val params: Params?,
    @SerializedName("text") val text: String?,
    @SerializedName("comment") val comment: String? = null,
) {
    companion object {
        fun requestPostData(transaction: HttpTransaction): PostData? {
            // todo gustavo: por que?
            if (transaction.requestPayloadSize == null || transaction.isResponseBodyEncoded) return null
            return PostData(
                mimeType = transaction.requestContentType ?: "text",
                params = null,
                text = transaction.requestBody ?: ""
            )
        }
    }
}