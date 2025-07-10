package com.example.archivai.presentation.screens.roles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.archivai.R
import com.example.archivai.presentation.screens.login_screen.composables.Spacer10
import com.example.archivai.presentation.theme.AppColor

@Composable
fun RoleCard(id : Int , name : String,onMoreOptionsClick: () -> Unit , onCardClick : () -> Unit ) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCardClick() }
        ,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.roles_nav_unselected),
                contentDescription = "Role Icon",
                modifier = Modifier.size(24.dp),
                tint = AppColor
            )
            Spacer10()
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = AppColor // Navy Blue
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "ID : $id",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                }
            }

            IconButton(onClick = { onMoreOptionsClick() }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Options",
                    tint = Color(0xFF002F6C)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoleCardPreview() {
    MaterialTheme {
        RoleCard(
            name = "HR", id = 225,
            onMoreOptionsClick = {  },
            onCardClick = { /* Handle card click */ }
        )
    }
}
