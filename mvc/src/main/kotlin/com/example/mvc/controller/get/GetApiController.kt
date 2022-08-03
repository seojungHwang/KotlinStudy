package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController //REST API Controller 동작
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello() : String {
        return "hello Kotlin"  //반환타입 String
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/requestMapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/hello2/{name}/{age}")
    fun hello2(@PathVariable name : String, @PathVariable age : Int) : String{
        println("${name}, ${age}")
        return name+" "+age
    }

    @GetMapping("/hello3/{name}/{age}")
    fun hello3(@PathVariable(value = "name") _name : String, @PathVariable(name = "age") _age : Int) : String{
        println("${_name}, ${_age}")
        return _name+" "+_age
    }

    @GetMapping("/hello4") //?key=value&key=value
    fun queryParam(@RequestParam name: String, @RequestParam age: Int): String {
        println("${name}, ${age}")
        return name+" "+age
    }

    //변수명 -(하이픈) 사용x
    @GetMapping("/hello5") //Object는 json 형태
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    //변수명 -(하이픈) 사용시
    @GetMapping("/hello6")
    fun queryParamMap(@RequestParam map: Map<String,Any>): Map<String, Any> {
        println(map)
        var phoneeNumner = map.get("phone-number")
        return map
    }
}