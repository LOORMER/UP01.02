package ru.btpit.nmedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Calendar
import kotlin.random.Random

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun likeById(id:Int)
    fun repos(id: Int)
    fun removeById(id: Int)
    fun ShareById(id:Int)
    fun addPost(post : Post, String:String)
}
class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 1,
            header = "ГПБОУ ВО БТПИТ",
            content = "15 февраля на базе 1 и 3 корпусов ГБПОУ ВО «БТПИТ» прошли торжественные митинги, посвященные 35-й годовщине со дня вывода советских войск из Республики Афганистан с поднятием государственного флага и возложением цветов к «Деревьям Памяти».\\nКаштаны были посажены на территории учебного корпуса № 1 и 3 в честь воинов-интернационалистов, которые учились в нашем техникуме.\\nСтуденты почтили память участников войн и конфликтов минутой молчания.",
            dataTime = "21 февраля в 19:12",
            isLike = false,
            amountlike = 999,
            amountviews = 0,
            amountrepost = 15,
            isRepos = false
        ),
        Post(
            id = 2,
            header = "ГПБОУ ВО БТПИТ",
            content = "Преподаватель Борисоглебского техникума промышленных и информационных технологий Гребенникова Лариса Владимировна одно из занятий по дисциплине «Краеведение» со студентами 1 курсов специальностей «Дошкольное образование» и «Коррекционная педагогика в начальном образовании» провела в МБУК БГО Борисоглебском историко-художественном музее.",
            dataTime = "27 Февраля в 12:56",
            isLike = false,
            amountlike = 0,
            amountviews =0,
            amountrepost = 0,
            isRepos = false,

            ),

        )

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data
    override fun likeById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else {
                if (it.isLike)
                    it.amountlike--
                else
                    it.amountlike++
                it.copy(isLike = !it.isLike)
            }
        }
        data.value = posts
    }
    override fun removeById(id: Int){
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun ShareById(id: Int) {
        posts = posts.map {
            if(it.id != id)
                it
            else
                it.copy(amountrepost = it.amountrepost + 1)
        }
        data.value = posts

    }



     override fun addPost(post: Post,String: String) {
         posts = listOf(
             post.copy(
                 id = 0,
                 dataTime = "dlsa",
                 content = " ",
                 amountlike = randomNumb(),
                 amountrepost = randomNumb(),
                 amountviews = randomNumb(),
                 isLike = false
             )
         ) + post
         data.value = posts
     }



    override fun repos(id: Int) {

        posts = posts.map {
            if (it.id != id) it else {
                it.copy(amountrepost = it.amountrepost + 10)
            }

        }
        data.value = posts
    }


}


class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun likeById(id: Int) = repository.likeById(id)
    fun repos(id: Int) = repository.repos(id)
    fun removeById(id: Int) = repository.removeById(id)
    fun ShareById(id:Int) = repository.ShareById(id)
    //private val edited = MutableLiveData(getEmptyPost())
    /*fun addPost(String: String){
        edited.val
   */ }


fun randomNumb() :Int
{
    return when(Random.nextInt(1,3))
    {
        1 -> Random.nextInt(0,1_000)
        2 -> Random.nextInt(1_000,1_000_000)
        3 -> Random.nextInt(1_000_000,1_000_000_000)
        else ->Random.nextInt(0,Int.MAX_VALUE)
    }
}
