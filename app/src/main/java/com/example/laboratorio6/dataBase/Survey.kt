package com.example.laboratorio6.dataBase

class Survey {

    var id:Int = 0
    var question:String = ""
    var type:String  = ""
    var answer:String = ""

    constructor(){
    }

    constructor(question:String, type:String, answer:String){
        this.question = question
        this.type = type
        this.answer = answer
    }

}