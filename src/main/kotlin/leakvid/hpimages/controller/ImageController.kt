package leakvid.hpimages.controller

import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/image")
class ImageController(val service: IImageService) {
    @GetMapping("/name")
    fun get(name: String): Image? {
        return service.get(name)
    }

    @PostMapping
    fun insert(@RequestBody encounter: Image){
        service.merge(encounter)
    }
}