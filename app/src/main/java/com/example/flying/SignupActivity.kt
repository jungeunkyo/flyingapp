package com.example.flying
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.flying.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.SignupIndiBtn.setOnClickListener {
            val intent = Intent(baseContext, SignupCheck1::class.java)
            startActivity(intent)
        }

        binding.SignupComBtn.setOnClickListener {
            val intent = Intent(baseContext, SignupCheck2::class.java)
            startActivity(intent)
        }

        binding.flyingLogo1.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}