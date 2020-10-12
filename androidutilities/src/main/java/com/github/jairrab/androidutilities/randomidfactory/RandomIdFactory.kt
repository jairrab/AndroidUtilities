/*
 * Copyright (C) 2020 - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Antonio Barria <jaybarria@gmail.com>
 * Last modified 7/5/20 7:34 AM
 */

/* Created 4/19/2020 9:26 AM */

package com.github.jairrab.androidutilities.randomidfactory

import java.security.SecureRandom
import java.util.*
import kotlin.experimental.and

object RandomIdFactory {
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun getString(length: Int = 9): String {
        val random = SecureRandom()
        val bytes = ByteArray(length)
        random.nextBytes(bytes)
        return (bytes.indices)
            .map { charPool[(bytes[it] and 0xFF.toByte() and (charPool.size - 1).toByte()).toInt()] }
            .joinToString("")
    }

    fun getUuid(): String {
        return UUID.randomUUID().toString()
    }
}