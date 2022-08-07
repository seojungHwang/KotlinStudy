package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/res")
class ResponseApiConroller {
    // ResponseEntity 응답값 처리


    //1. get 4xx
    @GetMapping("")
    fun getMapping(@RequestParam age : Int?): ResponseEntity<String> {
        // ? -> null 허용

        //엘비스 연산자 (코틀린스러움)
        return age?.let {
            //age not null
            if (it < 20) {
                return ResponseEntity.status(400).body("fail")
            }
            ResponseEntity.ok("OK")
        }?: kotlin.run{
            //age == null
            return ResponseEntity.status(400).body("null")
        }

        /*
        //1. age == null -> 400 error
        if(age == null){
            return ResponseEntity.status(400).body("null")
        }

        //2. age < 20 -> 400 error
        if(age < 20){
            return ResponseEntity.status(400).body("fail")
        }
        */
       // return ResponseEntity.ok("OK")
    }

    //2. post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    //3. put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. 기존 데이터가 없어서 새로 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    //4. delete 500
    @DeleteMapping("/{id}")
    fun delMapping(@PathVariable id : Int): ResponseEntity<Any> {
        return ResponseEntity.status(500).body(null)
    }
}