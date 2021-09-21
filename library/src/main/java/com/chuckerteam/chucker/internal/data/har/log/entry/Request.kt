package com.chuckerteam.chucker.internal.data.har.log.entry

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.log.entry.request.PostData
import com.chuckerteam.chucker.internal.data.har.log.entry.request.QueryString
import com.google.gson.annotations.SerializedName
import okhttp3.HttpUrl.Companion.toHttpUrl

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#request
internal data class Request(
    @SerializedName("method") val method: String,
    @SerializedName("url") val url: String,
    @SerializedName("httpVersion") val httpVersion: String,
    @SerializedName("cookies") val cookies: List<Cookie>,
    @SerializedName("headers") val headers: List<Header>,
    @SerializedName("queryString") val queryString: List<QueryString>,
    @SerializedName("postData") val postData: PostData?,
    @SerializedName("headersSize") val headersSize: Int,
    @SerializedName("bodySize") val bodySize: Long,
    @SerializedName("comment") val comment: String? = null
) {
    constructor(transaction: HttpTransaction) : this(
        method = transaction.method ?: "",
        url = transaction.url ?: "",
        httpVersion = transaction.protocol ?: "",
        cookies = emptyList(),
        headers = transaction.getParsedRequestHeaders()?.map { Header(it) } ?: emptyList(),
        queryString = QueryString.fromUrl(transaction.url!!.toHttpUrl()),
        postData = PostData.requestPostData(transaction),
        headersSize = transaction.requestHeaders?.length ?: -1,
        bodySize = transaction.requestPayloadSize ?: -1
    )
}
