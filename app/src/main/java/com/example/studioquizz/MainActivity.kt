package com.example.studioquizz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("com.example.studioquizz", Context.MODE_PRIVATE)

        val userScore = sharedPreferences.getInt("userScore", -1)

        if(userScore > -1){
            scoreMain.text = getString(R.string.existentScore) +" " + userScore
        }
        else{
            scoreMain.text = getString(R.string.voidScore)
        }
    }

    fun onClickbtnPlay(view:View){
       val intent = Intent(this, QuizzActivity::class.java)
        startActivity(intent)
    }
}
