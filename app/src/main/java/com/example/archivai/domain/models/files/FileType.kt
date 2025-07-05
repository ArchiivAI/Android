package com.example.archivai.domain.models.files

enum class FileType(typeId : Int){
    Word(1),
    Excel(2),
    Pdf(3),
    Image(4),
    Csv(5),
    Unknown(6);
}
