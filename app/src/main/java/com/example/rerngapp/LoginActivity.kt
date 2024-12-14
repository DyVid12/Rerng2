package com.example.rerngapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity() : AppCompatActivity(), Parcelable {

    private lateinit var userEdt: EditText
    private lateinit var passEdt: EditText
    private lateinit var loginButton: Button

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userEdt = findViewById(R.id.username)
        passEdt = findViewById(R.id.password)
        loginButton = findViewById(R.id.getInBnt)

        // Set an OnClickListener on the login button
        loginButton.setOnClickListener {
            val username = userEdt.text.toString()
            val password = passEdt.text.toString()

            Log.i("Test Credentials","Username: $username and Password : $password")

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in your username and password", Toast.LENGTH_SHORT).show()
            } else if (username == "ravit" && password == "ravit") {
                // Start MainActivity upon successful login
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginActivity> {
        override fun createFromParcel(parcel: Parcel): LoginActivity {
            return LoginActivity(parcel)
        }

        override fun newArray(size: Int): Array<LoginActivity?> {
            return arrayOfNulls(size)
        }
    }
}
