package com.example.flying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup_check1.*

class SignupCheck1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_check1)

        checkBoxAll.setOnClickListener { onCheckChanged(checkBoxAll) }
        firstCheck.setOnClickListener { onCheckChanged(firstCheck) }
        secondCheck.setOnClickListener { onCheckChanged(secondCheck) }
        thirdCheck.setOnClickListener { onCheckChanged(thirdCheck) }
        fourthCheck.setOnClickListener { onCheckChanged(fourthCheck) }

        CompletionBtn.setOnClickListener {
            //필수항목 체크 해야만 다음 화면으로 넘어갈 수 있음
            if(firstCheck.isChecked && secondCheck.isChecked){
                val intent = Intent(baseContext, SignupInfo::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "필수 항목을 모두 체크해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        BeforeBtn2.setOnClickListener{
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }
        flyingLogo3.setOnClickListener{
            val intent=Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }


    //모두 동의합니다 체크하면 전체 항목 체크됨
    //모두 동의합니다 체크 후 다른 항목 체크 풀면 모두 동의합니다 체크 해제됨
    private fun onCheckChanged(compoundButton: CompoundButton) {
        when (compoundButton.id) {
            R.id.checkBoxAll -> {
                if (checkBoxAll.isChecked) {
                    firstCheck.isChecked = true
                    secondCheck.isChecked = true
                    thirdCheck.isChecked = true
                    fourthCheck.isChecked = true
                } else {
                    firstCheck.isChecked = false
                    secondCheck.isChecked = false
                    thirdCheck.isChecked = false
                    fourthCheck.isChecked = false
                }
            }
            else -> {
                checkBoxAll.isChecked = (
                        firstCheck.isChecked
                                && secondCheck.isChecked
                                && thirdCheck.isChecked
                                && fourthCheck.isChecked)
            }
        }
    }
}