package leakvid.hpimages.controller

import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import org.apache.coyote.Response
import org.bson.types.Binary
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/image")
class ImageController(val service: IImageService) {
    @GetMapping("/{name}")
    fun get(@PathVariable("name") name: String): ResponseEntity<Image?> {
        val image = service.get(name)

        return if (image == null) {
            ResponseEntity.notFound().build();
        }else{
            ResponseEntity.ok(image)
        }
    }

    @PostMapping
    fun insert(@RequestBody image: Image) : ResponseEntity<Image> {
        service.merge(image)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun test(): ResponseEntity<Image?> {
        return ResponseEntity.ok(Image("Cat", Binary(ByteArray(0))))
    }
}