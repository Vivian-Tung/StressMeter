package dev.viviantung.stressmeter

import android.content.Context
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import dev.viviantung.stressmeter.ui.results.ResultsViewModel

class ImgConfirmActivity:  AppCompatActivity()  {
    private lateinit var imgView: ImageView
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.img_confirm)
        val resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)

        imgView = findViewById(R.id.selectedImage)
        submitButton = findViewById(R.id.btnSubmit)
        cancelButton = findViewById(R.id.btnCancel)

        val score = intent.getIntExtra("score", 0)
        val img = intent.getIntExtra("image", 0)

        if (img != 0) {
            // there is image, display it
            imgView.setImageResource(img)
        }

        submitButton.setOnClickListener() {
            // save the score into csv
            resultsViewModel.addScore(this, score)
            finishAffinity()
        }

        cancelButton.setOnClickListener() {
            finish();
        }
    }
}