package leakvid.hpimages.services.impl

import leakvid.hpimages.repositories.ImageRepository
import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import leakvid.hpimages.services.dtos.ImageDto
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ImageService(val repository: ImageRepository) : IImageService {
    override fun get(name: String): Image {
        return repository.findByName(name)
    }

    override fun merge(imageDto: ImageDto) {
        repository.save(Image(imageDto.name, Binary(BsonBinarySubType.BINARY, imageDto.file.bytes)))
    }
}