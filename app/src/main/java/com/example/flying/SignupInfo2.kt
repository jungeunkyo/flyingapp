package com.example.flying

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flying.data.AuthService
import com.example.flying.model.BaseResponse
import kotlinx.android.synthetic.main.activity_signup_info2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupInfo2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_info2)

        completionBtn2.setOnClickListener {
            //signUp()
            val intent = Intent(baseContext, CompleteActivity2::class.java)
            startActivity(intent)
        }

        beforeBtn2.setOnClickListener {
            val intent = Intent(baseContext, SignupCheck2::class.java)
            startActivity(intent)
        }

        flyingLogo6.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp() {
        val id = id2.text.toString()
        val pw = pw2.text.toString()
        val name = name2.text.toString()
        val phonenum = phonenum2.text.toString()
        val email = email2.text.toString()
        val pwCheck = pwCheck2.text.toString()

        if (name.isEmpty() || phonenum.isEmpty() ||
            id.isEmpty() || pw.isEmpty() || pwCheck.isEmpty()
        ) {
            Toast.makeText(this, "필수 정보를 모두 작성해주세요.", Toast.LENGTH_SHORT).show();
        }

        if (pw != pwCheck) {  //비번확인
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();

        } else {
            AuthService.service.signUp(id, pw, name, phonenum, email, "기업회원")
                .enqueue(object : Callback<BaseResponse> {
                    override fun onResponse(
                        call: Call<BaseResponse>,
                        response: Response<BaseResponse>
                    ) {
                        if (isDestroyed) return

                        if (response.isSuccessful) {
                            val body = response.body()!!
                            if (body.success) {
                                Log.d("SignupInfo2", "Succeed signing up.")

                                val intent = Intent(baseContext, CompleteActivity2::class.java)
                                startActivity(intent)

                            } else {
                                Toast.makeText(
                                    this@SignupInfo2,
                                    "이미 사용중인 아이디 입니다. 다시 입력해 주세요.",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }
                        } else {
                            Log.d("SignupInfo2", "isSuccessful variable is false.")
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        t.printStackTrace()
                        Log.d("SignupInfo2", "onFailure")
                    }
                })
        }
    }
}