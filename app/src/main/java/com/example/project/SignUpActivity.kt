package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_page)

        // Initialize Firebase components
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signUpButton = findViewById(R.id.signupButton)
        val loginNavigator: Button = findViewById(R.id.login_navigator)

        loginNavigator.setOnClickListener {
            val intent:Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Set click listener for the Sign Up button
        signUpButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate username and password (add your own validation logic)

        // Create user in Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // User creation successful, you can now add user data to Firebase Realtime Database
                    val userId = firebaseAuth.currentUser?.uid
                    userId?.let {
                        val userReference = database.reference.child("users").child(it)
                        val userData = mapOf(
                            "username" to username,
                            // Add other user details as needed
                        )

                        userReference.setValue(userData)
                    }
                    Toast.makeText(
                        this,
                        "Sign Up Successful!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Add any additional actions after successful sign-up
                } else {
                    Toast.makeText(
                        this,
                        "Sign Up Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
