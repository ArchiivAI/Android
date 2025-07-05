package com.example.archivai.data.source.remote.responseModels.folders

import com.example.archivai.domain.entities.File
import com.example.archivai.domain.models.files.FileType

class GetFilesResponseModel : ArrayList<GetFilesResponseModelItem>(){
    fun toDomainList(): List<File> {
        return this.map { it.toDomain() }
    }

    fun GetFilesResponseModelItem.toDomain(): File {
        return File(
            createdBy = createdBy,
            currentVersion = currentVersion,
            folderId = folderId,
            id = id,
            lastModifiedDate = lastModifiedDate,
            locked = locked,
            lockedBy = lockedBy,
            name = name,
            ocrText = ocrText,
            path = path,
            relativePath = relativePath,
            size = size,
            type = this.type.toFileType(),
            uploadDate = uploadDate,
            versions = versions
        )
    }

    fun Int.toFileType(): FileType {
        return when (this) {
            0 -> FileType.Word
            1 -> FileType.Pdf
            2 -> FileType.Excel
            3 -> FileType.Image
            4 -> FileType.Csv
            else -> FileType.Unknown
        }
    }






}