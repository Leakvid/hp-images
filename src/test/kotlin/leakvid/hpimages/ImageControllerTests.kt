package leakvid.hpimages

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import leakvid.hpimages.controller.ImageController
import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import leakvid.hpimages.services.dtos.ImageDto
import org.bson.types.Binary
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest(ImageController::class)
@AutoConfigureMockMvc(addFilters = false)
class ImageControllerTests() {

    @MockkBean
    private lateinit var imageService: IImageService

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val imageRoute = "/image/Cat"
    private val name = "Cat"
    private val mpf = MockMultipartFile("image", "cat.jpg", "image/jpg", "A Cat".toByteArray())
    private val image = Image("Cat", Binary(ByteArray(0)))
    private val imageDto = ImageDto("Cat", mpf)

    @Test
    fun `when the image does not exist, return not found`() {
        every { imageService.get(name) } returns null

        mockMvc.perform(
            MockMvcRequestBuilders.get(imageRoute)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andReturn()

        verify { imageService.get(name) }
    }


    @Test
    fun `when the image does exist, return ok`() {
        every { imageService.get(name) } returns image

        mockMvc.perform(
            MockMvcRequestBuilders.get(imageRoute)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()

        verify { imageService.get(name) }
    }

    @Test
    fun `when image is merged, return ok`() {
        every { imageService.merge(imageDto) }

        mockMvc.perform(
            MockMvcRequestBuilders.multipart(imageRoute)
                .file(mpf))
            .andExpect(status().isOk)
            .andReturn()

        verify { imageService.merge(imageDto) }
    }
}