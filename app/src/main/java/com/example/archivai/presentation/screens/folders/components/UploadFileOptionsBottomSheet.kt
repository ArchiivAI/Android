package com.example.archivai.presentation.screens.folders.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.archivai.R


@Composable
fun UploadFileOptionsBottomSheet(
    onDismissRequest: () -> Unit,
    onThisDeviceClick: () -> Unit,
    onTakePhotoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Upload Options",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onTakePhotoClick() }, // Only call onTakePhotoClick
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.take_photo),
                contentDescription = "Take photo"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Take Photo")
        }

        // From Device Button - REMOVE onDismissRequest from here
        Button(
            onClick = { onThisDeviceClick() }, // Only call onThisDeviceClick
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.this_device),
                contentDescription = "From device"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("From Device")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cancel Button
        OutlinedButton(
            onClick = onDismissRequest,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UploadFileOptionsBottomSheetPreview() {
    UploadFileOptionsBottomSheet(
        onDismissRequest = { },
        onThisDeviceClick = {},
        onTakePhotoClick = {}
    )
}






