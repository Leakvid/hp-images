package leakvid.hpimages.domain

import org.bson.types.Binary
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Image(
        @Id val name: String,
        val image: Binary
       )