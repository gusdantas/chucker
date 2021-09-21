package com.chuckerteam.chucker.internal.support

import androidx.annotation.VisibleForTesting
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.chuckerteam.chucker.internal.data.har.Har
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
        return Har(transactions)
    }
}
