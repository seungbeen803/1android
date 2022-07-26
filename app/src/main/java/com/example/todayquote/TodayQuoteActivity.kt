package com.example.todayquote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class TodayQuoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_todayactivity)

        // < > 괄호 안에는 Id값을 준 것의 이름을 적어야한다
        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val operator = findViewById<EditText>(R.id.operator)
        val calcBtn = findViewById<Button>(R.id.calcBtn)

        // num1, num2, operator 값 출력하게하기
        calcBtn.setOnClickListener {
            Log.d("mytag", num1.text.toString())
            Log.d("mytag", num2.text.toString())
            Log.d("mytag", operator.text.toString())

            // 자료현 변환 안 가르침 => 구글 검색 "코틀린" "자료형" "변환"
            // 자료형 변환해서 적절하게 두 변수에 저장하기
            val n1: Int = num1.text.toString().toInt()
            val n2: Int = num2.text.toString().toInt()

            // ToDo : 새 액티비티 (CalculateResultActivity) 만들고
            // Intent랑 startActivity 이용해서 해당 액티비로 이동하게 만들기
            // 그 과정에서 putExtra로 인텐트에 n1, n2 연산자 문자열 전달
            val intent = Intent(this, CalculateResultActivity::class.java)
            intent.putExtra("num1", n1)
            intent.putExtra("num2", n2)
            intent.putExtra("operator", operator.text.toString())
            startActivity(intent)


        }
    }
}