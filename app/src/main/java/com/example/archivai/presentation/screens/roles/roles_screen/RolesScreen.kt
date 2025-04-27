package com.example.archivai.presentation.screens.roles.roles_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.roles.components.RoleCard
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun RolesScreen(navController: NavController) {
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
                        .clickable {
                            navController.navigate(Screens.AddNewRole)
                        },
                    tint = AppColor
                )


            }
            LazyColumn(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(bottom = 30.dp)
            ) {
                items(sampleRoles) { role ->
                    RoleCard(role.id, role.name)
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RolesScreenPreview() {
    RolesScreen(rememberNavController())
}