package com.example.buttonclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private var timesClicked = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickMeClick(view : View){
        timesClicked++
        button.setText(timesClicked.toString())

        if (timesClicked == 10){
            Toast.makeText(this, "Way to go! 10 is a big number!", Toast.LENGTH_LONG).show()
        } else if (timesClicked == 50){
            Toast.makeText(this, "50! Amazing!", Toast.LENGTH_LONG).show()
        } else if(timesClicked == 100){
            Toast.makeText(this, "Are you still here? After 100? Go do something else.", Toast.LENGTH_LONG).show()
        }

    }
}