package dev.viviantung.stressmeter

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CsvHelper {

    private fun getFile(context: Context): File {
        return File(context.filesDir, "stress_timestamp.csv")
    }

    // write to csv
    fun writeScore(context: Context, score: Int) {
        val file = getFile(context)
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val line = "$timestamp,$score\n"

        file.appendText(line)
    }

    // read from csv
    fun readScores(context: Context): List<Pair<String, Int>> {
        val file = getFile(context)
        if (!file.exists()) return emptyList()

        return file.readLines().mapNotNull { line ->
            val parts = line.split(",")
            if (parts.size == 2) {
                val time = parts[0]
                val score = parts[1].toIntOrNull()
                if (score != null) Pair(time, score) else null
            } else null
        }
    }
}