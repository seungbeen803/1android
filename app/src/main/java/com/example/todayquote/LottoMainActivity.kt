package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class LottoMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lotto_main_activity)

        var randoms = findViewById<TextView>(R.id.lottoList)
        // mutableListOf<>() -> 가변 배열 내부적으로는 ArrayList()
        var random = mutableListOf<Int>()
        for(i in 1..6) random.add((1..45).random())
        randoms.text = "${random[0]}-${random[1]}-${random[2]}-${random[3]}-${random[4]}-${random[5]}"

    }
}