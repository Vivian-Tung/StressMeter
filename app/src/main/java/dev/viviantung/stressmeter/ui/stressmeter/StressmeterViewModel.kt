package dev.viviantung.stressmeter.ui.stressmeter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.viviantung.stressmeter.ImageData

class StressmeterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Touch the image that best captures how stressed you feel right now"
    }
    val text: LiveData<String> = _text

    // display selected images
    private var _selectedImages: IntArray? = null
    val selectedImages: IntArray
        get() {
            if (_selectedImages == null) {
                _selectedImages = ImageData.imageIds.toList().shuffled().take(16).toIntArray()
            }
            return _selectedImages!!
        }

    // to get more images
    fun refreshImages() {
        _selectedImages = ImageData.imageIds.toList().shuffled().take(16).toIntArray()
    }
}