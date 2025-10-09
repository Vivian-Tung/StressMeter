package dev.viviantung.stressmeter

import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class ImgConfirmActivity:  AppCompatActivity()  {
    private lateinit var imgView: ImageView
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.img_confirm)

        imgView = findViewById(R.id.selectedImage)
        submitButton = findViewById(R.id.btnSubmit)
        cancelButton = findViewById(R.id.btnCancel)

        val score = intent.getIntExtra("score", 0)
        val img = intent.getIntExtra("image", 0)

        if (img != 0) {
            imgView.setImageResource(img)
        }







        submitButton.setOnClickListener() {
            // save the score into csv
            finishAffinity()

        }

        cancelButton.setOnClickListener() {
            finishAffinity()
        }
    }
}