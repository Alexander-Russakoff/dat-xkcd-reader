package edu.temple.xkcdtemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    lateinit var titleTextView: TextView
    lateinit var altTextTextView: TextView
    lateinit var comicNumberEditText: EditText
    lateinit var loadComicButton: Button
    lateinit var comicDisplayImageView: ImageView
    lateinit var requestQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)

        titleTextView = findViewById(R.id.titleTextView)
        altTextTextView = findViewById(R.id.altTextTextView)
        comicNumberEditText = findViewById(R.id.comicNumberEditText)
        loadComicButton = findViewById(R.id.loadComicButton)
        comicDisplayImageView = findViewById(R.id.comicDisplayImageView)

        loadComicButton.setOnClickListener{
            val comicNumber = comicNumberEditText.text.toString()
            val url = "https://xkcd.com/$comicNumber/info.0.json"

            requestQueue.add(
            JsonObjectRequest(url,{
                                  Log.d("Json?????",it.toString())
                titleTextView.text = it.getString("title")
                altTextTextView.text = it.getString("alt")
                Picasso.get().load(it.getString("img")).into(comicDisplayImageView)

            },null)
            )
        }

    }
}