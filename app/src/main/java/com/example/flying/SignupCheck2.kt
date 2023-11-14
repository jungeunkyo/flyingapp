package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup_check2.*
import kotlinx.android.synthetic.main.activity_signup_check2.BeforeBtn3
import kotlinx.android.synthetic.main.activity_signup_check2.CompletionBtn2


class SignupCheck2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_check2)

        checkBoxAll2.setOnClickListener { onCheckChanged(checkBoxAll2) }
        firstCheck2.setOnClickListener { onCheckChanged(firstCheck2) }
        secondCheck2.setOnClickListener { onCheckChanged(secondCheck2) }
        thirdCheck2.setOnClickListener { onCheckChanged(thirdCheck2) }
        fourthCheck2.setOnClickListener { onCheckChanged(fourthCheck2) }

        CompletionBtn2.setOnClickListener {
            //필수항목 체크 해야만 다음 화면으로 넘어갈 수 있음
            if(firstCheck2.isChecked && secondCheck2.isChecked){
                val intent = Intent(baseContext, SignupInfo2::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "필수 항목을 모두 체크해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        BeforeBtn3.setOnClickListener{
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }
        flyingLogo5.setOnClickListener{
            val intent=Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }


    //모두 동의합니다 체크하면 전체 항목 체크됨
    //모두 동의합니다 체크 후 다른 항목 체크 풀면 모두 동의합니다 체크 해제됨
    private fun onCheckChanged(compoundButton: CompoundButton) {
        when (compoundButton.id) {
            R.id.checkBoxAll2 -> {
                if (checkBoxAll2.isChecked) {
                    firstCheck2.isChecked = true
                    secondCheck2.isChecked = true
                    thirdCheck2.isChecked = true
                    fourthCheck2.isChecked = true
                } else {
                    firstCheck2.isChecked = false
                    secondCheck2.isChecked = false
                    thirdCheck2.isChecked = false
                    fourthCheck2.isChecked = false
                }
            }
            else -> {
                checkBoxAll2.isChecked = (
                        firstCheck2.isChecked
                                && secondCheck2.isChecked
                                && thirdCheck2.isChecked
                                && fourthCheck2.isChecked)
            }
        }
    }
}