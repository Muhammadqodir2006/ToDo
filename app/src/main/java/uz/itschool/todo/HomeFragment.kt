package uz.itschool.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itschool.todo.adapter.HomeRecyclerAdapter
import uz.itschool.todo.database.Task
import uz.itschool.todo.databinding.FragmentHomeBinding
import java.time.LocalDate

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeAddTaskBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTaskFragment)
        }
        binding.homeToCalendarBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calendarFragment)
        }

        val today = LocalDate.now()

        binding.homeDay.text = today.dayOfMonth.toString()
        binding.homeMonthYear.text = today.month.name

        //TODO: get count
        binding.homeTasksCount.text

        ////////////////////////////////

        val tasks = ArrayList<Task>()

        binding.homeRecyclerVw.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.homeRecyclerVw.adapter = HomeRecyclerAdapter(requireContext())
        // TODO: Add Recycler

        return binding.root
    }

}