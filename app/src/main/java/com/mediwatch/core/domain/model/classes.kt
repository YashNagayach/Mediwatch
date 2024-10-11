package com.mediwatch.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Problems(
	val problems: List<Problem>
)

data class Problem(
	val diabetes: List<Diabetes>?,
	val asthma: List<Asthma>? // Add more diseases here in future
) {

}

data class Diabetes(
	val medications: List<Medication>?,
	val labs: List<Lab>?
)

data class Medication(
	val medicationsClasses: List<MedicationClass>?
)

data class MedicationClass(
	val className: List<ClassName>?,
	val className2: List<ClassName>?
)

data class ClassName(
	val associatedDrug: List<AssociatedDrug>?,
	val associatedDrug2: List<AssociatedDrug>?
)

@Entity(tableName = "associated_drugs")
data class AssociatedDrug(
	@PrimaryKey val id: Int,
	val name: String?,
	val dose: String?,
	val strength: String?
)

data class Lab(
	val missingField: String?
)

data class Asthma(
	val medications: List<Medication>?,
	val labs: List<Lab>?
)
