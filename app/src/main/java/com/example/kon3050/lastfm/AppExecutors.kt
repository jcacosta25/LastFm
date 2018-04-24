/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kon3050.lastfm

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

const val THREAD_COUNT = 3

/**
 * Global executor pools for the whole application.
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
open class AppExecutors @Inject constructor() {

    val diskIO: Executor
    val networkIO: Executor
    val mainThread: Executor

    init {
        diskIO = DiskIOThreadExecutor()
        networkIO = Executors.newFixedThreadPool(THREAD_COUNT)
        mainThread = MainThreadExecutor()
    }
}


class MainThreadExecutor @Inject constructor() : Executor {

//    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        Handler(Looper.getMainLooper()).post(command)
    }
}

class DiskIOThreadExecutor @Inject constructor() : Executor {

//    private val diskIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {
        Executors.newSingleThreadExecutor().execute(command)
    }
}