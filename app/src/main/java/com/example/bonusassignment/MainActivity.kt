package com.example.bonusassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bonusassignment.PLJF.ParsingLocalJSONFile

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun moveToParsingLocalJSONFile(view: View) {
        startActivity(Intent(this, ParsingLocalJSONFile::class.java))
    }
}