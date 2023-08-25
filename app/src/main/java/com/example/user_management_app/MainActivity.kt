package com.example.user_management_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.user_management_app.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.email.addTextChangedListener(
            afterTextChanged = {
                if (!it.isNullOrBlank()) {
                    binding.textInputLayout.error = null
                } else {
                    binding.textInputLayout.error = "Please Enter your email address"
                }
            }
        )

        binding.password.addTextChangedListener(
            afterTextChanged = {
                if (!it.isNullOrBlank()) {
                    binding.textInputLayout2.error = null
                } else {
                    binding.textInputLayout2.error = "Please Enter Password"
                }
            }
        )

        binding.login.setOnClickListener {
            val emailId = binding.email.text.toString()
            val passId = binding.password.text.toString()

            if (isValidEmail(emailId) && isValidPassword(passId)) {
                val intent = Intent(this,dashboard::class.java)
                intent.putExtra("email",emailId)
                startActivity(intent)
                Log.d("IntentData", "email: $emailId")

            } else {
                Toast.makeText(this,"Enter Valid Details",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}