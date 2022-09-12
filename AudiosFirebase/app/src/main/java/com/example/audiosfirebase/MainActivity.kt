package com.example.audiosfirebase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_SPEECH_INPUT = 100
    val database = Firebase.database
    val myRef = database.getReference("message")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        ib_micro.setOnClickListener{

            speak()
        }





    }

    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "habla")

        try {
            startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)
        }catch (e: Exception){

            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()



        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT ->{
                if (resultCode == Activity.RESULT_OK && null != data){
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    tv_audio_text.text = result!![0]
                    myRef.setValue(result!![0])

                }

            }
        }
    }
}