package com.example.archivai.presentation.screens.employees.components




import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.archivai.R
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_regular

@Composable
fun EmployeeCard(
    name: String,
    email: String,
    id: Int,
    image : String,
    onSettingsClicked : () -> Unit,
    onCardClicked : () -> Unit ,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .clickable { onCardClicked() }
        ,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("EmployeeCard", "Image URL: $image")
            AsyncImage(
                model = image.replace(" ", "%20"),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColor,
                        fontFamily = rubik_regular
                    )
                  Icon(painterResource(R.drawable.more_options_icon),
                      contentDescription = "more options icon",modifier = Modifier.clickable{ onSettingsClicked()})
                }

                Text(
                    text = email,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )

                Spacer10()
                Row {
                    Text(
                        text = "Role:  ",
                        fontSize = 14.sp,
                        color = AppColor,
                        fontFamily = rubik_regular,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "view all",
                        fontSize = 14.sp,
                        color = AppColor,
                        style = LocalTextStyle.current.copy(textDecoration = TextDecoration.Underline),
                        fontFamily = rubik_regular,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable {}
                    )

                }
                Text(
                    text = "ID: $id",
                    fontSize = 14.sp,
                    fontFamily = rubik_regular,
                    color = AppColor,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeCardPreview() {
    EmployeeCard(
        name = "Ahmed Ali",
        email = "ahmedali11@archival.com",
        id = 202344798,
        "",
        modifier = Modifier.padding(16.dp),
        onCardClicked = {},
        onSettingsClicked = {  }
    )
}