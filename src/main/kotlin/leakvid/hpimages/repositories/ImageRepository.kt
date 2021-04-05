package leakvid.hpimages.repositories

import leakvid.hpimages.domain.Image
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : MongoRepository<Image, Long> {
    fun findByName(name : String) : Image
}