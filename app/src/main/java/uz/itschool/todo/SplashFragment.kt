package uz.itschool.todo

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.itschool.todo.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(inflater,  container, false)
        val handler  = Handler()
        handler.postDelayed({
                            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 700)
        return binding.root
    }
}