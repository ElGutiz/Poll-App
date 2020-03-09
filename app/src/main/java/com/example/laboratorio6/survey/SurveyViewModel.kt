package com.example.laboratorio6.survey

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio6.dataBase.DataBaseSurvey
import com.example.laboratorio6.dataBase.Survey

class SurveyViewModel: ViewModel() {

    //Pregunta que se le mostrara al usuario
    private val _showQuestion = MutableLiveData<String>()
    val showQuestion: LiveData<String>
        get() = _showQuestion

    // Tipo de la pregunta
    var questionType: String
    var questionLeft: Int = 1

    //Tipo
    var newType: String = "Text"

    // Las que no se borran
    var allQuestions: ArrayList<String> = arrayListOf("Do you have any Comments or Suggestions?", "How was our Service?")
    var allTypes: ArrayList<String> = arrayListOf("Text", "Rating")

    init {
        questionType = allTypes[questionLeft - 1]
        _showQuestion.value = allQuestions[questionLeft - 1]
    }

    fun addQuesiton(context: Context?, newQuestion:String){
        allQuestions.add(0, newQuestion)
        allTypes.add(0, newType)

        // Metiendolo a la base de datos
        val survey = Survey(newQuestion, newType, "")
        val dataBase = DataBaseSurvey(context!!)
        dataBase.insertData(survey)

        newType = "Text"
    }

    fun nextQuestion(){
        this.questionLeft++
        _showQuestion.value = allQuestions[this.questionLeft - 1]
        questionType = allTypes[this.questionLeft - 1]
    }

    fun restartQuestions(){
        this.questionLeft = 1
    }

}