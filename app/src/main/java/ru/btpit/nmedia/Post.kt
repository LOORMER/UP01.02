package ru.btpit.nmedia

class Post (
    val id:Long,
    val header:String,
    val content: String,
    val dataTime:String,
    var amountlike:Int,
    var amountrepost:Int,
    var isLike:Boolean,
    var isRepos:Boolean
)


