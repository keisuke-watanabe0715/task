package com.websarva.wings.android.clock
//時計アプリを制作する

//必要なライブラリのインストール
import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Handle()でサブスレッドからメインスレッドへメッセージを受け渡す
        //Handler()には引数を渡す（引数無しだと上手く動かなかった）
        val handler = Handler(Looper.getMainLooper())

        //１秒ごとにタイマーでカウントする
        timer(name = "clock",period =1000){

            //カレンダークラスで時間・分・秒情報を取得
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)

            val post = handler.post {
                //画面部品を取得してtextViewに表示
                val test = findViewById<TextView>(R.id.textView)
                //時間の直後には「時」・分の直後には「分」・秒の直後には「秒」をつける
                test.text = "${hour}時 ${minute}分 ${second}秒"
            }
        }
    }
}



