package com.example.studioquizz

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_quizz.*

class QuizzActivity : AppCompatActivity() {

    var quizzes = ArrayList<Quizz>()
    var numberOfRightAnswers: Int = 0
    var currentQuizzIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        quizzes.add(Quizz(getString(R.string.firstQuestion), getString(R.string.firstdAnswer1), getString(R.string.firstdAnswer2), getString(R.string.firstdAnswer3), 2))
        quizzes.add(Quizz(getString(R.string.secondQuestion), getString(R.string.secondAnswer1), getString(R.string.secondAnswer2), getString(R.string.secondAnswer3), 3))
        quizzes.add(Quizz(getString(R.string.thirdQuestion), getString(R.string.thirdAnswer1), getString(R.string.thirdAnswer2), getString(R.string.thirdAnswer3), 2))
        quizzes.add(Quizz(getString(R.string.fourthQuestion), getString(R.string.fourthAnswer1), getString(R.string.fourthAnswer2), getString(R.string.fourthAnswer3), 1))

        showQuestion(quizzes.get(currentQuizzIndex))

    }

    fun showQuestion(quizz: Quizz){
        txtQuestion.setText(quizz.question)
        txtAnswer1.setText(quizz.answer1)
        txtAnswer2.setText(quizz.answer2)
        txtAnswer3.setText(quizz.answer3)
    }
    // fonction pour gérer les réponses, et passer à la question suivante
    fun handleAnswer(answerId: Int){
        val quizz = quizzes.get(currentQuizzIndex)

        if(quizz.isCorrect(answerId)){
            numberOfRightAnswers++
            Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Mauvaise réponse", Toast.LENGTH_SHORT).show()
        }

        currentQuizzIndex++

        if(currentQuizzIndex >= quizzes.size){
            val sharedPreferences = getSharedPreferences("com.example.studioquizz", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("userScore", numberOfRightAnswers).apply()

            var alert = AlertDialog.Builder(this)
            alert.setTitle(("Partie terminée!"))
            alert.setMessage("Nombre de point(s) : $numberOfRightAnswers")
            alert.setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                finish()
            }
            alert.show()
        }
        else{
            showQuestion(quizzes.get(currentQuizzIndex))
        }
    }

    fun onClickAnswer1(view: View){

        handleAnswer(1)
    }

    fun onClickAnswer2(view: View){

        handleAnswer(2)
    }

    fun onClickAnswer3(view: View){

        handleAnswer(3)
    }


}
