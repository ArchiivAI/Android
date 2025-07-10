package com.example.archivai.presentation.screens.employees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.domain.entities.Role
import com.example.archivai.presentation.theme.AppColor

@Composable
fun RoleChip(role: Role, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .background(AppColor, shape = RoundedCornerShape(16.dp))
            .padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = role.name,
            color = Color.White,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .clickable { onRemove() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Remove role",
                tint = AppColor,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}
