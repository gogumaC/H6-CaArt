package org.softeer_2nd.caArt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.softeer_2nd.caArt.model.data.dto.ColorData
import org.softeer_2nd.caArt.model.repository.CarColorImageRepository
import org.softeer_2nd.caArt.util.CoilUtils
import org.softeer_2nd.caArt.util.StringFormatter
import org.softeer_2nd.caArt.util.StringFormatter.extractExteriorPreviewBaseUrl
import javax.inject.Inject


@HiltViewModel
class CarColorChoiceViewModel @Inject constructor(
    val imageRepository: CarColorImageRepository
) : ViewModel() {

    private val _colorData = MutableLiveData<ColorData>()
    val colorData: LiveData<ColorData> = _colorData

    private val _spinCarImageIndex = MutableLiveData<Int>(0)
    val spinCarImageIndex: LiveData<Int> = _spinCarImageIndex

    private val _spinActive = MutableLiveData<Boolean>(false)
    val spinActive: LiveData<Boolean> = _spinActive

    val currentExteriorColorImage = MediatorLiveData<String>()

    private val _currentExteriorUrls = MutableStateFlow<List<String>?>(null)
    val currentExteriorUrls: Flow<List<String>?> = _currentExteriorUrls

    init {
        currentExteriorColorImage.addSource(spinCarImageIndex) { index ->
            val urls = _currentExteriorUrls.value

            if (urls != null && index in urls.indices) {
                val color = StringFormatter.extractColorFromUrl(urls[index])
                val baseUrl = urls[index].extractExteriorPreviewBaseUrl()
                val imageUrl = "${baseUrl}$color/image_${StringFormatter.getImageUrlFromIndex(index)}.png"
                currentExteriorColorImage.value = imageUrl
            }
        }

        viewModelScope.launch {
            currentExteriorUrls.collect { urls ->
                imageRepository.preloadExteriorImages(urls)
            }
        }
    }

    fun getImages(trimId: Int) {
        viewModelScope.launch {
            _colorData.value = imageRepository.fetchColorList(trimId)
        }
    }

    fun updateIndex(newIndex: Int) {
        val index = when {
            newIndex < 0 -> 60 + newIndex
            newIndex >= 60 -> newIndex - 60
            else -> newIndex
        }
        _spinCarImageIndex.value = index
    }

    fun activateSpinImage() {
        _spinActive.value = true
    }

    fun updateCurrentExteriorUrls(index: Int) {
        _currentExteriorUrls.value = colorData.value?.exteriorColors?.get(index)?.previews
        _spinCarImageIndex.value = 0
    }
}
