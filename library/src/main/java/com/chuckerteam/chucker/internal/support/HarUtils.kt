package com.chuckerteam.chucker.internal.support

import androidx.annotation.VisibleForTesting
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.Har
import com.chuckerteam.chucker.internal.data.har.Log
import com.chuckerteam.chucker.internal.data.har.log.Creator
import com.chuckerteam.chucker.internal.data.har.log.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// http://www.softwareishard.com/blog/har-12-spec/
// https://github.com/ahmadnassri/har-spec/blob/master/versions/1.2.md
internal object HarUtils {
    suspend fun harStringFromTransactions(
        transactions: List<HttpTransaction>
    ): String = withContext(Dispatchers.Default) {
        JsonConverter.nonNullSerializerInstance.toJson(fromHttpTransactions(transactions))
    }

    @VisibleForTesting
    fun fromHttpTransactions(transactions: List<HttpTransaction>): Har {
        return Har(
            log = Log(
                creator = Creator(
                    name = "BuildConfig.LIBRARY_PACKAGE_NAME",
                    version = "BuildConfig.VERSION_NAME"
                ),
                browser = null,
                pages = null,
                entries = transactions.map(Entry.Companion::fromHttpTransaction).filter { it.response != null }
            )
        )
    }
}
