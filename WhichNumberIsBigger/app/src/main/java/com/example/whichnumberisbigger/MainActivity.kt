package com.example.whichnumberisbigger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var game = BiggerNumberGame(0,1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game.generateNumbers()
        button_main_left.text = game.leftNumber.toString()
        button_main_right.text = game.rightNumber.toString()
        textView_main_score.text = "0"
        textView_main_streak.text = "Streak: 0"
    }

    fun onLeftButtonClick(view: View){
        game.checkAns("left")
        updateAll()
    }

    fun onRightButtonClick(view: View){
        game.checkAns("right")
        updateAll()
    }

    fun updateAll(){
        button_main_left.text = game.leftNumber.toString()
        button_main_right.text = game.rightNumber.toString()
        textView_main_score.text = game.score.toString()
        textView_main_streak.text = "Streak: " + game.streak.toString()
        happyMessage()
    }

    fun happyMessage(){
        if (game.correct == true) Toast.makeText(this, "noice", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "bruv", Toast.LENGTH_SHORT).show()
    }

}

