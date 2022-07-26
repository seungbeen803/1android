package com.example.todayquote

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SharedPreferenceStudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference_study)

        // SharedPreferences : 데이터 타입(타입 추론)
        val sp : SharedPreferences = getSharedPreferences("file_name", Context.MODE_PRIVATE)
        // XML에 저장한다 : file_name.XML 파일이 생긴다

        val editor : SharedPreferences.Editor = sp.edit()
        
        // 영구적으로 데이터 저장
        // 설정 값 저장
        editor.putInt("key1", 1)
        editor.putFloat("key2", 3.14F)
        editor.putString("hello", "world")

        // 최종 저장(세이브 버튼의 역할을 한다)
        // apply 는 저장하는 메서드
        editor.apply()

        val key1Value = sp.getInt("key1", -1)
        val key2Value = sp.getFloat("key2", 0.0f)
        // nullable 타입임
        val helloValue = sp.getString("hello", "default")

        Log.d("tag_name", key1Value.toString())
        Log.d("tag_name", key2Value.toString())
        // !! : nullable 타입을 강제로 nullable 이 아닌 것으로 바꿔주는 것
        Log.d("tag_name", helloValue!!)

        val contains : Boolean = sp.contains("key2")
        editor.remove("key1")
        editor.clear()

        // 내부 구현은 다르지만 apply 와 commit 의 기능은 같다
        editor.apply()
        editor.commit()
    }
}