package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/putMapping")
    fun putMapping(): String {
        return "put-Mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"] )
    fun requestMapping(): String {
        return "Request Put-Mapping"
    }

    @PutMapping(path = ["/putObj"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 1. response (껍데기 만듦
       return UserResponse().apply {

            // 2. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }     //apply는 자기자신을 반환함
        }.apply {
            // 3. description
            this.description = "~~~~~~~"
        }.apply {
            val userList = mutableListOf<UserRequest>() //배열
            userList.add(userRequest)
            userList.add(UserRequest(). apply {
                this.name = "a"
                this.age = 10
                this.email = "a email"
                this.phoneNumber = "010-1111-aaaa"
            })
            userList.add(UserRequest(). apply {
                this.name = "b"
                this.age = 20
                this.email = "b email"
                this.phoneNumber = "010-1111-bbbb"
            })
            this.userRequest = userList //위에 해당 배열을 넣음
        }
    }
}