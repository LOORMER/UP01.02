package ru.btpit.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.btpit.nmedia.databinding.PostCardBinding
import android.view.View

typealias OnLikeListner = (post : Post) -> Unit
typealias OnRemoveListner = (post : Post) -> Unit

class PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
class PostViewHolder(
    private val binding: PostCardBinding,
)  : RecyclerView.ViewHolder(binding.root) {
    fun  bind(post: Post,listner: PostAdapter.Listner) {

        binding.apply {
            textViewHeader.text = post.header
            textViewContent.text = post.content
            textViewDateTime.text = post.dataTime
            textViewLike.text = post.amountlike.toString()
            textViewRepost.text = post.amountrepost.toString()
            textViewSee.text = post.amountviews.toString()
            imagebutnlike.setImageResource(
                if (post.isLike) R.drawable.redlikes else R.drawable.likes
            )
            imagebutnlike.setOnClickListener{
                listner.onClickLike(post)
            }
            imagebutntochki.setOnClickListener{
                listner.onClickMore(post,it,binding)
            }
            imagebutnRepost.setOnClickListener{
                listner.onClickShare(post)
            }

        }
    }

}

class PostAdapter (private val listner: Listner): ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post, listner)
    }

    interface Listner {
        fun onClickLike(post: Post)
        fun onClickShare(post: Post)
        fun onClickMore(post: Post, view: View, binding: PostCardBinding)
        fun cancelEditPost(post: Post, binding: PostCardBinding)
        fun saveEditPost(post: Post, binding: PostCardBinding)
        fun editModeOn(binding: PostCardBinding, content: String)
    }

    private fun convertToString(count: Int): String {
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