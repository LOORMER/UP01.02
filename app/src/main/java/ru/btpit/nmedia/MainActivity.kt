package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import ru.btpit.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    var present_value_int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var count = true
        val Post = post(
            0,
            "ГБПОУ ВО &quot;БТПИТ&quot;",
            "✅ Подать заявление на участие в дистанционном электронном голосовании до 11 марта 2024 года✅ Выбрать удобный избирательный участок для голосования и подать заявление до 11 марта 2024 года✅ Узнайте о возможностях для избирателей на «Госуслугах»",
            "20 февраля в 17:18",
            0,
            0,
            false



        )
        with(binding)
        {
            textViewHeader.text = Post.header
            textViewContent.text = Post.content
            textViewDateTime.text = Post.datatime
            textViewLike.text = Post.like.toString()
            textViewRepost.text = Post.repost.toString()


            imagebutn.setOnClickListener {
                if (count) {
                    textViewLike.setText("1")
                    imagebutn.setBackgroundResource(R.drawable.redlikes)
                } else {
                    textViewLike.setText("0")
                    imagebutn.setBackgroundResource(R.drawable.likes)
                }
                count = count.not()
            }
        }



        var text11 = findViewById<TextView>(R.id.textViewRepost)
        var imagebutton2 = findViewById<ImageButton>(R.id.imagebutn2)
        imagebutton2.setOnClickListener {
            present_value_int +=1;
            text11.setText(likecount(present_value_int));
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