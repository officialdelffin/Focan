package com.example.focan

// Imposts :
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

    // Defining element types :
    lateinit var textCounter  : TextView
    lateinit var dotOne       : ImageView
    lateinit var dotTwo       : ImageView
    lateinit var dotThree     : ImageView
    lateinit var buttonStart  : AppCompatButton
    lateinit var buttonPause  : AppCompatButton
    lateinit var buttonResete : AppCompatButton

    private var totalTimerMillisecondsStudy = 2 * 60 * 1000L               // 25 minutes in milliseconds
    private var totalTimerMillisecondsInterval = 1 * 60 * 1000L             // 5 minutes in milliseconds
    private var counterStudy: CountDownTimer? = null                        // Creating a stopwatch for study
    private var counterInterval: CountDownTimer ? = null                    // Creating a stopwatch for interval
    private var timeLeftMillisStudy = totalTimerMillisecondsStudy           // Amarzening the time left
    private var timeLeftMillisInterval = totalTimerMillisecondsInterval     // Amarzening the time left
    private var isStudyTime = true                                          // The stopwatch is in study mode
    private var isRunning = false                                           // The stopwatch is not running


    override fun onCreate( savedInstanceState: Bundle? ) {

        super.onCreate( savedInstanceState )
        enableEdgeToEdge()
        setContentView( R.layout.activity_timer )
        startComponents()

        // Defining button clicks :
        buttonStart.setOnClickListener  { if ( !isRunning ) { funcionCheckRunning() } }
        buttonPause.setOnClickListener  { funcionPause() }
        buttonResete.setOnClickListener { checkResete() }

        ViewCompat.setOnApplyWindowInsetsListener( findViewById( R.id.main ) ) { v, insets ->
            val systemBars = insets.getInsets( WindowInsetsCompat.Type.systemBars() )
            v.setPadding( systemBars.left, systemBars.top, systemBars.right, systemBars.bottom )
            insets
        }
    }

    private fun startComponents() {

        // Starting elements :
        textCounter  = findViewById(R.id.textCounter)
        dotOne       = findViewById(R.id.dotOne)
        dotTwo       = findViewById(R.id.dotTwo)
        dotThree     = findViewById(R.id.dotThree)
        buttonStart  = findViewById(R.id.buttonStart)
        buttonPause  = findViewById(R.id.buttonPause)
        buttonResete = findViewById(R.id.buttonResete)

    }

    private fun funcionCheckRunning () {

        if ( !isRunning ) {

            if ( isStudyTime ) {

                funcionCounterStudy()

            }

            else {

                funcionCounterInterval()

            }

            isRunning = true

        }

    }

    private fun checkResete() {

        if ( isStudyTime ) { funcionReseteStudy() }
        else { funcionReseteInterval() }

    }

    private fun funcionCounterStudy() {

        // Canceling if the stopwatch is running :
        counterStudy?.cancel()

        // Creating the object counter and defining the time and the time interval :
        counterStudy = object : CountDownTimer( timeLeftMillisStudy , 1000 ) {

            // Updating every second :
            override fun onTick( millisUntilFinished : Long ) {

                timeLeftMillisStudy = millisUntilFinished

                val minutes = ( millisUntilFinished / 1000 ) / 60
                val seconds = ( millisUntilFinished / 1000 ) % 60

                val formattedTime = String.format( "%02d:%02d" , minutes , seconds )

                textCounter.text = formattedTime

            }

            override fun onFinish() {

                textCounter.text = "00:00"
                isStudyTime = false
                isRunning = false
                funcionCounterInterval()

            }

        }

        counterStudy?.start() // Starting the stopwatch

    }

    private fun funcionCounterInterval() {

        counterInterval?.cancel()

        counterInterval = object : CountDownTimer( totalTimerMillisecondsInterval , 1000 ) {

            override fun onTick(millisUntilFinished: Long) {

                timeLeftMillisInterval = millisUntilFinished

                var minutes = (millisUntilFinished / 1000) / 60
                var seconds = (millisUntilFinished / 1000) % 60

                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                textCounter.text = formattedTime

            }

            override fun onFinish() {

                textCounter.text = "00:00"
                isStudyTime = true
                isRunning = false
                funcionCounterStudy()

            }

        }

        counterInterval?.start()

    }

    private fun funcionPause() {

        counterStudy?.cancel()     // Canceling the stopwatch
        counterInterval?.cancel()  // Canceling the stopwatch
        isRunning = false          // The stopwatch is not running

    }

    private fun funcionReseteStudy() {

        counterStudy?.cancel()                              // Canceling the stopwatch
        timeLeftMillisStudy = totalTimerMillisecondsStudy   // Resetting the time
        isRunning = false                                   // The stopwatch is not running

        val minutes = (timeLeftMillisStudy / 1000) / 60
        val seconds = (timeLeftMillisStudy / 1000) % 60
        val formattedTime = String.format("%02d:%02d", minutes, seconds)

        textCounter.text = formattedTime

    }

    private fun funcionReseteInterval() {

        counterInterval?.cancel()
        timeLeftMillisInterval = totalTimerMillisecondsInterval
        isRunning = false

        val minutes = ( timeLeftMillisInterval / 1000 ) / 60
        val seconds = ( timeLeftMillisInterval / 1000 ) % 60

        val formattedTime = String.format( "%02d:%02d" , minutes , seconds )

        textCounter.text = formattedTime

    }

}