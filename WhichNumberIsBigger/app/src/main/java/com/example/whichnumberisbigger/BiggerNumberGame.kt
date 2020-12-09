package com.example.whichnumberisbigger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class BiggerNumberGame(val lowerLimit: Int, val upperLimit: Int){

    //wow i made the logic much more simple yaaaaaaayyyyyyyy

    var score = 0
    var leftNumber = 0
    var rightNumber = 0
    var streak = 0

    var correct = true;

    fun checkAns(whichClick: String){
        if (leftNumber > rightNumber){
            if (whichClick == "left") {
                correct = true
                score++
                streak++

            } else {
                correct = false
                score--
                streak = 0
            }
        } else {
            if (whichClick == "right"){
                correct = true
                score++
                streak++
            } else {
                correct = false
                score--
                streak = 0
            }

        }
        generateNumbers()
    }

    fun generateNumbers(){
        /*
        Generates left number, then continuously randomizes right number until the right number
        is not the same as the left number.
         */
        leftNumber  = (Math.random() * (upperLimit + 1 - lowerLimit) + lowerLimit).toInt()
        rightNumber = leftNumber
        while (leftNumber == rightNumber) {
            rightNumber = (Math.random() * (upperLimit + 1 - lowerLimit) + lowerLimit).toInt()
        }

    }
}