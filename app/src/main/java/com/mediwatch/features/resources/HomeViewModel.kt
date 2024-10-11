package com.mediwatch.features.resources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediwatch.core.data.repositories.MainRepository
import com.mediwatch.core.domain.model.AssociatedDrug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val resourceRepository: MainRepository,
) : ViewModel() {
	var resourceResult = listOf<AssociatedDrug>()
	private val _drugs = MutableStateFlow<List<AssociatedDrug>>(emptyList())
	val drugs = _drugs.asStateFlow()
	
	init {
		viewModelScope.launch {
			resourceResult = resourceRepository.getResources()
		}
	}
	
	fun loadDrugs() {
		viewModelScope.launch {
			val result = resourceRepository.loadJsonData()
			_drugs.value = result
		}
	}
}