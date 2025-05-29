package com.example.archivai.presentation.screens.roles.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_regular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RolesBottomSheet(
    onDismiss: () -> Unit,
    onRename: () -> Unit,
    onDelete: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color(0xFFF6F8FF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Rename option
            RolesBottomSheetItem(
                iconRes = R.drawable.rename_icon,
                text = "Rename Role",
                textColor = AppColor,
                onClick = onRename
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Delete option
            RolesBottomSheetItem(
                iconRes = R.drawable.delete_icon,
                text = "Delete Role",
                textColor = AppColor,
                onClick = onDelete
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RolesBottomSheetItem(
    iconRes: Int,
    text: String,
    textColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            tint = textColor,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontFamily = rubik_regular
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RolesBottomSheetPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFF6F8FF)) // Match the bottom sheet background color
    ) {
        // Rename option
        RolesBottomSheetItem(
            iconRes = R.drawable.rename_icon,
            text = "Rename",
            textColor = AppColor,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Delete option
        RolesBottomSheetItem(
            iconRes = R.drawable.delete_icon,
            text = "Delete",
            textColor = AppColor,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}