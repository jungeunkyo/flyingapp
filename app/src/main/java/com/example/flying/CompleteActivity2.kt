package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.flying.databinding.SignupComplete2Binding


class CompleteActivity2 : AppCompatActivity() {
    val binding by lazy {
        SignupComplete2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.completeBtn2.setOnClickListener {
            val intent = Intent(baseContext, LoginMainActivity2::class.java)
            startActivity(intent)
        }


    }
}