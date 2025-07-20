package com.example.focan

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer)
        startComponents()

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
}