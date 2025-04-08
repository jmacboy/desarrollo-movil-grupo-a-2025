package com.example.practicanavigation.ui.fragments.second

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.practicanavigation.R
import com.example.practicanavigation.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private val viewModel: SecondViewModel by viewModels()
    private lateinit var binding: FragmentSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelObservers()

    }

    private fun setupViewModelObservers() {
        viewModel.userLoggedIn.observe(this) {
            if (it.isNullOrEmpty()) {
                return@observe
            }
            val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(it)
            findNavController().navigate(action)
        }
        viewModel.loginError.observe(this) {
            if (!it) {
                return@observe
            }
            Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.editText?.text.toString()
            val password = binding.txtPassword.editText?.text.toString()
            viewModel.login(username, password)
        }
    }
}