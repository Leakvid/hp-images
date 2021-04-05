package leakvid.hpimages.services

import leakvid.hpimages.domain.Image

interface IImageService {
    fun get(name: String) : Image?
    fun merge(encounter: Image)
}