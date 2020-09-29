/*
 * Copyright (C) 2020 - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Antonio Barria <jaybarria@gmail.com>
 * Last modified 8/19/20 10:36 AM
 */

/* Created circa 2015-2019 */

package com.github.jairrab.androidutilities.timberutil

import timber.log.Timber

class DebugTreeMod(private val prefixTag: String) : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "$prefixTag$tag", message, t)
    }

    companion object {
        const val T0 = "~~~~"
        const val T1 = "!!!!"
        const val T2 = "@@@@"
        const val T3 = "####"
        const val T4 = "$$$$"
        const val T5 = "%%%%"
        const val T6 = "^^^^"
        const val T7 = "&&&&"
        const val T8 = "****"
        const val T9 = "~!~!"
    }
}