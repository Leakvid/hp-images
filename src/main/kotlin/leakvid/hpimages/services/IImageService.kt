package leakvid.hpimages.services

import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.dtos.ImageDto

interface IImageService {
    fun get(name: String) : Image?
    fun merge(imageDto: ImageDto)
}