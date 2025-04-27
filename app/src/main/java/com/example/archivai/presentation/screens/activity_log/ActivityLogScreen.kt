package com.example.archivai.presentation.screens.activity_log

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.activity_log.components.ActivityLogCard
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold

data class ActivityLogEntry(val name: String, val body: String)
val sampleLogs = listOf(
    ActivityLogEntry("Ahmed","added new file"),
    ActivityLogEntry("Ahmed","added new file"),
    ActivityLogEntry("Ahmed","added new file"),
    ActivityLogEntry("Ahmed","added new file"),

)
@Composable
fun ActivityLogScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                .padding(bottom = 72.dp)
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
                        .clickable {}
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
            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sampleLogs) { log ->
                    ActivityLogCard(log.name,log.body)
                }

            }

        }
    }
}

// Preview for the Activity Log Screen
@Preview(showBackground = true)
@Composable
fun ActivityLogScreenPreview() {
    ActivityLogScreen(rememberNavController())
}