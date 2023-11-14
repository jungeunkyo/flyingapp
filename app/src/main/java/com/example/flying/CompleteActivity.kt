package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.flying.databinding.SignupCompleteBinding


class CompleteActivity : AppCompatActivity() {
    val binding by lazy {
        SignupCompleteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.completeBtn.setOnClickListener {
            val intent = Intent(baseContext, LoginMainActivity::class.java)
            startActivity(intent)
        }


    }
}
