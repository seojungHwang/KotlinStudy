package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class) //class에 SnakeCase로 사용 설정
data class UserRequest (
    var name:String?=null,
    var age:Int?=null,
    var email:String?=null,

   // @JsonProperty("phone_number") //json에 들어갈 이름을 정해 줄수 있음
    var phoneNumber:String?=null  //phone_number
        )