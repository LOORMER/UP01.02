package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    var present_value_int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var imgbutt = findViewById<ImageButton>(R.id.imagebutn)
        var text12 = findViewById<TextView>(R.id.text1)
        var count = true
        imgbutt.setOnClickListener {
            if (count) {
                text12.setText("1")
                imgbutt.setBackgroundResource(R.drawable.redlikes)
            } else {
                text12.setText("0")
                imgbutt.setBackgroundResource(R.drawable.likes)
            }
            count = count.not()
        }
        var text11 = findViewById<TextView>(R.id.text2)
        var imagebutton2 = findViewById<ImageButton>(R.id.imagebutn2)
        imagebutton2.setOnClickListener {
            if (count) {
                text11.setText("1")
            } else {
                text11.setText("0")
            }
            count = count.not()
        }
    }

    fun likecount(count: Int): String
    {
        return when(count)
        {
            in 0 .. 999 -> count.toString()
            in 1000 .. 1000000 -> count.toString()
            in 1000000 .. 1000000000 -> count.toString()
            else -> "Более млрд"
        }
    }
}