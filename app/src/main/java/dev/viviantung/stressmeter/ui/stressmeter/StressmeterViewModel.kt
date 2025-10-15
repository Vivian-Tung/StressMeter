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

    var currentBatch = 0  // starts at batch 0
    val imagesPerBatch = 16
    val totalBatches = ImageData.imageIds.size / imagesPerBatch

    fun getCurrentBatch(): IntArray {
        val start = currentBatch * imagesPerBatch // start index
        val end = minOf(start + imagesPerBatch, ImageData.imageIds.size) // either end of batch or end of total
        _selectedImages = ImageData.imageIds.sliceArray(start until end) // slice
        return _selectedImages!!
    }

    // to get more images
    fun loadNextBatch() {
        currentBatch = (currentBatch + 1) % totalBatches
        getCurrentBatch()
    }
}