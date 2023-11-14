package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flying.databinding.ActivityLoginMain2Binding
import com.example.flying.databinding.ActivityLoginMainBinding

class LoginMainActivity2 : AppCompatActivity() {
    val binding by lazy {
        ActivityLoginMain2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cafeAdd.setOnClickListener {
            val intent = Intent(baseContext, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.flyingLogo1.setOnClickListener {
            val intent = Intent(baseContext, SeatActivity::class.java)
            startActivity(intent)
        }
    }
}