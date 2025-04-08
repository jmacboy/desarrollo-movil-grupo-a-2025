package com.example.practicafragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practicafragments.databinding.FragmentTest1Binding

/**
 * A simple [Fragment] subclass.
 */
class TestFragment1 : Fragment() {
    private lateinit var binding: FragmentTest1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTest1Binding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.button2.setOnClickListener {
            Toast.makeText(context, "Prueba desde el fragment 1", Toast.LENGTH_SHORT).show()
        }
    }

}