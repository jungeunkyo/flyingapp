package com.example.flying

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.flying.databinding.ActivityMypageIndiBinding

class MypageIndiActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMypageIndiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // 올바른 레이아웃 설정

        binding.logoutBtn.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        binding.cancelBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("예약취소")
                .setMessage("예약을 취소하시겠습니까?")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(baseContext, MypageIndiActivity2::class.java)
                        startActivity(intent)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            // 다이얼로그를 띄워주기
            builder.show()
        }
    }
}
