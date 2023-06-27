package com.example.zd8_v5

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        backButton = findViewById(R.id.backButton)

    }


    fun onLoginClicked(view: View) {
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username == savedUsername && password == savedPassword) {
            Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GeneralActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    fun BackButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}