package ru.btpit.nmedia

class post (
     val id:Long,
     val header:String,
     val content: String,
     val datatime:String,
     var like:Int,
     var repost:Int,
     var isLike:Boolean = false
 )


