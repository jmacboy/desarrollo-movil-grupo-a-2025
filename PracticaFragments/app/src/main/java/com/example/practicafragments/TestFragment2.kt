package com.example.practicafragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.practicafragments.databinding.FragmentTest2Binding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class TestFragment2 : Fragment() {
    private lateinit var binding: FragmentTest2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTest2Binding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.button.setOnClickListener {
            Toast.makeText(context, "Hola desde el fragment 2", Toast.LENGTH_SHORT).show()
        }
    }
}