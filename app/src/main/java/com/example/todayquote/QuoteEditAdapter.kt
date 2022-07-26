package com.example.todayquote

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Quote -> 명언
class QuoteEditAdapter(val dataList: List<Quote>)
    : RecyclerView.Adapter<QuoteEditAdapter.QuoteItemViewHolder>() {
    class QuoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var quote: Quote
        val quoteTextEdit = view.findViewById<EditText>(R.id.quote_text_edit)
        val quoteFromEdit = view.findViewById<EditText>(R.id.quote_from_edit)
        val quoteDeleteBtn = view.findViewById<Button>(R.id.quote_delete_btn)
        val quoteModifyBtn = view.findViewById<Button>(R.id.quote_modify_btn)

        // 초기화 블럭
        init{
            // context 객체로 호출할 수 있음
            val pref = view.context.getSharedPreferences("quotes", Context.MODE_PRIVATE)
            // 삭제 버튼
            quoteDeleteBtn.setOnClickListener {
                // (1) 삭제 관련 토스트 메시지 띄우기
                Toast.makeText(it.context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                // (2) EditText에 있는 내용 제거 (빈 문자열로 내용 채우기)
                quoteTextEdit.setText("")
                quoteFromEdit.setText("")
                
                // (3) 프리퍼런스에도 지웠음
                Quote.removeQuoteFromPreference(pref, adapterPosition)

                quote.text = ""
                quote.from = ""
            }
            quoteModifyBtn.setOnClickListener {
                Toast.makeText(it.context, "수정되었습니다.", Toast.LENGTH_SHORT).show()

                val newQuoteText = quoteTextEdit.text.toString()
                val newQuoteFrom = quoteFromEdit.text.toString()

                Quote.saveToPreference(pref, adapterPosition, newQuoteText, newQuoteFrom)

                quote.text = newQuoteText
                quote.from = newQuoteFrom
            }
        }
        fun bind(q: Quote) {
            quote = q
            quoteTextEdit.setText(quote.text)
            quoteFromEdit.setText(quote.from)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        Log.d("mytag", "onCreateViewHolder")

        // LayoutInflater <=
        val view = LayoutInflater.from(parent.context) // parent 뷰그룹으로 리사이클러뷰이다.
                                .inflate(viewType, parent, false) // viewType는 XML, parent리사이클러뷰에 붙일 것이다.

        return QuoteEditAdapter.QuoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        Log.d("mytag", position.toString())
        holder.bind(dataList[position])

    }

    // 목록에 들어가야할 개수
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.quote_edit_list_item
    }
}

// 명시적(explicit) 인텐트 vs 암시적(implicit) 인텐트
// 명시적 인텐트 => 목적지가 분명히 있는 인텐트가 명시적 인텐트
// 목적지가 분명하다
// ex : 액티비티 이동용 인텐트, B라는 서비스를 시작하는 인텐트
// 목적지가 있는데 불명확하다
// 암시적 인텐트 => 목적지가 있긴 한데, 명확하지 않은 인텐트가 암시적 인텐트
//
// ex : 공유