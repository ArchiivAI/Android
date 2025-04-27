import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.archivai.presentation.theme.rubik_bold
import com.example.archivai.presentation.theme.rubik_regular

data class StorageBreakdown(
    val totalUsed: Float, // e.g., 120 GB
    val totalCapacity: Float, // e.g., 300 GB
    val wordPercentage: Float, // Percentage of total used (e.g., 30%)
    val imagePercentage: Float, // e.g., 20%
    val excelPercentage: Float, // e.g., 10%
    val pdfPercentage: Float // e.g., 40%
)

@Composable
fun StorageUsageCard(breakdown: StorageBreakdown) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A237E), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Storage usage text
        Text(
            text = "${breakdown.totalUsed.toInt()} GB of ${breakdown.totalCapacity.toInt()} GB Used",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = rubik_bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Linear Progress Indicator for overall usage (Material 3)
        LinearProgressIndicator(
            progress = { breakdown.totalUsed / breakdown.totalCapacity },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color(0xFF4CAF50), // Green for the progress
            trackColor = Color.White.copy(alpha = 0.2f) // Background color in Material 3
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Segmented bar for breakdown
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        ) {
            // Word segment (green)
            Box(
                modifier = Modifier
                    .weight(breakdown.wordPercentage)
                    .fillMaxHeight()
                    .background(Color(0xFF4CAF50))
            )
            // Image segment (purple)
            Box(
                modifier = Modifier
                    .weight(breakdown.imagePercentage)
                    .fillMaxHeight()
                    .background(Color(0xFFAB47BC))
            )
            // Excel segment (cyan)
            Box(
                modifier = Modifier
                    .weight(breakdown.excelPercentage)
                    .fillMaxHeight()
                    .background(Color(0xFF26C6DA))
            )
            // PDF segment (pink)
            Box(
                modifier = Modifier
                    .weight(breakdown.pdfPercentage)
                    .fillMaxHeight()
                    .background(Color(0xFFF06292))
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Legend
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Word
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Word", fontSize = 12.sp, color = Color.White,fontFamily = rubik_regular)
            }

            // Image
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFAB47BC))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Image", fontSize = 12.sp, color = Color.White,fontFamily = rubik_regular)
            }

            // Excel
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF26C6DA))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Excel", fontSize = 12.sp, color = Color.White,fontFamily = rubik_regular)
            }

            // PDF
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF06292))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "PDF", fontSize = 12.sp, color = Color.White,fontFamily = rubik_regular)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StorageUsageCardPreview() {
    StorageUsageCard(
        breakdown = StorageBreakdown(
            totalUsed = 120f,
            totalCapacity = 300f,
            wordPercentage = 0.3f,
            imagePercentage = 0.2f,
            excelPercentage = 0.1f,
            pdfPercentage = 0.4f
        )
    )
}