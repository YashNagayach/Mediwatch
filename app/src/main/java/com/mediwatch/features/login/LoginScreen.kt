package com.mediwatch.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mediwatch.core.ui.AppBackground
import com.mediwatch.features.resources.globalPassword
import com.mediwatch.features.resources.globalUserName


@Composable
internal fun LoginRoute(
	modifier: Modifier = Modifier,
	navigateToHome: () -> Unit = {}
) {
	LoginScreen(
		navigateToHome = navigateToHome
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
	navigateToHome: () -> Unit
) {
	var text by remember { mutableStateOf("Hello") }
	var userName by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(20.dp),
		verticalArrangement = Arrangement.Center
	) {
		OutlinedTextField(
			value = userName,
			onValueChange = {
				userName = it
				globalUserName = it
			},
			label = { Text("Username") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(10.dp))
		OutlinedTextField(
			value = password,
			onValueChange = {
				password = it
				globalPassword = it
			},
			label = { Text("Password") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(20.dp))
		Button(navigateToHome, modifier = Modifier.fillMaxWidth()) {
			Text(text = stringResource(id = com.mediwatch.R.string.login))
		}
		
	}
	
}

@Preview
@Composable
fun LoginPreview() {
	AppBackground {
		LoginScreen {}
	}
}