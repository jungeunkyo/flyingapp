package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.flying.databinding.ActivityLoginMainBinding


class LoginMainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityLoginMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.flyingLogo1.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        binding.reservBtn2.setOnClickListener {
            val intent = Intent(baseContext, ReservationActivity::class.java)
            startActivity(intent)
        }

        binding.textView9.setOnClickListener {
            val intent = Intent(baseContext, MypageIndiActivity::class.java)
            startActivity(intent)
        }
    }
}