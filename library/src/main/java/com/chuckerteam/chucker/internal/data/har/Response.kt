package com.chuckerteam.chucker.internal.data.har

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.google.gson.annotations.SerializedName

internal data class Response(
    @SerializedName("status") val status: Int,
    @SerializedName("statusText") val statusText: String,
    @SerializedName("httpVersion") val httpVersion: String,
    @SerializedName("cookies") val cookies: List<Cookie>,
    @SerializedName("headers") val headers: List<Header>,
    @SerializedName("content") val content: PostData?,
    @SerializedName("redirectURL") val redirectUrl: String,
    @SerializedName("headersSize") val headersSize: Int,
    @SerializedName("bodySize") val bodySize: Long,
    @SerializedName("timings") val timings: Timings
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
                headers = transaction.getParsedResponseHeaders()?.map { Header(it.name, it.value) } ?: emptyList(),
                content = PostData.responsePostData(transaction),
                redirectUrl = "",
                headersSize = transaction.responseHeaders?.length ?: 0,
                bodySize = transaction.responsePayloadSize ?: 0,
                timings = Timings(0, 0, transaction.tookMs ?: 0)
            )
        }
    }
}
