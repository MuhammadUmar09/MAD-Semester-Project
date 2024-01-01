package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.os.Handler


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish() // Optional: finish the current activity to prevent going back to it
        }, 2000)
    }
}

