package ru.btpit.nmedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun repos()
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        header = "ГПБОУ ВО БТПИТ",
        content = "15 февраля на базе 1 и 3 корпусов ГБПОУ ВО «БТПИТ» прошли торжественные митинги, посвященные 35-й годовщине со дня вывода советских войск из Республики Афганистан с поднятием государственного флага и возложением цветов к «Деревьям Памяти».\\nКаштаны были посажены на территории учебного корпуса № 1 и 3 в честь воинов-интернационалистов, которые учились в нашем техникуме.\\nСтуденты почтили память участников войн и конфликтов минутой молчания.",
        dataTime = "21 февраля в 19:12",
        isLike = false,
        amountlike = 999,
        amountrepost = 15,
        isRepos = false
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {

            if (post.isLike)
                post.amountlike--
            else
                post.amountlike++
            post.isLike = !post.isLike
        data.value = post
    }
    override fun repos() {

        if (post.isRepos)
            post.amountrepost--
        else
            post.amountrepost++
        post.isRepos = !post.isRepos
        data.value = post
    }


}
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun repos()= repository.repos()
}