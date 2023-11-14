package com.example.flying

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.flying.data.AuthService
import com.example.flying.databinding.ActivityRegistrationBinding
import com.example.flying.model.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Spinner
import androidx.core.view.children


class RegistrationActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityRegistrationBinding.inflate(layoutInflater)
    }

    private var selectedTagList = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val comAddressArr = arrayOf("---선택하세요---", "서울", "부산", "경기")
        val comAddress2Arr = arrayOf("---선택하세요---", "양천구", "마포구", "구로구", "영등포구")
        val comAddress3Arr = arrayOf("--선택하세요--", "중구", "서구")
        val comAddress4Arr = arrayOf("--선택하세요--", "미추홀구", "연수구")

        val adapter = ArrayAdapter<String>(
            baseContext,
            android.R.layout.simple_spinner_dropdown_item,
            comAddressArr
        )
        binding.comAddress.adapter = adapter
        val adapter2 = ArrayAdapter<String>(
            baseContext,
            android.R.layout.simple_spinner_dropdown_item,
            comAddress2Arr
        )
        binding.comAddress2.adapter = adapter2
        val adapter3 = ArrayAdapter<String>(
            baseContext,
            android.R.layout.simple_spinner_dropdown_item,
            comAddress3Arr
        )
        binding.comAddress2.adapter = adapter3
        val adapter4 = ArrayAdapter<String>(
            baseContext,
            android.R.layout.simple_spinner_dropdown_item,
            comAddress4Arr
        )
        binding.comAddress2.adapter = adapter4

        binding.comAddress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) { // "서울시" 선택 시에만 동적으로 목록을 변경
                    val adapter2 = ArrayAdapter<String>(
                        baseContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        comAddress2Arr
                    )
                    binding.comAddress2.adapter = adapter2
                } else if ((position == 2)) {
                    // "서울시" 이외의 선택지일 때는 comAddress2 스피너를 초기화
                    val adapter3 = ArrayAdapter<String>(
                        baseContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        comAddress3Arr
                    )
                    binding.comAddress2.adapter = adapter3
                } else if ((position == 3)) {
                    // "서울시" 이외의 선택지일 때는 comAddress2 스피너를 초기화
                    val adapter4 = ArrayAdapter<String>(
                        baseContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        comAddress4Arr
                    )
                    binding.comAddress2.adapter = adapter4
                } else {
                    // "서울시" 이외의 선택지일 때는 comAddress2 스피너를 초기화
                    val adapter2 = ArrayAdapter<String>(
                        baseContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayOf("---선택하세요---")
                    )
                    binding.comAddress2.adapter = adapter2
                    val adapter3 = ArrayAdapter<String>(
                        baseContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayOf("---선택하세요---")
                    )
                    binding.comAddress2.adapter = adapter3
                    val adapter4 = ArrayAdapter<String>(
                        baseContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayOf("---선택하세요---")
                    )
                    binding.comAddress2.adapter = adapter4

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 선택되지 않은 경우에 대한 처리 (필요에 따라 구현)
            }
        }



        binding.completionBtn.setOnClickListener {
            //Update()
            val intent = Intent(baseContext, SeatActivity::class.java)
            startActivity(intent)
        }

        binding.beforeBtn.setOnClickListener {
            val intent = Intent(baseContext, LoginMainActivity2::class.java)
            startActivity(intent)
        }

        binding.flyingLogo4.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

        val tagButtons = binding.tagContainer.children.filter { it is CheckBox }
            .map { it as CheckBox }

        tagButtons.forEach {
            it.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if (selectedTagList.contains(buttonView.id)) return@setOnCheckedChangeListener

                    if (selectedTagList.size == 3) {
                        val id = selectedTagList.removeAt(0)
                        tagButtons.firstOrNull { it.id == id }?.isChecked = false
                    }

                    selectedTagList.add(buttonView.id)

                } else {
                    if (!selectedTagList.contains(buttonView.id)) return@setOnCheckedChangeListener

                    val index = selectedTagList.indexOfFirst { it == buttonView.id }
                    if (index >= 0) {
                        selectedTagList.removeAt(index)
                    }
                }
            }
        }
    }

    private fun Update() {
        val id = binding.id.text.toString()
        val cafeName = binding.cafeName.text.toString()
        val city = binding.comAddress.selectedItem.toString()
        val gu = binding.comAddress2.selectedItem.toString()
        val address = binding.comAddress3.text.toString()
        val businessNumber = binding.comNum.text.toString()
        val phoneNumber = binding.number.text.toString()

        // 선택된 태그 리스트
        val tags = selectedTagList.map {
            (binding.tagContainer.findViewById(it) as CheckBox).text.toString()
        }
        Log.d("RegistrationActivity", tags.toString())

        if (businessNumber.isEmpty() || cafeName.isEmpty() ||
            id.isEmpty() || phoneNumber.isEmpty() || city.isEmpty() || gu.isEmpty() || address.isEmpty() || tags.isEmpty()
        ) {
            Toast.makeText(this, "정보를 모두 작성해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            AuthService.service.Update(
                id,
                cafeName,
                city,
                gu,
                address,
                businessNumber,
                phoneNumber,
                tags.getOrNull(0) ?: "",
                tags.getOrNull(1) ?: "",
                tags.getOrNull(2) ?: "",
            ).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    if (isDestroyed) return

                    if (response.isSuccessful) {
                        val body = response.body()!!
                        if (body.success) {

                            val intent = Intent(baseContext, SeatActivity::class.java)
                            startActivity(intent) // SeatActivity로 이동

                            finish() // 현재 액티비티 종료
                            Log.d("RegistrationActivity", "Succeed update.")
                        }
                    } else {
                        Log.d("RegistrationActivity", "isSuccessful variable is false.")
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("RegistrationActivity", "onFailure")
                }
            })
        }
    }
}