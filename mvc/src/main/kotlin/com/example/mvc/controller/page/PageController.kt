package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

//html  페이지 내리는 것은 conroller 어노테이션 사용
@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String {
        println("init main")
        //controller 어노테이션때문에 해당 페이지로 이동
        return "main.html"
    }


    @ResponseBody  //json 형태로 사용하기 위함
    @GetMapping("/res")
    fun response(): UserRequest {
        //객체형태
        return UserRequest().apply {
            this.name = "name!"
        }
       // println("res main")
      //  return "main.html"
    }
}