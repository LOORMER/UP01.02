package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.btpit.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var present_value_int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var count = true
        val Post = Post(
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
            textViewDateTime.text = Post.dataTime
            textViewLike.text = toStringFromNumb(Post.amountlike)
            textViewRepost.text = toStringFromNumb(Post.amountrepost)


            imagebutn.setOnClickListener {
                if (count) {
                    imagebutn.setBackgroundResource(R.drawable.redlikes)
                } else {
                    imagebutn.setBackgroundResource(R.drawable.likes)
                }
                count = count.not()
            }
        }

        }
    }

    fun toStringFromNumb(count: Int): String
    {
        return when(count)
        {
            in 0 .. 999 -> count.toString()
            in 1000 .. 1000000 -> ((count/100).toFloat()/10).toString() + "К"
            in 1000000 .. 1000000000 -> count.toString()
            else -> "Более млрд"
        }
    }
}