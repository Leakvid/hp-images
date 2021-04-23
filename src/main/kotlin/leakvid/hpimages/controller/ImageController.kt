package leakvid.hpimages.controller

import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import leakvid.hpimages.services.dtos.ImageDto
import org.apache.coyote.Response
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin
@RequestMapping("/image")
class ImageController(val service: IImageService) {
    @GetMapping(
        value = ["/{name}"],
        produces = [MediaType.IMAGE_JPEG_VALUE]
    )
    fun get(@PathVariable("name") name: String): ResponseEntity<ByteArray> {
        val image = service.get(name)

        return if (image == null) {
            ResponseEntity.notFound().build();
        }else{
            ResponseEntity.ok(image.image.data)
        }
    }

    @PostMapping(value = ["/{name}"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun insert(
        @PathVariable("name") name: String,
        @RequestParam("image") file: MultipartFile
    ) : ResponseEntity<Image> {
        service.merge(ImageDto(name, file))
        return ResponseEntity.ok().build()
    }
}