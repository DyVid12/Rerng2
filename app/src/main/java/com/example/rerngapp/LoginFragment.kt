package com.example.rerngapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.rerngapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var buttonlogin : Button
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonlogin = view.findViewById(R.id.bottunlogin)
        buttonlogin.setOnClickListener({
            val intent=Intent(requireContext(),LoginActivity::class.java)
            startActivity(intent)
        })
    }


}