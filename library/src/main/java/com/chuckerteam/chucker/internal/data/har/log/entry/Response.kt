package com.chuckerteam.chucker.internal.data.har.log.entry

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.log.entry.response.Content
import com.google.gson.annotations.SerializedName

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#response
internal data class Response(
    @SerializedName("status") val status: Int,
    @SerializedName("statusText") val statusText: String,
    @SerializedName("httpVersion") val httpVersion: String,
    @SerializedName("cookies") val cookies: List<Cookie>,
    @SerializedName("headers") val headers: List<Header>,
    @SerializedName("content") val content: Content?,
    @SerializedName("redirectURL") val redirectUrl: String,
    @SerializedName("headersSize") val headersSize: Int = -1,
    @SerializedName("bodySize") val bodySize: Long = -1,
    @SerializedName("comment") val comment: String? = null
) {
    companion object {
        fun fromHttpTransaction(transaction: HttpTransaction): Response? {
            if (transaction.responseDate == null) {
                return null
            }
            return Response(
                status = transaction.responseCode ?: 0,
                statusText = transaction.responseMessage ?: "",
                httpVersion = transaction.protocol ?: "",
                cookies = emptyList(),
                headers = transaction.getParsedResponseHeaders()?.map { Header(it.name, it.value) }
                    ?: emptyList(),
                content = Content.responseContent(transaction),
                redirectUrl = "",
                headersSize = transaction.responseHeaders?.length ?: 0,
                bodySize = transaction.responsePayloadSize ?: 0,
//                timings = Timings(0, 0, transaction.tookMs ?: 0)
            )
        }
    }
}
