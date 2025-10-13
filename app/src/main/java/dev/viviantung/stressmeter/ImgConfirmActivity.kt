package dev.viviantung.stressmeter

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

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
            // there is image, display it
            imgView.setImageResource(img)
            Toast.makeText(this, "Score: $score", Toast.LENGTH_SHORT).show()
        }

        submitButton.setOnClickListener() {
            // save the score into csv
            CsvHelper.writeScore(this, score)
            finishAffinity()
        }

        cancelButton.setOnClickListener() {
            finish();
        }
    }
}