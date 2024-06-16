package fr.arolla.hierarchix

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class HierarchixApplicationTests {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    fun assignAUser() {
        this.mockMvc!!.perform(post("/assign")
            .contentType("application/json")
            .content("{\"employeeId\": \"Arnold Simplet\", \"managerId\": \"Barbara Song\", \"departmentId\": \"Engineering\"}"))
            .andExpect(status().isOk)

    }
}
