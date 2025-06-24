package com.example.archivai.presentation.screens.folders

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.archivai.R
import com.example.archivai.presentation.screens.folders.components.CreateFolderDialog
import com.example.archivai.presentation.screens.folders.components.FolderCard
import com.example.archivai.presentation.screens.folders.components.FolderSettingsBottomSheet
import com.example.archivai.presentation.screens.sections.components.CustomFloatingActionButton
import com.example.archivai.presentation.screens.sections.components.DeleteSectionDialog
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold
import com.example.archivai.sections.presentation.components.FolderFabBottomSheet
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FoldersScreen(
    navController: NavController,
    viewModel: FolderViewModel = hiltViewModel(),
    sectionName: String,
    sectionId: Int
) {
    val context = LocalContext.current
    var folderName by remember { mutableStateOf("") }

    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getFolders(sectionId)
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is FoldersUiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 48.dp, horizontal = 24.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Icon(
                    painterResource(R.drawable.arrow_icon),
                    contentDescription = "back icon",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(6.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$sectionName Folders",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painterResource(R.drawable.search_icon),
                    contentDescription = "search icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {},
                    tint = AppColor
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painterResource(R.drawable.list_view_icon),
                    contentDescription = "list icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {},
                    tint = AppColor
                )
            }

            // Content Area
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = AppColor
                        )
                    }

                    state.error != null -> {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error loading folders",
                                fontSize = 16.sp,
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.error!!,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }

                    state.folders!!.isEmpty() -> {
                        Text(
                            text = "No folders available",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    else -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(state.folders!!) { folder ->
                                FolderCard(
                                    folder.name, folder.numberOfFolders,
                                    onMoreOptionsClick = {
                                        viewModel.selectFolder(folder)
                                        viewModel.showSettingsBottomSheet()
                                    }

                                    )
                            }
                        }
                    }
                }
            }
        }


        CustomFloatingActionButton(
            onClick = { viewModel.showFabBottomSheet() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 56.dp)
        )

        if (state.isFabBottomSheetVisible) {
            FolderFabBottomSheet(
                onDismiss = { viewModel.hideFabBottomSheet() },
                onUploadFileClick = { },
                onAddFileWithAIClick = { },
                onCreateFolderClick = { viewModel.showCreateFolderDialog() }
            )
        }


        if (state.isCreateFolderDialogVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                CreateFolderDialog(
                    folderName = folderName,
                    onFolderNameChange = { folderName = it },
                    onDismiss = {
                        viewModel.hideCreateFolderDialog()
                        folderName = ""
                    },
                    onConfirm = {
                        viewModel.createFolder(folderName, sectionId)
                        Log.d("screen", sectionName)
                        folderName = ""
                    }
                )
            }
        }
        if (state.isDeleteFolderDialogVisible) {
            viewModel.hideFabBottomSheet()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                DeleteSectionDialog(
                    onDismiss = { viewModel.hideDeleteFolderDialog() },
                    onConfirm = {
                        viewModel.deleteFolder(state.selectedFolder!!.folderId,sectionId)
                    }
                )
            }

        }
        if (state.isSettingsBottomSheetVisible){
            FolderSettingsBottomSheet(
                onDismiss = {viewModel.hideSettingsBottomSheet()},
                onEditPermissions = {},
                onRename = {},
                onDelete = { viewModel.showDeleteFolderDialog()},
                onMove = {},
                onMakeCopy = {},
                onViewPermittedPermissions = {}
            )




        }
    }
}

@Preview(showBackground = true)
@Composable
fun FolderScreenPreview() {
    FoldersScreen(
        rememberNavController(), sectionName = "Demo", sectionId = 1
    )
}
