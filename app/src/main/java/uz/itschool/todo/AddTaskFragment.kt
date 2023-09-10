package uz.itschool.todo

import android.graphics.drawable.Drawable
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
    ): View {
        val binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.addTaskBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.addTasKDatePicker.minDate = Date().time
        binding.addTaskEditImg.setOnClickListener {
            // TODO: ImagePicker
        }
        binding.addTaskAddBtn.setOnClickListener {
            val day : Int = binding.addTasKDatePicker.dayOfMonth
            val month : Int = binding.addTasKDatePicker.month
            val year : Int = binding.addTasKDatePicker.year

            val imageUrl = if (binding.addTaskImage.id == R.drawable.icon_main_50) "" else "1"
            val text : String = binding.addTaskTaskEdittxt.text.toString().trim()
            // TODO: Add task



            requireActivity().onBackPressed()
        }

        return binding.root
    }



}