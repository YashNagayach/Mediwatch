package com.mediwatch.features.resources

import androidx.lifecycle.ViewModel
import com.mediwatch.core.domain.model.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
) : ViewModel(){
	val resourceDetails = MutableStateFlow(Details(-1, "", ""))
	
	fun getResourceDetails(id: Int) {
	
	}
	
}