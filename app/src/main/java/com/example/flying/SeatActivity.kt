package com.example.flying

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SeatActivity : AppCompatActivity() {

    private lateinit var seatLayout: FrameLayout
    private lateinit var seatNumberEditText: EditText
    private lateinit var createButton: Button
    private lateinit var updateButton: Button
    private val seatLayoutData = mutableListOf<SeatData>()
    private var selectedSeat: View? = null
    private var initialOffsetX = 0
    private var initialOffsetY = 0

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)


        seatLayout = findViewById(R.id.seat_layout)
        seatNumberEditText = findViewById(R.id.seat_number)
        createButton = findViewById(R.id.createBtn)
        updateButton = findViewById(R.id.saveBtn)

        createButton.setOnClickListener { generateSeats() }
        updateButton.setOnClickListener { saveSeatLayout() }

        updateButton.setOnClickListener {
            //saveSeatLayout()
            val intent = Intent(this, LoginMainActivity21::class.java)
            startActivity(intent)
            //finish()
        }


    }

    private fun generateSeats() {
        val seatNumber = seatNumberEditText.text.toString().toIntOrNull() ?: return
        seatLayout.removeAllViews()
        seatLayoutData.clear()

        val size = resources.getDimensionPixelSize(R.dimen.seat_size)

        val margin = resources.getDimensionPixelSize(R.dimen.seat_margin)
        val containerWidth = (resources.displayMetrics.density * 300).toInt()
        val containerHeight = containerWidth
        val totalColumns = containerWidth / (size + margin)
        val totalRows = containerHeight / (size + margin)
        val offsetX = (containerWidth - totalColumns * (size + margin) + margin) / 2
        val offsetY = (containerHeight - totalRows * (size + margin) + margin) / 2

        for (i in 0 until seatNumber) {
            val seat = layoutInflater.inflate(R.layout.seat_item, seatLayout, false)
            val seatNumberSpan = seat.findViewById<TextView>(R.id.seat_number_span)

            val sizePx = resources.getDimensionPixelSize(R.dimen.seat_size)
            val layoutParams = FrameLayout.LayoutParams(sizePx, sizePx)

            val column = i % totalColumns
            val row = i / totalColumns
            val left = offsetX + column * (sizePx + margin)
            val top = offsetY + row * (sizePx + margin)

            layoutParams.setMargins(left, top, 0, 0)
            seat.layoutParams = layoutParams
            seat.setBackgroundColor(Color.parseColor("#FF589C85"))
            seatNumberSpan.text = (i + 1).toString()
            seat.setOnClickListener { startDrag(it) }
            seatLayout.addView(seat)

            seatLayoutData.add(SeatData(i, left, top))

            // 좌석을 드래그하는 기능을 처리하는 리스너
            val seatTouchListener = object : View.OnTouchListener {
                private var initialX = 0f
                private var initialY = 0f
                private var initialTouchX = 0f
                private var initialTouchY = 0f

                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            initialX = view.x
                            initialY = view.y
                            initialTouchX = event.rawX
                            initialTouchY = event.rawY
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val offsetX = event.rawX - initialTouchX
                            val offsetY = event.rawY - initialTouchY
                            view.x = initialX + offsetX
                            view.y = initialY + offsetY
                        }
                        MotionEvent.ACTION_UP -> {
                            // 드래그 앤 드랍 완료 시 추가 처리할 내용
                        }
                    }
                    return true
                }
            }

// generateSeats() 함수 내의 for 루프 안에 다음 코드를 추가하여 seat 요소에 리스너를 설정
            seat.setOnTouchListener(seatTouchListener)

        }
    }

    private fun saveSeatLayout() {
        // 좌석 배치 저장 기능 구현
        for (i in 0 until seatLayoutData.size) {
            val seat = seatLayout.getChildAt(i)
            val seatId = seat.tag as Int
            seatLayoutData[seatId].left = seat.left
            seatLayoutData[seatId].top = seat.top
        }

        // 저장된 좌석 배치 데이터 활용
        for (seatData in seatLayoutData) {
            // 좌석 데이터 활용하여 저장 또는 전송
            println("Seat ID: ${seatData.id}, Left: ${seatData.left}, Top: ${seatData.top}")
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun startDrag(view: View) {
        selectedSeat = view
        val location = IntArray(2)
        selectedSeat?.getLocationOnScreen(location)
        val rect = Rect(location[0], location[1], location[0] + selectedSeat!!.width, location[1] + selectedSeat!!.height)
        initialOffsetX = view.left - rect.left
        initialOffsetY = view.top - rect.top

        seatLayout.setOnTouchListener { _, event ->
            if (selectedSeat != null) {
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = event.rawX.toInt() - seatLayout.left - initialOffsetX
                        val y = event.rawY.toInt() - seatLayout.top - initialOffsetY
                        moveSeat(selectedSeat!!, x, y)
                    }
                    MotionEvent.ACTION_UP -> {
                        selectedSeat = null
                    }
                }
                true
            } else {
                false
            }
        }
    }

    private fun moveSeat(view: View, x: Int, y: Int) {
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        layoutParams.leftMargin = x
        layoutParams.topMargin = y
        view.layoutParams = layoutParams
    }

    private data class SeatData(val id: Int, var left: Int, var top: Int)
}

