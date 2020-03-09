package com.example.laboratorio6.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultsViewModel: ViewModel() {

    private val _results = MutableLiveData<Float>()
    val obtainedResults: LiveData<Float>
        get() = _results

    private val _numberSurvey = MutableLiveData<Int>()
    val numberSurvey: LiveData<Int>
        get() = _numberSurvey

    private val _answersString = MutableLiveData<String>()
    val answersString: LiveData<String>
        get() = _answersString

    private var answers: ArrayList<String> = ArrayList()

    init {
        _numberSurvey.value = 1
        _results.value = 0F
    }

    fun add(inputAnswer: String){
        answers.add(inputAnswer)
    }

    fun newSurvey(){
        _numberSurvey.value = (_numberSurvey.value)?.plus(1)
    }

    fun getAnswers(){
        var answers = "Tus Respuestas:\n"

        for (i in answers){
            answers += i + "\n"
        }

        this._answersString.value = answers
    }
}