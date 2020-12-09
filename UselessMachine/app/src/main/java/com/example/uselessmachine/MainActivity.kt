package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selfDestructActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            Toast.makeText(this, "Switch is ${if(isChecked) "ON" else "OFF"}", Toast.LENGTH_SHORT).show()

            if (isChecked){
                var randomInterval = (0..10000).random()

                val uncheckTimer = object: CountDownTimer(randomInterval.toLong(), 10){
                    override fun onTick(millisUntilFinished: Long) {
                        if (!switch_main_useless.isChecked) {
                            cancel()
                            layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                        }
                    }
                    override fun onFinish() {
                        switch_main_useless.isChecked = false
                        layout_main.setBackgroundColor(Color.rgb((0..255).random(),(0..255).random(),(0..255).random()))
                    }

                }
                uncheckTimer.start()
            }
        }

        button_main_self_destruct.setOnClickListener {
            var isColor = false
            var lastTime: Long = 11000
            Toast.makeText(this, "Self Destruct sequence initiated. Current session will crash in 10 seconds.", Toast.LENGTH_SHORT).show()
            if (!selfDestructActive) {
                val destructTimer = object : CountDownTimer(11000, 1) {
                    override fun onTick(millisUntilFinished: Long) {
                        button_main_self_destruct.text = (millisUntilFinished / 1000).toInt().toString()

                        if (millisUntilFinished <= lastTime){
                            if(isColor){
                                layout_main.setBackgroundColor(Color.rgb(255,255,255))
                                isColor = false
                            } else {
                                if (millisUntilFinished <= 1000) {
                                    layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                                    isColor = true
                                } else {
                                    layout_main.setBackgroundColor(Color.rgb(0, 30, 60))
                                    isColor = true
                                }
                            }
                            lastTime = millisUntilFinished - (((millisUntilFinished + 1) / 11))
                        }
                    }
                    override fun onFinish() {
                        finish()
                    }
                }
                destructTimer.start()
                selfDestructActive = true
            }
        }

        button_main_look_busy.setOnClickListener {
            var progress = 0

            textView_main_progressBarPercent.text = "0%"
            progressBar_main_look_busy.progress = 0
            switch_main_useless.visibility = View.GONE
            button_main_look_busy.visibility = View.GONE
            button_main_self_destruct.visibility = View.GONE
            progressBar_main_look_busy.visibility = View.VISIBLE
            textView_main_progressBarPercent.visibility = View.VISIBLE

            var lastTime:Long = 29000
            var informationalMessage = ""
            val progressTimer = object: CountDownTimer(29000, 10){

                override fun onTick(millisUntilFinished: Long) {
                    if(millisUntilFinished <= lastTime){
                        progress++

                        when(progress / 10){
                            9 -> lastTime -= 1000
                            10 -> lastTime -= 1000
                            else -> lastTime -= 100
                        }
                    }

                    when(progress / 10){ // Progress Completed in 10s
                        0 -> informationalMessage = "Preparing to download..."
                        1 -> informationalMessage = "Starting up queue..."
                        2 -> informationalMessage = "Uploading device statistics..."
                        3 -> informationalMessage = "Downloading recent software fixes..."
                        4 -> informationalMessage = "Downloading the recent memes..."
                        5 -> informationalMessage = "Downloading MICROSOFT FLIGHT SIMULATOR 2020"
                        6 -> informationalMessage = "red seems sus..."
                        7 -> informationalMessage = "LMG MOUNTED AND READDYYYYYY..."
                        8 -> informationalMessage = "50... 40... 30... 20... 10... Butter..."
                        9 -> informationalMessage = "Downloading more unspecified and unnecessary things."
                        10 -> informationalMessage = "u w0t m9"
                    }

                    textView_main_progressBarPercent.text = "$progress% $informationalMessage"
                    progressBar_main_look_busy.progress = progress
                }

                override fun onFinish() {
                    textView_main_progressBarPercent.text = "Task Completed"
                    var delayTimer = object:CountDownTimer(1000,1000){
                        override fun onTick(p0: Long) {
                        }

                        override fun onFinish() {
                            switch_main_useless.visibility = View.VISIBLE
                            button_main_look_busy.visibility = View.VISIBLE
                            button_main_self_destruct.visibility = View.VISIBLE
                            progressBar_main_look_busy.visibility = View.GONE
                            textView_main_progressBarPercent.visibility = View.GONE
                        }

                    }
                    delayTimer.start()
                }
            }
            progressTimer.start()
        }

    }
}