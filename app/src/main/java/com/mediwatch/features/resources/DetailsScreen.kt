package com.mediwatch.features.resources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mediwatch.core.domain.model.Details


@Composable
internal fun DetailsRoute(
	modifier: Modifier = Modifier,
	viewModel: DetailsViewModel = hiltViewModel(),
	onBackClick: () -> Unit,
	resourceId: String?
) {
//	ResourceDetailsScreen(, onBackClick)
}

@Composable
fun DetailsScreen(resource: Details, onBackClick: () -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(20.dp),
		verticalArrangement = Arrangement.Center
	) {
		Text(text = resource.name)
		Text(text = resource.name)
		Text(text = resource.name)
		Text(text = resource.name)
	}
	
}