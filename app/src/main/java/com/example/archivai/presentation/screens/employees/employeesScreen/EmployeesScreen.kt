package com.example.archivai.presentation.screens.employees.employeesScreen


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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.employees.components.EmployeeCard
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold

@Composable
fun EmployeeScreen(navController: NavController) {
    data class EmployeeItem( val name: String,val email: String,val id: Int  )

    val sampleEmployees = listOf(
        EmployeeItem("amr ","ame@gmail.com",1),
        EmployeeItem("amr ","ame@gmail.com",1),
        EmployeeItem("amr ","ame@gmail.com",1),
        EmployeeItem("amr ","ame@gmail.com",1),
        EmployeeItem("amr ","ame@gmail.com",1)

    )




    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 24.dp)

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp).padding(6.dp)) {
                Text(
                    text = "Employees",
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
                        .clickable {navController.navigate(Screens.AddNewEmployee)},
                    tint = AppColor
                )


            }
            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleEmployees) { employee ->
                    EmployeeCard(employee.name, employee.email, employee.id)
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmployeeScreenPreview() {
    EmployeeScreen(rememberNavController())
}