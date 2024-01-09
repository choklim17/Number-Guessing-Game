package com.example.numberguessinggame

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme

private const val MAX_ROUNDS = 10

class MainActivity : ComponentActivity() {

    private lateinit var backgroundView: RelativeLayout

    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var newGameButton: Button

    private var score: Int = 0
    private var roundsPlayed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Layout
        backgroundView = findViewById(R.id.backgroundView)

        // Initialize Buttons
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        newGameButton = findViewById(R.id.newGameButton)

        // Start the game
        newGameButton.setOnClickListener {
            startGame()
        }

    }

    private fun startGame() {
        // Enable Buttons
        leftButton.isEnabled = true;
        rightButton.isEnabled = true;

        // Reset score and rounds played
        score = 0
        roundsPlayed = 0

        backgroundView.setBackgroundColor(Color.WHITE)

        assignNumbersToButtons()

        leftButton.setOnClickListener {
            checkHigherNumber(true)
            assignNumbersToButtons()
        }

        rightButton.setOnClickListener {
            checkHigherNumber(false)
            assignNumbersToButtons()
        }
    }

    private fun checkHigherNumber(isLeftSelected: Boolean) {
        val leftNumber = leftButton.text.toString().toInt()
        val rightNumber = rightButton.text.toString().toInt()
        val isAnswerCorrect = if (isLeftSelected) leftNumber > rightNumber else rightNumber > leftNumber

        if (isAnswerCorrect) {
            backgroundView.setBackgroundColor(Color.CYAN)
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            score++
        }
        else {
            backgroundView.setBackgroundColor(Color.RED)
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        roundsPlayed++

        if (roundsPlayed >= MAX_ROUNDS) {
            Toast.makeText(this, "Game Over! Your score is $score", Toast.LENGTH_LONG).show()
            leftButton.isEnabled = false
            rightButton.isEnabled = false
        }
    }

    private fun assignNumbersToButtons() {
        val leftNumber = (0..10).random()
        var rightNumber = leftNumber

        while (rightNumber == leftNumber) {
            rightNumber = (0..10).random()
        }

        leftButton.text = leftNumber.toString()
        rightButton.text = rightNumber.toString()
    }

}
