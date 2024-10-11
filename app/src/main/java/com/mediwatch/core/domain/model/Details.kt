package com.mediwatch.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Details(
    val id: Int,
    val name: String,
    val imageUrl: String
) : Parcelable