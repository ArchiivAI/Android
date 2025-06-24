package com.example.archivai.data.source.remote.responseModels.folders

import com.example.archivai.domain.entities.Folder

class GetFoldersInSectionResponseModel : ArrayList<FolderDetailsResponseModel>() {
    fun toDomainList(): List<Folder> {
        return this.map { it.toDomain() }
    }
    fun FolderDetailsResponseModel.toDomain(): Folder {
        return Folder(
            folderId = folderId,
            lastModifiedDate = lastModifiedDate,
            name = name,
            numberOfEmployees = numberOfEmployees,
            numberOfFiles = numberOfFiles,
            numberOfFolders = numberOfFolders,
            sectionName = sectionName,
            size = size
        )
    }
}


