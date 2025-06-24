package com.example.archivai.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archivai.domain.usecases.home.GetDocsUseCase
import com.example.archivai.domain.usecases.home.GetQuickAccessSectionsUseCase
import com.example.archivai.domain.usecases.home.GetStorageUseCase
import com.example.archivai.domain.usecases.home.GetUserDetails
import com.example.archivai.domain.usecases.sections.GetSectionsUseCase
import com.example.archivai.presentation.screens.roles.roles_screen.RolesUiState
import com.example.archivai.presentation.screens.sections.SectionsUiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDocsUseCase: GetDocsUseCase,
    private val getStorageUsedUseCase: GetStorageUseCase,
    private val getUserDetailsUseCase: GetUserDetails,
    private val getQuickAccessSectionsUseCase: GetQuickAccessSectionsUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getDocs()
        getStorage()
        getUserName()
        getUserPic()
        getQuickAccessSections()
    }
    fun getQuickAccessSections(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val sections = getQuickAccessSectionsUseCase()
                Log.d("ViewModel", "Sections fetched: ${sections.size}")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    quickAccessSections = sections
                )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch sections", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )

            }


        }


    }

    fun getDocs(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val docs = getDocsUseCase.invoke()
                Log.d("ViewModel", "docs fetched: $docs")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    wordPercentage = docs.wordCount.toFloat(),
                    imagePercentage = docs.imageCount.toFloat(),
                    excelPercentage = docs.excelCount.toFloat(),
                    pdfPercentage = docs.pdfCount.toFloat(),
                    )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch docs", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }


        }

    }
    fun getStorage(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val storage = getStorageUsedUseCase.invoke()
                Log.d("ViewModel", "docs fetched: $storage")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    storageUsed = storage.usedStorage.toString(),
                    totalStorage = storage.totalStorage.toString()
                )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch storage used", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }


        }



    }
    fun getUserName(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userDetails = getUserDetailsUseCase.invoke()
                Log.d("ViewModel", "user details fetched: $userDetails")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    userName = userDetails.firstName
                )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch user details", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    fun getUserPic(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val userDetails = getUserDetailsUseCase.invoke()
                Log.d("ViewModel", "user image fetched: ${userDetails.imageUrl}")
                _uiState.value = _uiState.value.copy(isLoading = false,
                    imageUrl = userDetails.imageUrl
                )
            } catch (e: Exception) {
                Log.e("ViewModel", "Failed to fetch user details", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }




}