package com.example.archivai.presentation.screens.employees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .background(AppColor.copy(alpha = 0.2f), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = role.name,
            color = AppColor,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Remove role",
            modifier = Modifier
                .size(16.dp)
                .clickable { onRemove() },
            tint = AppColor
        )
    }
}