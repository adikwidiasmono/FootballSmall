package com.small.main.util

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { UI }
    open val io: CoroutineContext by lazy { CommonPool }
}