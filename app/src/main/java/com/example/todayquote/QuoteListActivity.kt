package com.example.todayquote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_list_activity)

        val size = intent.getIntExtra("quote_size",-1)
        Log.d("mytag",size.toString())

        val pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        // 준비물 1 -> 리스트에 포함될 데이터
        val quotes = Quote.getQuotesFromPreference(pref)
        
        // 준비물 2 -> 리스트에 포함될 개별 데이터를 표시할 XML 레이아웃 파일 (한 항목에 대한 레이아웃)
        
        //준비물 3 -> 리스트에 포함될 각 항목이 표시될 방식을 관리할 레이아웃 매니저
        val layoutManager = LinearLayoutManager(this)
        val quoteList = findViewById<RecyclerView>(R.id.quote_list)

        // 준비물 4 -> 리스트에 포함될 각 데이터를 보여줄 뷰 객체를 보관할 뷰 홀더 클래스
        // 준비물 5 -> 리스트에 포함될 리스트 데이터와 레이아웃 파일을 연결하는 등 총체적인 관리 기능을 제공할 어댑터 클래스
        val adapter = QuoteAdapter(quotes)
        quoteList.setHasFixedSize(false)
        quoteList.layoutManager = layoutManager
        quoteList.adapter = adapter

    }
}
