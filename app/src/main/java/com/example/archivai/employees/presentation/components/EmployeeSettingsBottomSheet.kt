import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.archivai.R
import com.example.archivai.sections.presentation.components.SettingsBottomSheetItem
import com.example.ui.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeSettingsBottomSheet(
    onRename : ()-> Unit,
    onDelete : () -> Unit,
    onDismiss : () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            containerColor = Color(0xFFF6F8FF)
        ) {
            // Rename option
            SettingsBottomSheetItem(
                iconRes = R.drawable.rename_icon,
                text = "Edit information",
                textColor = AppColor,
                onClick = onRename
            )
            //delete option
            Spacer(modifier = Modifier.height(16.dp))
            SettingsBottomSheetItem(
                iconRes = R.drawable.delete_icon,
                text = "Delete Employee",
                textColor = AppColor,
                onClick = onDelete
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}