package com.example.rerngapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rerngapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout and initialize binding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Navigate to LoginActivity
        binding.already.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Handle sign-up button click
        binding.getInBnt.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val pass = binding.password.text.toString().trim()
            val confirmPass = binding.confirmPassword.text.toString().trim() // Adjust this field as needed

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Sign-up Successful!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                val errorMessage = task.exception?.localizedMessage ?: "Sign-up failed!"
                                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
