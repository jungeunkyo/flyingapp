package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.flying.data.AuthService
import com.example.flying.databinding.ActivityLoginBinding
import com.example.flying.model.BaseResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginBtn2.setOnClickListener {
            //login()
            val intent = Intent(baseContext, LoginMainActivity::class.java)
            startActivity(intent)
        }


        binding.flyingLogo2.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signup.setOnClickListener {
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }

    }


    private fun login() {
        val edit_id = edit_id.text.toString()
        val edit_pw = edit_pw.text.toString()

        if (edit_id.isEmpty() || edit_pw.isEmpty()) {
            Toast.makeText(this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        }


        if (edit_id.isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }

        if (edit_pw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }


        else{
            AuthService.service.login(edit_id, edit_pw)
                .enqueue(object : Callback<BaseResponse> {
                    override fun onResponse(
                        call: Call<BaseResponse>,
                        response: Response<BaseResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            // 로그인 성공 시 처리
                            val body = response.body()!!
                            if (body.success) {
                                Log.d("LoginActivity", "indi")
                                membertype()
                                // 로그인 완료 후 membertype() 함수 호출
                            }  else {
                                Toast.makeText(this@LoginActivity, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                                // 비밀번호가 틀린 경우 토스트 메시지 표시
                            }
                        } else {
                            // 로그인 실패 시 처리
                            Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            Log.e("LoginActivity", "로그인 실패: " + response.message())
                        }

                    }
                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        t.printStackTrace()
                        Log.d("LoginActivity", "onFailure")
                    }


                })
        }
    }

    private fun membertype() {
        val edit_id = edit_id.text.toString()
        val edit_pw = edit_pw.text.toString()

        AuthService.service.membertype(edit_id, edit_pw)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    if (isDestroyed) return

                    if (response.isSuccessful) {
                        val body = response.body()!!

                        if (body.success) {
                            Log.d("LoginActivity", "indi")


                        val intent = Intent(baseContext, LoginMainActivity::class.java)
                        startActivity(intent)
                        } else {
                            Log.d("LoginActivity", "business")


                            val intent = Intent(baseContext, LoginMainActivity2::class.java)
                            startActivity(intent)
                        }
                    }
                }


                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("LoginActivity", "onFailure")
                }


            })




    }

}
