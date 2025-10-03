package dev.viviantung.stressmeter.ui.stressmeter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StressmeterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is stressmeter Fragment"
    }
    val text: LiveData<String> = _text
}