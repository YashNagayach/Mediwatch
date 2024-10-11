package com.mediwatch.features.resources

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mediwatch.core.domain.model.AssociatedDrug
import java.time.LocalTime


@Composable
internal fun ResourceRoute(
	modifier: Modifier = Modifier,
	viewModel: HomeViewModel = hiltViewModel(),
	onItemClick: (Int) -> Unit,
	navController: NavController
) {
	val drugList by viewModel.drugs.collectAsState()
	
	// Load the data when the screen is shown
	LaunchedEffect(Unit) {
		viewModel.loadDrugs()
	}
	val resourceResult = viewModel.resourceResult
	ResourceScreen(drugList, onItemClick, navController)
}

@Composable
fun ResourceScreen(
	resourceResult: List<AssociatedDrug>,
	onItemClick: (Int) -> Unit,
	navController: NavController,
) {
	val greetingMessage = Greeting.getGreetingByTime(LocalTime.now().hour).message
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(20.dp),
		verticalArrangement = Arrangement.Center
	) {
		Card(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			elevation = 8.dp,
			backgroundColor = MaterialTheme.colorScheme.surface
		) {
			Column(
				modifier = Modifier
					.padding(24.dp), // Add padding inside the Card
				verticalArrangement = Arrangement.spacedBy(8.dp) // Space between items
			) {
				// Greeting Message
				Text(
					text = greetingMessage,
					style = MaterialTheme.typography.bodyLarge,
					color = MaterialTheme.colorScheme.primary
				)
				
				// User Name
				Text(
					text = "Name: $globalUserName",
					style = MaterialTheme.typography.bodyLarge,
					color = MaterialTheme.colorScheme.onSurface
				)
				
				// User Password
				Text(
					text = "Password: $globalPassword",
					style = MaterialTheme.typography.bodyLarge,
					color = MaterialTheme.colorScheme.onSurface
				)
			}
		}
		LazyColumn(
			modifier = Modifier
				.padding(start = 16.dp, end = 16.dp, top = 10.dp)
				.systemBarsPadding(),
			verticalArrangement = Arrangement.SpaceEvenly
		) {
			items(resourceResult) { item ->
				ResourceItemView(item, onItemClick, navController)
			}
		}
		
	}
	
}

@Composable
fun ResourceItemView(
	item: AssociatedDrug?,
	onItemClick: (Int) -> Unit,
	navController: NavController
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 10.dp)
			.clickable(
				interactionSource = remember { MutableInteractionSource() },
				indication = rememberRipple(
					color = MaterialTheme.colorScheme.secondary,
					bounded = true
				),
				onClick = {
					navController.currentBackStackEntry?.savedStateHandle?.set(
						key = "resourceDetails",
						value = item?.id?.let { AssociatedDrug(it, item.name, "", "") }
					)
					item?.id?.let { onItemClick(it) }
				},
			),
		backgroundColor = MaterialTheme.colorScheme.onPrimary,
		shape = RoundedCornerShape(20.dp),
		elevation = 8.dp,
	) {
		Column(modifier = Modifier.padding(10.dp)) {
			if (item != null) {
				Column {
					Text(
						"Medicine name:" + item.name,
						modifier = Modifier.padding(10.dp),
					)
					Text(
						"Dose:" + item.dose,
						modifier = Modifier.padding(10.dp),
					)
					Text(
						"Strength:" + item.strength,
						modifier = Modifier.padding(10.dp),
					)
				}
				
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ResourceScreenPreview() {
	// Mock LazyPagingItems
	val mockItems = listOf(
		AssociatedDrug(1, "Diabetes", "", ""),
		AssociatedDrug(2, "Asthma", "", ""),
		AssociatedDrug(3, "Bp", "", "")
	)
	
	// Mock NavController
	val navController = rememberNavController()
	
	// Call the ResourceScreen with mock data
	ResourceScreen(
		resourceResult = mockItems,
		onItemClick = { /* Do nothing */ },
		navController = navController
	)
}

enum class Greeting(val message: String) {
	MORNING("Good Morning"),
	AFTERNOON("Good Afternoon"),
	EVENING("Good Evening");
	
	companion object {
		fun getGreetingByTime(hour: Int): Greeting {
			return when (hour) {
				in 5..11 -> MORNING
				in 12..16 -> AFTERNOON
				in 17..20 -> EVENING
				else -> EVENING
			}
		}
	}
}

var globalUserName = ""
var globalPassword = ""