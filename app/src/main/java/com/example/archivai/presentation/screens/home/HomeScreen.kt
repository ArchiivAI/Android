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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.activity_log.components.ActivityLogCard
import com.example.archivai.presentation.screens.home.components.HomeTopAppBar

import com.example.archivai.presentation.screens.sections.components.SectionCard
import com.example.archivai.presentation.theme.AppColor



data class SectionEntry(val sectionName: String, val noOfFolders: Int)

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray background
    ) {

        Column (
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 24.dp)


        ){
            HomeTopAppBar("Ahmed")


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                // Storage Usage Card
                StorageUsageCard(
                    breakdown = StorageBreakdown(
                        totalUsed = 120f,
                        totalCapacity = 300f,
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

                // Activity Log Card
                ActivityLogCard(
                    name = "Ahmed ",
                    body = "added File in Insider Insider Insider"
                )

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
                        tint = AppColor
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Section Cards
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(
                        items = listOf(
                            SectionEntry("Section Name", 20),
                            SectionEntry("Section Name", 40),
                            SectionEntry("Section Name", 60),
                            SectionEntry("Section Name", 80),
                            SectionEntry("Section Name", 100)
                        )
                    ) { section ->
                        SectionCard(
                            sectionName = section.sectionName,
                            noOfFolders = section.noOfFolders
                        )
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