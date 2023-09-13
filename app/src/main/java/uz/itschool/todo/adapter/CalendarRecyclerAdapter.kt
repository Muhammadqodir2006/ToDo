package uz.itschool.todo.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.itschool.todo.R
import uz.itschool.todo.database.AppDatabase
import uz.itschool.todo.database.Task
import java.time.LocalDate

class CalendarRecyclerAdapter(val tasks:ArrayList<Task>, val appDatabase: AppDatabase, val context: Context, val calendarRecyclerInterface: CalendarRecyclerInterface) : RecyclerView.Adapter<CalendarRecyclerAdapter.MyHolder>(){
    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val texT = itemView.findViewById<TextView>(R.id.calendar_item_text)
        val cardView = itemView.findViewById<CardView>(R.id.calendar_item_parent)
        val deleteBTN = itemView.findViewById<FloatingActionButton>(R.id.calendar_item_delete_btn)
        val editBTN = itemView.findViewById<FloatingActionButton>(R.id.calendar_item_edit_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val task = tasks[position]
        holder.editBTN.visibility = View.GONE

        holder.texT.text = task.text
        if (tasks[position].state == 0) holder.cardView.setCardBackgroundColor(Color.rgb(255,36,36))
        val today = LocalDate.now()
        if (task.year >= today.year){
            if (task.year > today.year) {
                holder.cardView.setCardBackgroundColor(Color.rgb(111, 192, 255))
                holder.editBTN.visibility = View.VISIBLE
            }
            else{
                if (task.month > today.monthValue) holder.cardView.setCardBackgroundColor(Color.rgb(111, 192, 255))
                else{
                    if (task.month == today.monthValue && task.day >= today.dayOfMonth) {
                        holder.cardView.setCardBackgroundColor(Color.rgb(111, 192, 255))
                        holder.editBTN.visibility = View.VISIBLE
                    }
                }
            }
        }
        if (task.state == 1) {
            holder.cardView.setCardBackgroundColor(Color.GREEN)
            holder.editBTN.visibility = View.GONE
        }

        holder.deleteBTN.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Do you really want to permanently delete this task ?")
            builder.setCancelable(true)
            builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                    appDatabase.getTaskDao().deleteTask(task)
                    tasks.remove(task)
                    notifyItemRemoved(position)
                } as DialogInterface.OnClickListener)
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                    dialog.cancel()
                } as DialogInterface.OnClickListener)
            val alertDialog = builder.create()
            alertDialog.show()

        }
        holder.editBTN.setOnClickListener {
            calendarRecyclerInterface.editTask(task)
        }
    }

    interface CalendarRecyclerInterface{
        fun editTask(task: Task)
    }

}