package com.example.bonusassignment.PLJF

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bonusassignment.MainActivity
import com.example.bonusassignment.R
import org.json.JSONArray
import java.lang.Exception

class ParsingLocalJSONFile : AppCompatActivity() {

    private lateinit var rvImages: RecyclerView
    private lateinit var adapter: ImageRecyclerViewAdapter
    private lateinit var imagesList: MutableList<ImageData>
    private lateinit var openImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parsing_local_j_s_o_n_file)

        rvImages = findViewById(R.id.rvImages)
        imagesList = mutableListOf()
        openImage = findViewById(R.id.open_image)
        adapter = ImageRecyclerViewAdapter(imagesList, this)
        rvImages.adapter = adapter
        rvImages.layoutManager = LinearLayoutManager(this)

        fetchData()

        openImage.setOnClickListener {
            openImage.visibility = View.GONE
            rvImages.visibility = View.VISIBLE
        }

    }

    private fun fetchData() {
        val response = try {
            this@ParsingLocalJSONFile.assets.open("Data.json").bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            ""
        }
        if (response.isNotEmpty())
            setData(response)

    }

    private fun setData(response: String) {
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {

            val date = jsonArray.getJSONObject(i).getString("date")
            val explanation = jsonArray.getJSONObject(i).getString("explanation")
            val hdurl = jsonArray.getJSONObject(i).getString("hdurl")
            val title = jsonArray.getJSONObject(i).getString("title")
            val copyright = jsonArray.getJSONObject(i).getString("media_type")

            imagesList.add(ImageData(copyright, date, explanation, hdurl, title))
        }
        adapter.notifyDataSetChanged()
        Toast.makeText(this, imagesList.size.toString(), Toast.LENGTH_LONG).show()
    }

    fun openDialog(explanation: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Explanation")

        val txt = TextView(this)
        txt.text = explanation

        builder.setView(txt)
        builder.create().show()
    }

    fun openImage(url: String){
        Glide.with(this).load(url).into(openImage)
        openImage.visibility = View.VISIBLE
        rvImages.visibility = View.GONE
    }
}