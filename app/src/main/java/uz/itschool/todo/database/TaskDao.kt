package uz.itschool.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.time.Month

@Dao
interface TaskDao {

    @Query("select * from tasks where day = :day AND month = :month AND year = :year")
    fun getTasks(day :Int , month: Int, year:Int ): List<Task>

    @Query("select * from tasks where state = 0 AND day = :day AND month = :month AND year = :year")
    fun getUndone(day :Int , month: Int, year:Int): List<Task>

    @Insert
    fun addTask(newTask:Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateState(task: Task)
}