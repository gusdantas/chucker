package com.chuckerteam.chucker.internal.data.har.log.entry

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.log.entry.response.Content
import com.google.gson.annotations.SerializedName
import java.net.HttpURLConnection

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#response
internal data class Response(
    @SerializedName("status") val status: Int,
    @SerializedName("statusText") val statusText: String,
    @SerializedName("httpVersion") val httpVersion: String,
    @SerializedName("cookies") val cookies: List<Cookie>,
    @SerializedName("headers") val headers: List<Header>,
    @SerializedName("content") val content: Content?,
    @SerializedName("redirectURL") val redirectUrl: String,
    @SerializedName("headersSize") val headersSize: Int,
    @SerializedName("bodySize") val bodySize: Long,
    @SerializedName("comment") val comment: String? = null
) {
    constructor(transaction: HttpTransaction) : this(
        status = transaction.responseCode ?: 0,
        statusText = transaction.responseMessage ?: "",
        httpVersion = transaction.protocol ?: "",
        cookies = emptyList(),
        headers = transaction.getParsedResponseHeaders()?.map { Header(it) } ?: emptyList(),
        content = Content.responseContent(transaction),
        redirectUrl = "",
        headersSize = transaction.responseHeaders?.length ?: -1,
        bodySize = if (transaction.responseCode == HttpURLConnection.HTTP_NOT_MODIFIED) 0
        else transaction.responsePayloadSize ?: -1
    )
}