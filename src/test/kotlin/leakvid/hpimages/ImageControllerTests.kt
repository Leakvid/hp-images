package leakvid.hpimages

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import leakvid.hpimages.controller.ImageController
import leakvid.hpimages.domain.Image
import leakvid.hpimages.services.IImageService
import org.bson.types.Binary
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
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

    private val getImageRoute = "/image/Cat"
    private val createImageRoute = "/image"
    private val name = "Cat"
    private val image = Image("Cat", Binary(ByteArray(0)))

    @Test
    fun `when the image does not exist, return not found`() {
        every { imageService.get(name) }  returns null

        mockMvc.perform(
            MockMvcRequestBuilders.get(getImageRoute)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andReturn()

        verify { imageService.get(name) }
    }


    @Test
    fun `when the image does exist, return ok`() {
        every { imageService.get(name) } returns image

        mockMvc.perform(
            MockMvcRequestBuilders.get(getImageRoute)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()

        verify { imageService.get(name) }
    }

    @Test
    fun `when image is merged, return ok`() {
        every { imageService.merge(image) }

        mockMvc.perform(
            MockMvcRequestBuilders.post(createImageRoute, image)
                .content(ObjectMapper().writeValueAsString(image))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()

        verify { imageService.merge(image) }
    }
}