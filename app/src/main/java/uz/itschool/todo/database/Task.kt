package uz.itschool.todo.database

// TODO: Change for data class and edit to use for database
data class Task(
    val id: Int = 0,
    val day: Int,
    val month : Int,
    val year : Int,
    val text : String,
    val imageUrl :String,
    var state : Boolean = false

)