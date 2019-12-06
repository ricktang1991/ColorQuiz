package com.example.colorquiz

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    // Random Color
    lateinit var answer: ColorEnum
    lateinit var leftChoice: ColorEnum
    lateinit var rightChioce: ColorEnum

    // Components
    lateinit var scoreLabel: TextView
    lateinit var colorAnswer: TextView
    lateinit var colorLeftIV: ImageButton
    lateinit var colorRightIV: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreLabel = findViewById<TextView>(R.id.scoreValue)
        colorAnswer = findViewById<TextView>(R.id.colorAnswer)
        colorLeftIV = findViewById<ImageButton>(R.id.colorLeft)
        colorRightIV = findViewById<ImageButton>(R.id.colorRight)

        randomColor()
    }

    fun randomColor() {
        val rndA = Random().nextInt(ColorEnum.values().size)
        val rndL = Random().nextInt(ColorEnum.values().size)
        val rndR = Random().nextInt(ColorEnum.values().size)
        val isLeft = if ((0..1).random() == 1) true else false
        answer = ColorEnum.values()[rndA]

        leftChoice = ColorEnum.values()[if(isLeft) rndA else rndL]
        rightChioce = ColorEnum.values()[if(!isLeft) rndA else rndR]

        while (leftChoice == rightChioce) {
            leftChoice = ColorEnum.values()[if(isLeft) rndA else rndL]
            rightChioce = ColorEnum.values()[if(!isLeft) rndA else rndR]
        }

        colorAnswer.text = answer.colorName
        colorLeftIV.setBackgroundColor(leftChoice.colorCode)
        colorRightIV.setBackgroundColor(rightChioce.colorCode)
    }


    fun leftClick(view: View) {
        checkAnswer(view)
        randomColor()
    }

    fun rightClick(view: View) {
        checkAnswer(view)
        randomColor()
    }

    fun checkAnswer(cliked: View) {
        var clickedColor = (cliked.background as ColorDrawable).color
        val answerColor = answer.colorCode

        val cur = scoreLabel.text.toString().toInt()
        if (clickedColor == answerColor) {
            scoreLabel.text = (cur + 1).toString()
            Toast.makeText(this, "Right!!", Toast.LENGTH_SHORT).show()
        } else {
            scoreLabel.text = (cur - 1).toString()
            Toast.makeText(this, "Wrong!!", Toast.LENGTH_SHORT).show()
        }
    }
}

enum class ColorEnum(val colorName: String, val colorCode: Int) {
    RED("Red", Color.RED),
    BLACK("Black", Color.BLACK),
    CYAN( "Cyan", Color.CYAN),
    BLUE( "Blue", Color.BLUE),
    YELLOW( "Yellow", Color.YELLOW),
    DKGRAY( "Dkgra", Color.DKGRAY),
    MAGENTA( "Magenta", Color.MAGENTA),
    LTGRAY( "Ltgray", Color.LTGRAY),
    GREEN( "Green", Color.GREEN)
}
