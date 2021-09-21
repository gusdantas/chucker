package com.chuckerteam.chucker.internal.data.har.log.entry

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.google.gson.annotations.SerializedName

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#timings
internal data class Timings(
    @SerializedName("blocked") val blocked: Long? = -1,
    @SerializedName("dns") val dns: Long? = -1,
    @SerializedName("connect") val connect: Long? = -1,
    @SerializedName("send") val send: Long,
    @SerializedName("wait") val wait: Long,
    @SerializedName("receive") val receive: Long,
    @SerializedName("ssl") val ssl: Long? = -1,
    @SerializedName("comment") val comment: String? = null
) {
    companion object {
        fun fromHttpTransaction(transaction: HttpTransaction) = Timings(
            blocked = null,
            dns = null,
            connect = null,
            send = 0,
            wait = 0,
            receive = transaction.tookMs ?: 0,
            ssl = null
        )
    }
}
