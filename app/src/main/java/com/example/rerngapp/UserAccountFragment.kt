package com.example.rerngapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class UserAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_account, container, false)

        // Retrieve email from arguments
        val userEmail = arguments?.getString("email") ?: "No Email"
        Log.d("UserAccountFragment", "Email retrieved from arguments: $userEmail")


        // Update UI
        val userNameTextView = view.findViewById<TextView>(R.id.userName)
        val userEmailTextView = view.findViewById<TextView>(R.id.userEmail)

        userNameTextView.text = "Welcome!"
        userEmailTextView.text = userEmail

        val logoutButton = view.findViewById<Button>(R.id.btnLogout)
        logoutButton.setOnClickListener {
            // Sign out user
            FirebaseAuth.getInstance().signOut()

            // Show logout message
            Toast.makeText(requireContext(), "You have successfully logged out.", Toast.LENGTH_SHORT).show()

            // Redirect to IntroActivity
            val intent = Intent(requireContext(), IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        return view
    }


}