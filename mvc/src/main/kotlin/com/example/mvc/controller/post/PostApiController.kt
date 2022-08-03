package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post")
    fun postMapping(): String {
        return "Post-Mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path=["/requestMapping"])
    fun requestMapping(): String {
        return "Post-Mapping"
    }

    @PostMapping("/post/obj")
    fun postObject(@RequestBody userRequest: UserRequest): UserRequest {
        //post 사용 시 @RequestBody 붙히면 row-json으로 날려야함
        //form-data로 날릴 시 에러나며, @RequestBody를 빼고 날려야함

        //json -> object
        println(userRequest)
        //object -> json
        return userRequest
    }

}