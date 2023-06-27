package com.example.zd8_v5

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etEmail = findViewById(R.id.etEmail)
    }

    fun onSaveClicked(view: View) {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val email = etEmail.text.toString()

        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()

        Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun BackButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}