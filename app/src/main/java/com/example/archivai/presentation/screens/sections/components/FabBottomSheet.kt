package com.example.archivai.presentation.screens.sections.components

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
fun FabBottomSheet(
    onDismiss: () -> Unit,
    onScanClick: () -> Unit,
    onAddFileWithAIClick: () -> Unit,
    onCreateSectionClick: () -> Unit,
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

            // Add File With AI Option
            BottomSheetItem(
                iconRes = R.drawable.add_with_ai_icon,
                text = "Add File With AI",
                textColor = AppColor,
                onClick = onAddFileWithAIClick
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Create Section Option
            BottomSheetItem(
                iconRes = R.drawable.section_icon,
                text = "Create Section",
                textColor = AppColor,
                onClick = onCreateSectionClick
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BottomSheetItem(
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
fun FabBottomSheetPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Add File With AI Option
        BottomSheetItem(
            iconRes = R.drawable.add_with_ai_icon,
            text = "Add File With AI",
            textColor = AppColor,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Create Section Option
        BottomSheetItem(
            iconRes = R.drawable.section_icon,
            text = "Create Section",
            textColor = AppColor,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}