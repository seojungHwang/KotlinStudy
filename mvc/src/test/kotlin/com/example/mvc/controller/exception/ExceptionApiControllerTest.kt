package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            get("/api/exc/hello")
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "mm")
        queryParams.add("age", "20")

        mockMvc.perform(
            get("/api/exc").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("mm 20")
        ).andDo(print())
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "mm")
        queryParams.add("age", "9")

        mockMvc.perform(
            get("/api/exc").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("9")
        ).andDo(print())
    }

    @Test
    fun postTest(){

        val userRequest = UserRequest().apply {
            this.name = "mm"
            this.age = 10
            this.phoneNumber = "010-1111-2222"
            this.address = "aa"
            this.email = "aa@bbb"
            this.createAt = "2022-08-15 19:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        print(json)

        mockMvc.perform(
            post("/api/exc")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("mm")
        ).andExpect(
            jsonPath("\$.age").value(10)
        ).andExpect(
            jsonPath("\$.email").value("aa@bbb")
        ).andExpect(
            jsonPath("\$.phoneNumber").value("010-1111-2222")
        ).andExpect(
            jsonPath("\$.address").value("aa")
        ).andExpect(
            jsonPath("\$.createAt").value("2022-08-15 19:00:00")
        ).andDo(print())
    }

    @Test
    fun postFailTest(){

        val userRequest = UserRequest().apply {
            this.name = "mm"
            this.age = -1
            this.phoneNumber = "010-1111-2222"
            this.address = "aa"
            this.email = "aa@bbb"
            this.createAt = "2022-08-15 19:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        print(json)

        mockMvc.perform(
            post("/api/exc")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().`is`(400)
        ).andDo(print())
    }

}