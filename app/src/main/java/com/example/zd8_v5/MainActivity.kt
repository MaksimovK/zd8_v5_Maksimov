package com.example.zd8_v5

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
    }

    fun onRegisterClicked(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun onLoginClicked(view: View) {
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (savedUsername != null && savedPassword != null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "No credentials found. Please register first.", Toast.LENGTH_SHORT).show()
        }
    }
}