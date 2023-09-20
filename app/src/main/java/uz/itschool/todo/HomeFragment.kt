package uz.itschool.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.todo.adapter.HomeRecyclerAdapter
import uz.itschool.todo.database.AppDatabase
import uz.itschool.todo.database.Task
import uz.itschool.todo.databinding.FragmentHomeBinding
import java.time.LocalDate

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val appDatabase = AppDatabase.getInstance(requireContext())
        binding.homeAddTaskBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment)
        }
        binding.homeToCalendarBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendarFragment)
        }

        val today = LocalDate.now()

        binding.homeDay.text = today.dayOfWeek.name.lowercase().capitalize()
        binding.homeMonthYear.text = "${today.dayOfMonth}th ${today.month.name.lowercase().capitalize()}, ${today.year}"
        val taskCount = appDatabase.getTaskDao().getUndone(today.dayOfMonth, today.monthValue, today.year).size
        binding.homeTasksCount.text =  "$taskCount tasks for today"
        if (taskCount == 0) binding.homeTasksCount.text =   "No tasks for today"
        ////////////////////////////////

        val tasks = ArrayList<Task>()

        binding.homeRecyclerVw.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.homeRecyclerVw.adapter = HomeRecyclerAdapter(requireContext(), appDatabase, today.dayOfMonth, today.monthValue, today.year, requireActivity(), object : HomeRecyclerAdapter.HomeRecyclerInterface{
            override fun navigateEdit(task: Task) {
                val bundle = Bundle()
                bundle.putSerializable("param1", task)
                findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment, bundle)
            }
        })

        return binding.root
    }

}