package com.moneysync.utils

import android.net.Uri
import android.util.Base64

object ExportLinkBuilder {
    private const val SCHEME = "moneysync"
    private const val HOST = "import"

    fun buildLink(encryptedPayload: ByteArray): String {
        val encoded = Base64.encodeToString(encryptedPayload, Base64.URL_SAFE or Base64.NO_WRAP)
        return Uri.Builder()
            .scheme(SCHEME)
            .authority(HOST)
            .appendQueryParameter("data", encoded)
            .build()
            .toString()
    }
}
