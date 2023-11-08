package com.example.a2b.login.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a2b.R
import com.example.a2b.databinding.FragmentLoginBinding
import com.example.a2b.databinding.FragmentSplashBinding
import com.example.a2b.login.viewmodel.LoginViewModel
import com.example.a2b.splash.viewmodel.SplashViewModel
import com.google.android.material.textfield.TextInputLayout



class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel

    private val emailRegex = ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
//    private val userRegex = "^(?![0-9])[a-zA-Z0-9_]{3,15}$"
    private val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
    private val phoneRegex = "^\\d{11}$"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val userName = binding.userEditLogin.text.toString()
            val mail = binding.emailEditLogin.text.toString()
            val password = binding.passwordEditLogin.text.toString()
            val phoneNumber = binding.phoneEditLogin.text.toString()
            checkInputs(requireContext(), userName, mail, password, phoneNumber)
        }




    }
    fun checkInputs(context: Context, username:String, mail:String, password:String, phoneNum:String){
        binding.userLayoutLogin.error = null
        binding.emailLayoutLogin.error = null
        binding.passwordLayoutLogin.error = null

//        if (!username.matches(userRegex.toRegex())) {
//            binding.userLayoutLogin.setEndIconMode(TextInputLayout.END_ICON_CLEAR_TEXT)
//            binding.userLayoutLogin.error="Enter a valid username "
//            Toast.makeText(requireContext(), "Enter a valid username", Toast.LENGTH_SHORT).show()
//        } else

            if (!mail.matches(emailRegex.toRegex())) {
            binding.emailLayoutLogin.errorContentDescription="Enter a valid email"
            Toast.makeText(requireContext(), "Enter a valid email", Toast.LENGTH_SHORT).show()

        } else if (!phoneNum.matches(phoneRegex.toRegex())) {
            binding.phoneLayoutLogin.errorContentDescription="Enter 11 numbers"
            Toast.makeText(requireContext(), "Enter 11 numbers", Toast.LENGTH_SHORT).show()

        }else if (!password.matches(passwordRegex.toRegex())) {
            binding.passwordLayoutLogin.error="- At least 8 characters." +
                    "\n- At least one letter (uppercase or lowercase)." +
                    "\n- At least one digit."
            Toast.makeText(requireContext(), "- At least 8 characters." +
                    "\n- At least one letter (uppercase or lowercase)." +
                    "\n- At least one digit.", Toast.LENGTH_SHORT).show()

        } else {
            loginViewModel.signUp(context,username,mail,password,phoneNum)
            findNavController().navigate(R.id.homeFragment)



        }

    }
}