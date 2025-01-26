package com.example.rerngapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rerngapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Navigate to SignupActivity
        binding.register.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        // Login button click listener
        binding.getInBnt.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val pass = binding.password.text.toString().trim()

            // Log the entered email
            Log.d("LoginActivity", "User entered email: $email")

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = firebaseAuth.currentUser
                            val userEmail = user?.email ?: "No Email"
                            saveLoginStatus(true) // Save login status

                            Log.d("LoginActivity", "User successfully logged in with email: $userEmail")
                            Toast.makeText(this, "Welcome back!!, $userEmail!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("email", userEmail)
                            startActivity(intent)
                        } else {
                            val errorMessage = task.exception?.localizedMessage ?: "Login failed!"
                            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Empty fields are not allowed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveLoginStatus(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }


}
