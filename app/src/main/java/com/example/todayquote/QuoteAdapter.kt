package com.example.todayquote

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class QuoteAdapter(private val dataList: List<Quote>):
    RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>()
{
    class QuoteItemViewHolder(val view : View ) : RecyclerView.ViewHolder(view){  //view를 홀더하는 것을 본질적인 역할이다.
        lateinit var quote: Quote
        val quoteText = view.findViewById<TextView>(R.id.quote_text) //타입 : TextView
        val quoteFrom = view.findViewById<TextView>(R.id.quote_from) //타입 : TextView
        val shareBtn = view.findViewById<Button>(R.id.share) //타입 : Button

        init {
            shareBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND) // 보낸다는 의미
                intent.putExtra(Intent.EXTRA_TITLE, "힘이 되는 명언")
                intent.putExtra(Intent.EXTRA_SUBJECT, "힘이 되는 명언")
                intent.putExtra(Intent.EXTRA_TEXT, "${quote.text}\n출처 : ${quote.from}")
                intent.type = "text/plain"
                val chooser = Intent.createChooser(intent, "명언 공유")
                it.context.startActivity(chooser)
            }
        }

        fun bind(q: Quote){ //명언 데이터와 view를 결속을 시킬 것이다.
            this.quote = q

            quoteText.text = quote.text
            quoteFrom.text = quote.from
        }

    }

    //식별자 veiwGroup를 받은 후 quote_list_item의 내용을 받아오는 것
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        val view = LayoutInflater.from(parent.context) //parent 뷰그룹으로 리사이클러뷰이다.
            .inflate(viewType, parent, false) //viewType는 XML, parent리싸이클러뷰에 붙일 것이다 .

        return QuoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    //    override fun getItemCount(): Int { //호출하면 전체 아이템에 들어갈 총 개수가 나오는 것
//        return dataList.size
//    }
    override fun getItemCount() = dataList.size

    //    override fun getItemViewType(position: Int): Int {
//        val data = dataList[position]
//        return R.layout.quote_list_item
//    }
    override fun getItemViewType(position: Int) = R.layout.quote_list_item

}
