package com.example.laboratorio6.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val DATABASE_NAME = "Data"
val TABLE_NAME = "Survey"
val COL_QUESTION = "questions"
val COL_TYPE = "type"
val COL_ANSWER = "answer"
val COL_ID = "id"

class DataBaseSurvey(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME+" ("+
                COL_ID +" INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                COL_QUESTION + " VARCHAR(256),"+
                COL_TYPE + " VARCHAR(256),"+
                COL_ANSWER + " VARCHAR(256))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    // Metiendo a la base de datos
    fun insertData(survey: Survey){
        val dataBase = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(COL_QUESTION, survey.question)
        contentValues.put(COL_TYPE, survey.type)
        contentValues.put(COL_ANSWER, survey.answer)

        var result = dataBase.insert(TABLE_NAME, null, contentValues)

        if (result == -1.toLong())
            Log.i("SKLFJSDLKFJDSLKF", "FAILEEDDDDDDDD")
        else
            Log.i("SKLFJSDLKFJDSLKF", "SUCCESSSSSSSSS")
    }

    // Consiguiendo los resultados de las preguntas
    fun readData(): MutableList<Survey>{
        var list: MutableList<Survey> = ArrayList()

        val dataBase = this.readableDatabase
        val query = "SELECT * from" + TABLE_NAME
        val result = dataBase.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var survey = Survey()
                survey.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                survey.question = result.getString(result.getColumnIndex(COL_QUESTION))
                survey.type = result.getString(result.getColumnIndex(COL_TYPE))
                survey.answer = result.getString(result.getColumnIndex(COL_ANSWER))
                list.add(survey)
            }while(result.moveToNext())
        }

        result.close()
        dataBase.close()

        return list
    }

    // Actualizando datos
    fun updateData(){
        val dataBase = this.writableDatabase
        val query = "SELECT * from" + TABLE_NAME
        val result = dataBase.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var contentValue = ContentValues()
                contentValue.put(COL_ANSWER, result.getString(result.getColumnIndex(COL_ANSWER)))
                dataBase.update(TABLE_NAME, contentValue, COL_ID + "=? AND" + COL_QUESTION + "+?",
                    arrayOf(
                        result.getString(result.getColumnIndex(COL_ID)),
                        result.getString(result.getColumnIndex(COL_QUESTION))
                    ))
            }while(result.moveToNext())
        }

        result.close()
        dataBase.close()

    }

    // Borrando de la base de datos
    fun deleteData(){
        val dataBase = this.writableDatabase
        dataBase.delete(TABLE_NAME, null, null)
        dataBase.close()
    }

}