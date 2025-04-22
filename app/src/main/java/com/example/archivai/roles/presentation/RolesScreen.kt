package com.example.archivai.roles.presentation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.roles.presentation.components.RoleCard
import com.example.archivai.sections.presentation.components.SectionCard
import com.example.archivai.sections.presentation.sampleSections
import com.example.ui.theme.AppColor
import com.example.ui.theme.rubik_semibold

@Composable
fun RolesScreen() {
    data class RoleItem( val id: Int ,val name: String)

    val sampleRoles = listOf(
        RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),
       RoleItem(20,"HR Specialist"),




    )



    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                .padding(bottom = 72.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp).padding(6.dp)) {
                Text(
                    text = "Roles",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
                Spacer(modifier = Modifier.weight(1F))
                Icon(
                    painterResource(R.drawable.add_icon),
                    contentDescription = "add icon",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {},
                    tint = AppColor
                )


            }
            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(sampleRoles) { role ->
                   RoleCard(role.id,role.name)
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RolesScreenPreview() {
    RolesScreen()
}