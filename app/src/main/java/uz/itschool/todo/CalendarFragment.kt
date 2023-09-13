package uz.itschool.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.todo.adapter.CalendarRecyclerAdapter
import uz.itschool.todo.database.AppDatabase
import uz.itschool.todo.database.Task
import uz.itschool.todo.databinding.FragmentCalendarBinding
import java.time.LocalDate

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val appDatabase = AppDatabase.getInstance(requireContext())

        binding.calendarBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.calendarTasksRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // TODO: specify tasks
        val today = LocalDate.now()
        var tasks = appDatabase.getTaskDao().getTasks(today.dayOfMonth, today.monthValue, today.year) as ArrayList<Task>
        binding.calendarTasksRecycler.adapter = CalendarRecyclerAdapter(tasks, appDatabase, requireContext(), object : CalendarRecyclerAdapter.CalendarRecyclerInterface{
            override fun editTask(task: Task) {
                val bundle = Bundle()
                bundle.putSerializable("param1", task)
                findNavController().navigate(R.id.action_calendarFragment_to_addTaskFragment, bundle)
            }
        })


        //////////////////////////

        binding.calendarDatePicker.setOnDateChangedListener { datePicker, i, i2, i3 ->
            val datepicker = binding.calendarDatePicker
            val day = datepicker.dayOfMonth
            val month = datepicker.month+1
            val year = datepicker.year
            tasks = appDatabase.getTaskDao().getTasks(day, month, year) as ArrayList<Task>
            binding.calendarTasksRecycler.adapter = CalendarRecyclerAdapter(tasks, appDatabase, requireContext(), object : CalendarRecyclerAdapter.CalendarRecyclerInterface{
                override fun editTask(task: Task) {
                    val bundle = Bundle()
                    bundle.putSerializable("param1", task)
                    findNavController().navigate(R.id.action_calendarFragment_to_addTaskFragment, bundle)
                }
            })
        }

        return binding.root
    }


}