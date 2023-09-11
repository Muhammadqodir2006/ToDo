package uz.itschool.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var day: Int,
    var month : Int,
    var year : Int,
    var text : String,
    @ColumnInfo(name = "image_url") var imageUrl :String,
    var state : Int = 0

)