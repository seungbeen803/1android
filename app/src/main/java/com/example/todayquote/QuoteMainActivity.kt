package com.example.todayquote

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

//        data class Quote(주생성자)
data class Quote(var idx: Int, var text: String, var from: String = "") {
    //    fun method() {} // 객체가 있어야만 호출 가능
    // object {클래스 이름으로 호출할 수 있는 클래스 메서드}
    companion object {
        fun saveToPreference(
            pref: SharedPreferences,
            idx: Int,
            text: String,
            from: String = ""
        ) : Quote { // 반환 타입을 적어주자
            val editor = pref.edit()

            editor.putString("${idx}.text", text)
            editor.putString("${idx}.from", from.trim())

            editor.apply()

            // 반환 타입을 적어주지 않으면 오류가 발생한다
            return Quote(idx, text, from)
        }
        
        // MutableList<> : 내용을 바꿀 수 있는 리스트
        fun getQuotesFromPreference(pref: SharedPreferences) : MutableList<Quote> {
            val quotes = mutableListOf<Quote>()
            
            // until : 0~20까지의 범위에서 20은 포함하지않는다
            // .. : 0~20까지의 범위에서 20을 포함한다
            for(i in 0 until 20) {
                val quoteText = pref.getString("${i}.text", "")!!
                val quoteFrom = pref.getString("${i}.from", "")!!
                // isNotBlank : 내용이 비어있는지 비어있지않은지 구분
                if (quoteText.isNotBlank()) {
                    quotes.add(Quote(i, quoteText, quoteFrom))
                }
            }
            return quotes
        }

        // 삭제하는 것
        fun removeQuoteFromPreference(pref : SharedPreferences, idx: Int){
            val editor = pref.edit()

            editor.remove("${idx}.text")
            editor.remove("${idx}.from")

            editor.apply()
        }
    }
}

class QuoteMainActivity : AppCompatActivity() {
//    액티비티는 생성자 호출을 우리가 할 수 없고, 운영체제가 수행해주므로
//    생성자에서 해당 값을 초기화를 못 시켜주니까 lateinit으로 해서
//    나중에 해당 값이 사용 전 반드시 초기화 됨을 약속함
    // 나중에 초기화 : lateinit
    private lateinit var quotes: MutableList<Quote>
    private lateinit var pref: SharedPreferences

    fun initializeQuotes() {
        val initialized = pref.getBoolean("initialized", false)
        if(!initialized) {
            // 적절한 초기 명언 저장하기 Quote.saveToPreference(pref, 0, "명언 1", " 출처 1" )
            Quote.saveToPreference(pref, 0, "명언 1", " 출처 1" )
            Quote.saveToPreference(pref, 1, "명언 2", " 출처 2" )
            Quote.saveToPreference(pref, 2, "명언 3", " 출처 3" )

            val editor = pref.edit()
            editor.putBoolean("initialzed", true)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_main_activity)

        pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        // initialize : 초기화하다
        initializeQuotes()

        var quote = findViewById<TextView>(R.id.quote)
//        quote.text = "코드로 바꿈"
        var quote1 = findViewById<TextView>(R.id.quote1)
//        quote1.text = "명언 맨"

        quotes = Quote.getQuotesFromPreference(pref)

        if(quotes.isNotEmpty()) {
            val idx = Random().nextInt(quotes.size)
            val q = quotes[idx]
            quote.text = q.text
            quote1.text = q.from
        } else {
            quote.text = "저장된 명언이 없습니다."
            quote1.text = ""
        }

        var editButton = findViewById<Button>(R.id.quote_edit_button)
        editButton.setOnClickListener {
            val intent = Intent(this, QuoteEditActivity::class.java)
            startActivity(intent)
        }
        var listButton = findViewById<Button>(R.id.quote_list_button)
        listButton.setOnClickListener {
            val intent = Intent(this, QuoteListActivity::class.java)
            startActivity(intent)
        }

//        ArrayList => 가변 배열
//        quotes = mutableListOf()
//        // var nums = listOf(1, 2, 3)
//
//        var q1 = Quote(1, "새로운 일에 도전하다 보면 가끔 실수를 저지를 수 있다. 자신의 실수를 빨리 인정하고 다른 시도에 집중하는 것이 최선이다. ", "스티브 잡스")
//        quotes.add(q1)
//        quotes.add(Quote(2, "만족할 줄 아는 사람은 진정한 부자이고, 탐욕스러운 사람은 진실로 가난한 사람이다.", "솔론"))
//        quotes.add(Quote(3, "피할 수 없으면 즐겨라", "로버트 엘리엇"))
//
//        // import java.util.*
////      nextInt : 호출할 때마다 랜덤한 값을 나오도록 함
//        val randomIndex = Random().nextInt(quotes.size) // 2까지
//        val randomQuote = quotes[randomIndex]
//
//        quote.text = randomQuote.text
//        quote1.text = randomQuote.from


    }
}