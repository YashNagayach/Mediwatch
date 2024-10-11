package com.mediwatch.core.pagination.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resources")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)