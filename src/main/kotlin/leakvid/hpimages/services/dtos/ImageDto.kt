package leakvid.hpimages.services.dtos

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.web.multipart.MultipartFile

@Document
data class ImageDto(
    val name: String,
    val file: MultipartFile
)