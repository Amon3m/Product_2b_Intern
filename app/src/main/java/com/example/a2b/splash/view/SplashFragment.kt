package com.example.a2b.splash.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.a2b.R
import com.example.a2b.databinding.FragmentSplashBinding
import com.example.a2b.splash.viewmodel.SplashViewModel


class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var splashViewModel: SplashViewModel

    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProvider(requireActivity()).get(SplashViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       handler.postDelayed({ navigateToNextFragment() }, 2000)
    }

    private fun navigateToNextFragment() {
        val userLoggedIn = splashViewModel.checkIfUserLoggedIn(requireContext())
        Log.e("splash", "$userLoggedIn");
        if (userLoggedIn) {
            findNavController().navigate(R.id.homeFragment)
        } else {
            findNavController().navigate(R.id.loginFragment)
        }
    }


}