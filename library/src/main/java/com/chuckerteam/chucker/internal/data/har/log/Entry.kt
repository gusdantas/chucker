package com.chuckerteam.chucker.internal.data.har.log

import androidx.annotation.VisibleForTesting
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.log.entry.Cache
import com.chuckerteam.chucker.internal.data.har.log.entry.Request
import com.chuckerteam.chucker.internal.data.har.log.entry.Response
import com.chuckerteam.chucker.internal.data.har.log.entry.Timings
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md#entries
internal data class Entry(
    @SerializedName("pageref") val pageref: String?,
    @SerializedName("startedDateTime") val startedDateTime: String,
    @SerializedName("time") val time: Long = 0,
    @SerializedName("request") val request: Request?,
    @SerializedName("response") val response: Response?,
    @SerializedName("cache") val cache: Cache,
    @SerializedName("timings") val timings: Timings,
    @SerializedName("serverIPAddress") val serverIPAddress: String?,
    @SerializedName("connection") val connection: String?,
    @SerializedName("comment") val comment: String? = null
) {
    @VisibleForTesting
    object DateFormat : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)
    }

    companion object {
        fun fromHttpTransaction(transaction: HttpTransaction) = Entry(
            pageref = null,
            startedDateTime = transaction.requestDate.harFormatted(),
            time = transaction.tookMs ?: 0,
            request = Request.fromHttpTransaction(transaction),
            response = Response.fromHttpTransaction(transaction),
            cache = Cache(null, null, null),
            timings = Timings.fromHttpTransaction(transaction),
            serverIPAddress = null,
            connection = null
        )

        private fun Long?.harFormatted(): String {
            val date = if (this == null) Date() else Date(this)
            return DateFormat.get()?.format(date) ?: ""
        }
    }
}
