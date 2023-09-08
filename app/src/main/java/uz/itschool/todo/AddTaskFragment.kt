package uz.itschool.todo

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import uz.itschool.todo.databinding.FragmentAddTaskBinding
import java.security.Permission
import java.util.Date

class AddTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.addTaskBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.addTasKDatePicker.minDate = Date().time

        binding.addTaskEditImg.setOnClickListener {


        }

        return binding.root
    }



}