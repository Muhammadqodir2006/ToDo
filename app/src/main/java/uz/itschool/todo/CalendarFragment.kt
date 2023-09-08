package uz.itschool.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.itschool.todo.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)

        binding.calendarBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

}