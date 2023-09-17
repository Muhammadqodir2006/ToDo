package uz.itschool.todo

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import uz.itschool.todo.database.AppDatabase
import uz.itschool.todo.database.Task
import uz.itschool.todo.databinding.FragmentAddTaskBinding
import java.util.Date

private const val ARG_PARAM1 = "param1"

class AddTaskFragment : Fragment() {
    private var param1: Task? = null

    var imageUrl: String = ""
    lateinit var binding:FragmentAddTaskBinding
    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        val galleryUri = it
        try{
            binding.addTaskImage.setImageURI(galleryUri)
            imageUrl = galleryUri.toString()
        }catch(e:Exception){
            e.printStackTrace()
        }

    }
    @SuppressLint("SetTextI18n")
    override fun
            onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val appDatabase = AppDatabase.getInstance(requireContext())
        binding.addTaskBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.addTasKDatePicker.minDate = Date().time
        binding.addTaskAddBtn.isEnabled = false

        if (param1 != null){
            binding.addTaskAddBtn.text = "Update"
            binding.addTaskTopText.text = "Edit task"
            binding.addTaskTaskEdittxt.setText(param1!!.text)
            binding.addTasKDatePicker.init(param1!!.year, param1!!.month-1, param1!!.day, null)
            binding.addTaskAddBtn.isEnabled = true
            if (param1!!.imageUrl != "") binding.addTaskImage.setImageURI(Uri.parse(param1!!.imageUrl))
            binding.addTaskImage
        }

        binding.addTaskTaskEdittxt.addTextChangedListener {
            binding.addTaskAddBtn.isEnabled =
                binding.addTaskTaskEdittxt.text.toString().isNotEmpty()
        }

        binding.addTaskEditGallery.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
        binding.addTaskEditDelete.setOnClickListener {
            binding.addTaskImage.setImageResource(R.drawable.icon_main_50)
            imageUrl = ""
        }
        binding.addTaskEditCamera.visibility = View.GONE
        binding.addTaskAddBtn.setOnClickListener {
            val day: Int = binding.addTasKDatePicker.dayOfMonth
            val month: Int = binding.addTasKDatePicker.month + 1
            val year: Int = binding.addTasKDatePicker.year
            val text: String = binding.addTaskTaskEdittxt.text.toString().trim()

            if (param1 == null) {
                appDatabase.getTaskDao().addTask(
                    Task(
                        day = day,
                        month = month,
                        year = year,
                        text = text,
                        imageUrl = imageUrl,
                        state = 0
                    )
                )
            }else{
                val task = param1!!
                task.day = day
                task.month = month
                task.year = year
                task.text = text
                task.imageUrl = imageUrl
                appDatabase.getTaskDao().updateTask(task)
            }
            requireActivity().onBackPressed()
        }

        return binding.root
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Task
        }
    }

    companion object {
        fun newInstance(param1: Task) =
            AddTaskFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }


}