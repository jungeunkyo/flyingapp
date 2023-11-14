package com.example.flying

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.example.flying.databinding.ActivityReservationBinding

class ReservationActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityReservationBinding.inflate(layoutInflater)
    }

    private lateinit var selectedRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val payButton = findViewById<Button>(R.id.pay_btn)
        val radioGroup = findViewById<RadioGroup>(R.id.use_time)

        binding.imageView6.setOnClickListener {
            val intent = Intent(baseContext, ReservationActivity2::class.java)
            startActivity(intent)
        }

        payButton.setOnClickListener(View.OnClickListener {
            // 선택된 라디오 버튼 가져오기
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                selectedRadioButton = findViewById(selectedId)
                val selectedOption = selectedRadioButton.text.toString()

                //알림창 띄우기
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("결제 확인")
                alertDialogBuilder.setMessage("'$selectedOption'을 결제하시겠습니까?")
                alertDialogBuilder.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                })
                alertDialogBuilder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                })
                alertDialogBuilder.show()



                /*
                // 결제 정보 설정
                val orderId = generateOrderId() // 주문 ID 생성
                val payment = Payment(
                    amount = selectedCost, // 선택된 라디오 버튼의 결제 비용 설정
                    orderId = orderId,
                    paymentMethods = listOf(PaymentMethod.CREDIT_CARD), // 결제 수단 (크레딧 카드 등)
                    returnUrl = "YOUR_RETURN_URL" // 결제 후 리디렉션될 URL 설정
                )
                    // Toss Payments 초기화
                    val tossPaymentsConfig = TossPaymentsConfig(
                        apiKey = "YOUR_API_KEY",
                        environment = TossPaymentsConfig.Environment.SANDBOX // 또는 .PRODUCTION
                )


                TossPayments.initialize(this, tossPaymentsConfig)

                // 결제 페이지로 이동
                val tossPayments = TossPayments.getInstance()
                val paymentIntent = tossPayments.createPaymentIntent(payment)
                tossPayments.startPaymentActivityForResult(this, paymentIntent, PAYMENT_REQUEST_CODE)

                */
            }
        }    /*
            // 주문 ID 생성 함수
            fun generateOrderId(): String {
                return "ORDER12345"
            }
*/

        )
    }
}

