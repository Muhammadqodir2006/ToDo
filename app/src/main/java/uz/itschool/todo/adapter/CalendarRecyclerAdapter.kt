package uz.itschool.todo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import uz.itschool.todo.R
import uz.itschool.todo.database.Task
import java.time.LocalDate

class CalendarRecyclerAdapter(val tasks:List<Task>) : RecyclerView.Adapter<CalendarRecyclerAdapter.MyHolder>(){
    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val texT = itemView.findViewById<TextView>(R.id.calendar_item_text)
        val cardView = itemView.findViewById<CardView>(R.id.calendar_item_parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val task = tasks[position]
        holder.texT.text = task.text
        if (tasks[position].state == 0) holder.cardView.setCardBackgroundColor(Color.rgb(255,36,36))
        val today = LocalDate.now()
        if (task.year >= today.year){
            if (task.year > today.year) holder.cardView.setCardBackgroundColor(Color.rgb(111, 192, 255))
            else{
                if (task.month > today.monthValue) holder.cardView.setCardBackgroundColor(Color.rgb(111, 192, 255))
                else{
                    if (task.month == today.monthValue && task.day > today.dayOfMonth) holder.cardView.setCardBackgroundColor(Color.rgb(111, 192, 255))
                }
            }
        }
    }

}