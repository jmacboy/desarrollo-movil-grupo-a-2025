package com.example.practicanavigation.ui.fragments.third

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.practicanavigation.R
import com.example.practicanavigation.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdFragment()
    }

    private val args: ThirdFragmentArgs by navArgs()
    private val viewModel: ThirdViewModel by viewModels()
    private lateinit var binding: FragmentThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        val username = args.username
        binding.lblWelcome.text = "Bienvenido $username"
        return binding.root
    }

}