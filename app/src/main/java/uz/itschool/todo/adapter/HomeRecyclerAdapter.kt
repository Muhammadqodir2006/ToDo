package uz.itschool.todo.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.itschool.todo.R
import uz.itschool.todo.database.AppDatabase
import uz.itschool.todo.database.Task


class HomeRecyclerAdapter(val context : Context,  appDatabase: AppDatabase, day:Int, month:Int, year:Int) : RecyclerView.Adapter<HomeRecyclerAdapter.MyHoler>() {
    var tasks = appDatabase.getTaskDao().getUndone(day, month, year)


    inner class MyHoler(itemView: View):RecyclerView.ViewHolder(itemView){
        val checkBox = itemView.findViewById<AppCompatCheckBox>(R.id.home_item_checkbox)
        val text = itemView.findViewById<TextView>(R.id.home_item_text)
        val edit  = itemView.findViewById<FloatingActionButton>(R.id.home_item_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHoler {
        return MyHoler(LayoutInflater.from(parent.context).inflate(R.layout.home_task_item, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: MyHoler, position: Int) {
        holder.edit.isEnabled = false
        holder.checkBox.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Do you really want to delete this task ?")
            builder.setCancelable(true)
            builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                    //TODO: Delete the task
                } as DialogInterface.OnClickListener)
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                    dialog.cancel()
                } as DialogInterface.OnClickListener)

            val alertDialog = builder.create()
            alertDialog.show()
        }
        holder.text.text = tasks[position].text
    }
}