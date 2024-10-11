package com.mediwatch.core.data.repositories

import android.content.Context
import androidx.datastore.preferences.protobuf.Api
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mediwatch.core.domain.model.AssociatedDrug
import com.mediwatch.core.domain.model.Problems
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import javax.inject.Inject

class MainRepository @Inject constructor(
	@ApplicationContext private val context: Context,
	private val api: Api
) {
	
	suspend fun loadJsonData(): List<AssociatedDrug> {
		return withContext(Dispatchers.IO) {
			
			val inputStream = context.assets.open("data.json")
			val reader = InputStreamReader(inputStream)
			val gson = Gson()
			
			// Parse the entire JSON into the Problems object
			val type = object : TypeToken<Problems>() {}.type
			val problems: Problems = gson.fromJson(reader, type)
			
			// Extract the associated drugs
			val associatedDrugs = problems.problems.flatMap { problem ->
				problem.diabetes?.flatMap { diabetes ->
					diabetes.medications?.flatMap { medication ->
						medication.medicationsClasses?.flatMap { medClass ->
							medClass.className?.flatMap { className ->
								className.associatedDrug ?: emptyList()
							} ?: emptyList()
						} ?: emptyList()
					} ?: emptyList()
				} ?: emptyList()
			}
			
			associatedDrugs
		}
	}
	
	suspend fun getResources(): List<AssociatedDrug> {
		val list = mutableListOf<AssociatedDrug>()
		for (i in 0..30) {
			list.add(AssociatedDrug(i, "Dose$i", "name$i", "strength$i"))
		}
		return list
	}
	
	suspend fun getMedicineDetails(id: Int): AssociatedDrug {
		return AssociatedDrug(id, "Dose$id", "name$id", "strength$id")
	}
	
	
}