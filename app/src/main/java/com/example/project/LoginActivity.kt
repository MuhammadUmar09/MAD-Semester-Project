package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button


    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase components
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is already authenticated
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // User is already authenticated, navigate to the next activity
            navigateToNextActivity()
            return
        }

        setContentView(R.layout.login_page)

        // Initialize views
        usernameEditText = findViewById(R.id.loginUsernameEditText)
        passwordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)

        // Set click listeners
        loginButton.setOnClickListener {
            loginUser()
        }
        val forgetPasswordTextView = findViewById<TextView>(R.id.forgetPassword)

        forgetPasswordTextView.setOnClickListener {
            // Implement forgot password logic if needed
            // For example, open a new activity or show a dialog
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToNextActivity() {
        // Replace MainActivity::class.java with the actual next activity you want to navigate to
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
        finish() // finish the LoginActivity so the user can't navigate back to it
    }
    private fun loginUser() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            return
        }

        // Authenticate the user with Firebase
        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // Login successful
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent:Intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)

                    // You can add additional logic here, such as navigating to the next activity
                } else {
                    // If login fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
