package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var timeElapsed = 0L
    private var startPoint = 0L

    private val KEY_TIME_ELAPSED = "elapsed time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeElapsed = savedInstanceState?.getLong(KEY_TIME_ELAPSED) ?: 0L
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")

        button_main_start.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime() - timeElapsed
            chronometer_main_timer.start()
            button_main_start.isEnabled = false
            button_main_stop.isEnabled = true
        }

        button_main_stop.setOnClickListener {
            timeElapsed = SystemClock.elapsedRealtime() - chronometer_main_timer.base
            chronometer_main_timer.stop()
            button_main_start.isEnabled = true
            button_main_stop.isEnabled = false
        }

        button_main_reset.setOnClickListener {
            chronometer_main_timer.base = SystemClock.elapsedRealtime()
            chronometer_main_timer.stop()
            timeElapsed = 0L
            button_main_start.isEnabled = true
            button_main_stop.isEnabled = false
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState called")
        outState.putLong(KEY_TIME_ELAPSED, timeElapsed)
    }
}