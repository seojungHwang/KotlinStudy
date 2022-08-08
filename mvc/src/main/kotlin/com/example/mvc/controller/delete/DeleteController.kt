package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated  //검증
class DeleteController {

    //1. path variable
    //2. request param

    //Request Param
    @DeleteMapping(path = ["/deleteMapping"])
    fun deleteMapping(@RequestParam(value = "name") _name : String,
                      @NotNull(message = "age 값이 누락되었습니다.")
                      @Min(value = 20, message = "age는 20보다 커야 합니다.")
                      @RequestParam age : Int): String {
        println(_name)
        println(age)
        return _name+" "+age
    }

    //Path Variable
    @DeleteMapping(path = ["/del/{name}/{age}"])
    fun deleteMappingPath(@PathVariable(value = "name")
                          @Size(min = 2, max = 5)
                          @NotNull
                          _name : String,  //aa ~ aaaaa
                          @NotNull(message = "age 값이 누락되었습니다.")
                          @Min(value = 20, message = "age는 20보다 커야 합니다.")
                          @PathVariable age : Int): String {
        println(_name)
        println(age)
        return _name+" "+age
    }
}