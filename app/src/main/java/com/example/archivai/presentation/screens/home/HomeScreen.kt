package com.example.archivai.presentation.screens.home

import StorageBreakdown
import StorageUsageCard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.activity_log.components.ActivityLogCard
import com.example.archivai.presentation.screens.home.components.HomeTopAppBar
import com.example.archivai.presentation.screens.sections.components.SectionCard
import com.example.archivai.presentation.theme.AppColor



@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp)


        ) {
            HomeTopAppBar(state.userName, imageUrl = state.imageUrl, onProfileClick = {
                navController.navigate(
                    Screens.Profile
                )
            })


            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                // Storage Usage Card
                StorageUsageCard(
                    breakdown = StorageBreakdown(
                        totalUsed = state.storageUsed.toFloat(),
                        totalCapacity = state.totalStorage.toFloat(),
                        wordPercentage = 0.3f,
                        imagePercentage = 0.2f,
                        excelPercentage = 0.1f,
                        pdfPercentage = 0.4f
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Activity Log Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Activity Log",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColor
                    )
                    Text(
                        text = "View All",
                        fontSize = 14.sp,
                        color = AppColor,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate(Screens.ActivityLog) }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                state.latestActivityLog?.let { log ->
                    ActivityLogCard(
                        userImage = log.userImage,
                        body = log.message,
                        date = log.date
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Quick Access Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quick Access",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColor
                    )
                    Icon(
                        painterResource(R.drawable.list_view_icon),
                        contentDescription = "More",
                        tint = AppColor,
                        modifier = Modifier.clickable{}
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Section Cards
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(state.quickAccessSections!!) { section ->
                        SectionCard(
                            sectionName = section.name,
                            noOfFolders = null,
                            onMoreOptionsClick = {}
                        ) {}
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}