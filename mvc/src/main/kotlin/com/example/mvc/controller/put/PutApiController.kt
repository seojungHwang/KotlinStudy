package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {

        // 어노테이을 통해 빈 검증
        // bindingResult -> Validator를 상속받는 클래스에서 객체값을 검증하는 방식이다.
        if(bindingResult.hasErrors()){
            //500 error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError //형변환
                val message = it.defaultMessage
                msg.append(field.field +" : "+message+"\n")
                //밑에 에러 결과
                /*
                name : 비어 있을 수 없습니다
                address : 공백일 수 없습니다
                name : 크기가 2에서 8 사이여야 합니다
                email : 올바른 형식의 이메일 주소여야 합니다
                 */
            }
            return  ResponseEntity.badRequest().body(msg.toString())
        }

        return  ResponseEntity.ok("")

    /*
        // 1. response (껍데기 만듦)
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
                this.address = "aa"
                this.phoneNumber = "010-1111-2222"
            })
            userList.add(UserRequest(). apply {
                this.name = "b"
                this.age = 20
                this.email = "b email"
                this.address = "bb"
                this.phoneNumber = "010-1111-3333"
            })
            this.userRequest = userList //위에 해당 배열을 넣음
        }
     */
    }

}
