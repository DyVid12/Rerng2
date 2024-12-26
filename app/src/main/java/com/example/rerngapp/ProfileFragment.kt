package com.example.rerngapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.rerngapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    // View Binding variable
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        // Setup UI components
        setupUI()

        return view
    }

    private fun setupUI() {
        // Example: Set user details (replace with actual data logic)
        binding.userName.text = "John Doe"
        binding.userEmail.text = "johndoe@example.com"
        binding.profileImage.setImageResource(R.drawable.avatar)

        // Add button actions
        binding.btnUpdateProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Update Profile clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Logout clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}
