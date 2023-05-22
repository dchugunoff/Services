package com.chugunov.services

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyJobService: JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    override fun onStartJob(params: JobParameters?): Boolean {
        coroutineScope.launch {
            log("onStartCommand")
            for (i in 0 until 100) {
                delay(1000)
                log("Timer: $i")
            }
            jobFinished(params, true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        log("onStopJob")
        return true
    }

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        coroutineScope.cancel()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyJobService: $message")
    }

    companion object {
        const val JOB_ID = 123
    }
}