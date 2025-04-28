package com.example.archivai.presentation.screens.sections

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.sections.components.CustomFloatingActionButton
import com.example.archivai.presentation.screens.sections.components.MainBottomBar
import com.example.archivai.presentation.screens.sections.components.SectionCard


import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold

data class SectionItem(val name: String, val folderCount: Int)


val sampleSections = listOf(
    SectionItem("Section Name", 20),
    SectionItem("Section Name", 20),
    SectionItem("Section Name", 20),
    SectionItem("Section Name", 20),
    SectionItem("Section Name", 20),
    SectionItem("Section Name", 20),
    SectionItem("Section Name", 20)
)

@Composable
fun SectionsScreen(navController: NavController) {

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(vertical = 48.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth().height(32.dp)) {
                    Text(
                        text = "Sections",
                        fontFamily = rubik_semibold,
                        fontSize = 20.sp,
                        color = AppColor
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Icon(
                        painterResource(R.drawable.search_icon),
                        contentDescription = "search icon",
                        modifier = Modifier.size(32.dp).clickable {},
                        tint = AppColor
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        painterResource(R.drawable.list_view_icon),
                        contentDescription = "search icon",
                        modifier = Modifier.size(32.dp).clickable {},
                        tint = AppColor
                    )

                }
                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(sampleSections) { section ->
                        SectionCard(section.name, section.folderCount)
                    }

                }


            }
            CustomFloatingActionButton(
                onClick = {

                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .padding(bottom = 56.dp)

            )





        }


    }



@Preview(showBackground = true )
@Composable
fun SectionScreenPreview(modifier: Modifier = Modifier) {
    SectionsScreen(rememberNavController())
}