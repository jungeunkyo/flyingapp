package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flying.databinding.ActivityMainBinding
import com.example.flying.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMypageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        binding.logoutBtn.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}