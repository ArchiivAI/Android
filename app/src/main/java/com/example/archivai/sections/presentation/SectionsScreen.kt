package com.example.archivai.sections.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.sections.presentation.components.CustomFloatingActionButton
import com.example.archivai.sections.presentation.components.FabBottomSheet

import com.example.ui.theme.AppColor
import com.example.ui.theme.rubik_semibold

@Composable
fun SectionsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 24.dp, end = 24.dp)) {
            Row(modifier = Modifier.fillMaxWidth().height(32.dp)) {
                Text(
                    text = "Sections",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
                Spacer(modifier = Modifier.width(182.dp))
                Icon(
                    painterResource(R.drawable.search_icon),
                    contentDescription = "search icon",
                    modifier = Modifier.size(32.dp).clickable {},
                    AppColor
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painterResource(R.drawable.list_view_icon),
                    contentDescription = "search icon",
                    modifier = Modifier.size(32.dp).clickable {},
                    AppColor
                )

            }
            LazyColumn {


            }



        }
        CustomFloatingActionButton(
            onClick = {

            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )


    }
}

@Preview(showBackground = true )
@Composable
fun SectionScreenPreview(modifier: Modifier = Modifier) {
    SectionsScreen()
}