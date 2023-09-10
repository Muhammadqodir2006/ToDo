package uz.itschool.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.todo.adapter.CalendarRecyclerAdapter
import uz.itschool.todo.database.Task
import uz.itschool.todo.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.calendarBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.calendarTasksRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // TODO: specify tasks
        val tasks = ArrayList<Task>()
        binding.calendarTasksRecycler.adapter = CalendarRecyclerAdapter(tasks)


        //////////////////////////

        binding.calendarDatePicker.setOnDateChangedListener { datePicker, i, i2, i3 ->
            //TODO: Update recycler
        }

        return binding.root
    }

}