package com.example.focan

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TimerActivity : AppCompatActivity() {

    lateinit var textCounter  : TextView
    lateinit var dotOne       : ImageView
    lateinit var dotTwo       : ImageView
    lateinit var dotThree     : ImageView
    lateinit var buttonStart  : AppCompatButton
    lateinit var buttonPause  : AppCompatButton
    lateinit var buttonResete : AppCompatButton

    private var totalTimerMilliseconds = 25 * 60 * 1000L
    private var counter : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer)
        startComponents()
        buttonStart.setOnClickListener{ funcionCounter() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startComponents() {

        textCounter  = findViewById(R.id.textCounter)
        dotOne       = findViewById(R.id.dotOne)
        dotTwo       = findViewById(R.id.dotTwo)
        dotThree     = findViewById(R.id.dotThree)
        buttonStart  = findViewById(R.id.buttonStart)
        buttonPause  = findViewById(R.id.buttonPause)
        buttonResete = findViewById(R.id.buttonResete)

    }

    private fun funcionCounter() {

        counter?.cancel()

        counter = object : CountDownTimer( totalTimerMilliseconds , 1000 ) {

            override fun onTick(millisUntilFinished: Long) {

                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60

                val formattedTime = String.format( "%02d:%02d" , minutes, seconds )

                textCounter.text = formattedTime

            }

            override fun onFinish() {

                textCounter.text = "00:00"

            }

        }

        counter?.start()


    }

}