package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flying.databinding.ActivityLoginMain21Binding
import com.example.flying.databinding.ActivityReservationBinding

class LoginMainActivity21 : AppCompatActivity() {
    val binding by lazy {
        ActivityLoginMain21Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mypage.setOnClickListener {
            val intent = Intent(baseContext, MypageActivity::class.java)
            startActivity(intent)
        }


    }
}