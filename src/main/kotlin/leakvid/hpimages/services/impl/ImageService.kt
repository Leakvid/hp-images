package leakvid.hpimages.services.impl

import leakvid.hpimages.repositories.ImageRepository
import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ImageService(val repository: ImageRepository) : IImageService {
    override fun get(name: String): Image {
        return repository.findByName(name)
    }

    override fun merge(encounter: Image) {
        repository.save(encounter)
    }
}