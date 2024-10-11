package com.mediwatch.core.pagination.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mediwatch.core.domain.model.AssociatedDrug
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(resource: List<AssociatedDrug>)
	
	@Query("SELECT * FROM associated_drugs")
	fun getAllResources(): Flow<List<AssociatedDrug>>
	
	@Query("DELETE FROM associated_drugs")
	suspend fun clearAllResources()
}