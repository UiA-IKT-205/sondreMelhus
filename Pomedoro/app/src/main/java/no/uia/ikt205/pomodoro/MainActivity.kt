package no.uia.ikt205.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import no.uia.ikt205.pomodoro.util.millisecondsToDescriptiveTime
import no.uia.ikt205.pomodoro.util.minutesToMilliSeconds

class MainActivity : AppCompatActivity() {

    lateinit var timer:CountDownTimer
    lateinit var startButton:Button
    lateinit var countdownDisplay:TextView

    var timeToCountDownInMs = 5000L
    val timeTicks = 1000L
    private val buttonTimeIncrement = 30L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       startButton = findViewById<Button>(R.id.startCountdownButton)
       startButton.setOnClickListener(){
           startCountDown(it)
       }
       countdownDisplay = findViewById<TextView>(R.id.countDownView)

        val setTimerDurationButtons = listOf<Button>(findViewById<Button>(R.id.setTimerDuration30MinutesButton),
                findViewById<Button>(R.id.setTimerDuration60MinutesButton),
                findViewById<Button>(R.id.setTimerDuration90MinutesButton),
                findViewById<Button>(R.id.setTimerDuration120MinutesButton)
        )

        setTimerDurationButtons.forEachIndexed(){ index, button ->
            button.setOnClickListener(){
                val newCountdownTime = minutesToMilliSeconds((index+1) * buttonTimeIncrement)
                setCountdownTime(newCountdownTime)

            }
        }
    }

    private fun setCountdownTime(newCountdownTimeInMs:Long){
        timeToCountDownInMs =  newCountdownTimeInMs
        updateCountDownDisplay(timeToCountDownInMs)
    }

    private fun startCountDown(viewOfTimer: View){

        timer = object : CountDownTimer(timeToCountDownInMs,timeTicks) {
            override fun onFinish() {
                viewOfTimer.isEnabled = true
                Toast.makeText(this@MainActivity,"Arbeidsøkt er ferdig", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
               updateCountDownDisplay(millisUntilFinished)
            }
        }
        viewOfTimer.isEnabled = false
        timer.start()
    }

    fun updateCountDownDisplay(timeInMs:Long){
        countdownDisplay.text = millisecondsToDescriptiveTime(timeInMs)
    }

}