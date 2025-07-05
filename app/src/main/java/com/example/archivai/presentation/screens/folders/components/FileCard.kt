package com.example.archivai.presentation.screens.folders.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.archivai.R
import com.example.archivai.domain.models.files.FileType
import com.example.archivai.presentation.theme.rubik_bold
import com.example.archivai.presentation.theme.rubik_regular

@Composable
fun FileCard(
    fileName: String,
    fileType : FileType,
    onMoreOptionsClick : () -> Unit,
    onCardClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                 val id = when (fileType) {
                    FileType.Word -> R.drawable.word_file_icon
                    FileType.Excel -> R.drawable.excel_file_icon
                    FileType.Pdf -> R.drawable.pdf_file_icon
                    FileType.Image -> R.drawable.image_file_icon
                    FileType.Csv -> R.drawable.csv_file_icon
                    else -> R.drawable.word_file_icon // Default icon for unknown types
                }

                Image(
                    painter = painterResource(id),
                    contentDescription = "File Icon",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = fileName,
                    fontSize = 16.sp,
                    fontFamily = rubik_bold,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(R.drawable.more_options_icon),
                    contentDescription = "More Options",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onMoreOptionsClick() },
                    tint = Color.Black
                )

            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun FileCardPreview() {
    FileCard("Calma", FileType.Image,{},{})
}