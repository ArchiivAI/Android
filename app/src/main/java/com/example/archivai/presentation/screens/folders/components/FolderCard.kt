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
import com.example.archivai.presentation.theme.rubik_bold
import com.example.archivai.presentation.theme.rubik_regular

@Composable
fun FolderCard(folderName : String,noOfFolders : Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                // Folder Icon
                Image(
                    painter = painterResource(id = R.drawable.folder_icon),
                    contentDescription = "Folder Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = folderName,
                    fontSize = 16.sp,
                    fontFamily = rubik_bold,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Folder Count
                Text(
                    text = " $noOfFolders  Folders",
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = rubik_regular
                )
                Spacer(modifier = Modifier.width(12.dp))
                // More Options Icon (Three dots)
                Icon(
                    painter = painterResource(R.drawable.more_options_icon),
                    contentDescription = "More Options",
                    modifier = Modifier.size(20.dp).
                    clickable {},
                    tint = Color.Black
                )

            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun FolderCardPreview(modifier: Modifier = Modifier) {
    FolderCard("Calma", 21)
}