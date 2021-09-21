package com.chuckerteam.chucker.internal.data.har

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction
import com.google.gson.annotations.SerializedName

internal data class Har(
    @SerializedName("log") val log: Log
) {
    constructor(transactions: List<HttpTransaction>) : this(
        log = Log(transactions)
    )
}
