package com.example.archivai.presentation.screens.sections

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.archivai.presentation.navigation.Screens
import com.example.archivai.presentation.screens.sections.components.CreateSectionDialog
import com.example.archivai.presentation.screens.sections.components.CustomFloatingActionButton
import com.example.archivai.presentation.screens.sections.components.DeleteSectionDialog
import com.example.archivai.presentation.screens.sections.components.FabBottomSheet
import com.example.archivai.presentation.screens.sections.components.RenameSectionDialog
import com.example.archivai.presentation.screens.sections.components.SectionCard
import com.example.archivai.presentation.screens.sections.components.SectionPermissionDialog
import com.example.archivai.presentation.screens.sections.components.SettingsBottomSheet
import com.example.archivai.presentation.theme.AppColor
import com.example.archivai.presentation.theme.rubik_semibold
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SectionsScreen(navController: NavController, viewModel: SectionsViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsState()
    var sectionName by remember { mutableStateOf("") }
    var newSectionName by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is SectionsUiEvents.ShowToast ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                SectionsUiEvents.NavigateToAddFilesWithAI -> TODO()
                SectionsUiEvents.NavigateToCreateSection -> TODO()
                SectionsUiEvents.NavigateToScan -> TODO()
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
                .padding(top = 36.dp)
                .padding(horizontal = 8.dp)
        ) {
            // Header row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(
                    text = "Sections",
                    fontFamily = rubik_semibold,
                    fontSize = 20.sp,
                    color = AppColor
                )
                Spacer(modifier = Modifier.weight(1F))
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
                    contentDescription = "search icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {},
                    tint = AppColor
                )
            }

            // Content area
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                when {
                    state.isLoading -> {
                        // Loading state
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = AppColor
                        )
                    }

                    state.error != null -> {
                        // Error state
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error loading sections",
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

                    state.sections.isEmpty() -> {
                        // Empty state
                        Text(
                            text = "No sections available",
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
                            items(state.sections) { section ->
                                SectionCard(
                                    section.name,
                                    section.numberOfFolders,
                                    onMoreOptionsClick = {
                                        viewModel.selectSection(section)
                                        viewModel.showSettingsBottomSheet()
                                    },
                                    onCardClick = {
                                        navController.navigate(
                                            Screens.Folders(
                                                sectionId = section.id,
                                                sectionName = section.name
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        CustomFloatingActionButton(
            onClick = {
                viewModel.showFabBottomSheet()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 56.dp)
        )

        if (state.showFabBottomSheet) {
            FabBottomSheet(
                onDismiss = { viewModel.hideFabBottomSheet() },
                onScanClick = { },
                onAddFileWithAIClick = { },
                onCreateSectionClick = { viewModel.showCreateDialog() }
            )
        }

        // Show bottom sheet
        if (state.showSettingsBottomSheet) {
            SettingsBottomSheet(
                onDismiss = { viewModel.hideSettingsBottomSheet() },
                onEditPermissions = {viewModel.hideSettingsBottomSheet()
                    viewModel.showPermissionSettingsDialog() },
                onRename = { viewModel.showRenameDialog() },
                onDelete = { viewModel.showDeleteDialog() },
                onViewPermittedPermissions = { /* handle with selectedSection */ }
            )
        }
        if (state.showEditPermissionsDialog){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
            SectionPermissionDialog(
                onDismissRequest = { viewModel.hidePermissionSettingsDialog() },
                viewModel = viewModel
            )
        }}

        if (state.showRenameDialog) {
            viewModel.hideSettingsBottomSheet()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                RenameSectionDialog(
                    initialName = state.selectedSection!!.name,
                    newSectionName = newSectionName,
                    onSectionNameChange = { newSectionName = it },
                    onDismiss = {
                        viewModel.hideRenameDialog()
                        newSectionName = ""
                    },
                    onConfirm = {
                        viewModel.renameSection(state.selectedSection!!.id, newSectionName)
                        Log.d("screen", newSectionName)
                        newSectionName = ""
                    }

                )
            }
        }
        if (state.showCreateDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                CreateSectionDialog(
                    sectionName = sectionName,
                    onSectionNameChange = { sectionName = it },
                    onDismiss = {
                        viewModel.hideCreateDialog()
                        sectionName = ""
                    },
                    onConfirm = {
                        viewModel.createSection(sectionName)
                        Log.d("screen", sectionName)
                        sectionName = ""
                    }

                )
            }

        }
        if (state.showDeleteDialog) {
            viewModel.hideSettingsBottomSheet()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                DeleteSectionDialog(
                    onDismiss = { viewModel.hideDeleteDialog() },
                    onConfirm = { viewModel.deleteSection(state.selectedSection!!.id) }
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun SectionScreenPreview(modifier: Modifier = Modifier) {
    SectionsScreen(rememberNavController())
}