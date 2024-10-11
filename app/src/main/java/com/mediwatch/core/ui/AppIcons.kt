package com.mediwatch.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.mediwatch.R

object AppIcons {
    val Home = R.drawable.ic_home
    val HomeBorder = R.drawable.ic_home_border
    val Resources = R.drawable.ic_resources
    val ResourcesBorder = R.drawable.ic_resources
    val Person = Icons.Rounded.Person
    val Settings = Icons.Rounded.Settings
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}