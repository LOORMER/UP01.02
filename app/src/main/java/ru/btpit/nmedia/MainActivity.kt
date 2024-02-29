package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.databinding.PostCardBinding

class MainActivity : AppCompatActivity(),PostAdapter.Listner {
     val viewModel: PostViewModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostAdapter(this)
        binding.list.adapter = adapter
        viewModel.data.observe(this) { post ->
            adapter.submitList(post)


        }

        fun toStringFromNumb(count: Int): String {
            return when (count) {
                in 0..<1_000 -> count.toString()
                in 1000..<1_100 -> "1K"
                in 1_100..<10_000 -> ((count / 100).toFloat() / 10).toString() + "K"
                in 10_000..<1_000_000 -> (count / 1_000).toString() + "K"
                in 1_000_000..<1_100_000 -> "1M"
                in 1_100_000..<10_000_000 -> ((count / 100_000).toFloat() / 10).toString() + "M"
                in 10_000_000..<1_000_000_000 -> (count / 1_000_000).toString() + "M"
                else -> "ꚙ"
            }
        }
    }


    override fun onClickLike(post: Post) {
        viewModel.likeById(post.id)

    }

    override fun onClickShare(post: Post) {
        viewModel.ShareById(post.id)

    }

    override fun onClickMore(post: Post, view: View, binding: PostCardBinding) {
        val popupMenu = PopupMenu(this,view)
        popupMenu.inflate(R.menu.post_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId)
            {
                R.id.remove -> viewModel.removeById(post.id)
                //R.id.add -> viewModel.addPo
            }
            true
        }
        popupMenu.show()

    }

    override fun cancelEditPost(post: Post, binding: PostCardBinding) {

    }

    override fun saveEditPost(post: Post, binding: PostCardBinding) {

    }

    override fun editModeOn(binding: PostCardBinding, content: String) {

    }
}