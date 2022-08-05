package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteController {

    //1. path variable
    //2. request param

    //Request Param
    @DeleteMapping(path = ["/deleteMapping"])
    fun deleteMapping(@RequestParam(value = "name") _name : String, @RequestParam age : Int): String {
        println(_name)
        println(age)
        return _name+" "+age
    }

    //Path Variable
    @DeleteMapping(path = ["/del/{name}/{age}"])
    fun deleteMappingPath(@PathVariable(value = "name") _name : String, @PathVariable age : Int): String {
        println(_name)
        println(age)
        return _name+" "+age
    }
}