package com.example.archivai.presentation.screens.activity_log

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.activity_log.components.ActivityLogCard
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun ActivityLogScreen(
    navController: NavController,
    viewModel: ActivityLogViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getLogs()
    }

    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding( horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Icon(
                    painterResource(R.drawable.arrow_icon),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .align(Alignment.CenterVertically)
                        .padding(6.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Activity Log",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = AppColor)
                    }
                }

                !state.error.isNullOrEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.error ?: "An unexpected error occurred",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.logs) {
                            ActivityLogCard(
                                userImage = it.userImage,
                                body = it.message,
                                date = it.date
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityLogScreenPreview() {
    ActivityLogScreen(rememberNavController())
}
