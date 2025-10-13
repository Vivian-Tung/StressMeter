package dev.viviantung.stressmeter.ui.results

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.viviantung.stressmeter.CsvHelper
import java.io.File

class ResultsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "A graph showing you Stress Levels"
    }
    private val _scores = MutableLiveData<List<Pair<String, Int>>>()


    val text: LiveData<String> = _text

    val scores: LiveData<List<Pair<String, Int>>> = _scores

    // i think i need to put the data here?
    fun loadScores(context: Context) {
        val data = CsvHelper.readScores(context)
        _scores.value = data
    }

    // add new score
    fun addScore(context: Context, score: Int) {
        CsvHelper.writeScore(context, score) // write new score
        loadScores(context) // load it again
    }
}