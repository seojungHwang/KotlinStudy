package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*
import kotlin.math.min

// @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class) //class에 SnakeCase로 사용 설정
data class UserRequest (

    @field:NotEmpty
    @field:Size(min = 2, max = 8)  //field -> 해당 필드에 적용시키겠다
    var name:String?=null,

    @field:PositiveOrZero // 0 <= 숫자 -> 숫자를 검증(0도 포함 / 양수)
    var age:Int?=null,

    @field:Email
    var email:String?=null,

    @field:NotBlank  //공백검증
    var address:String?=null,

   // @JsonProperty("phone_number") //json에 들어갈 이름을 정해 줄수 있음
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") //정규식 패턴 지정하여 검증
    var phoneNumber:String?=null,  //phone_number

    //어노테이션을 만듦! 어디서든 해당 어노테이션 사용 가능 하며, 사용자 지정 어노테이션
    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createAt:String?=null //yyyy-MM-dd HH:mm:ss
){
    /*
    //메소드라서 field 안 붙힘
    //이렇게 만들 수 있지만 공통적인 기능은 어노테이션으로 따로 만들어서 인터페이스로 지정해 사용하는 것이 편함!
    @AssertTrue(message = "생성일자의 패턴 : yyyy-MM-dd HH:mm:ss")
    private fun isValidCreatedAt():Boolean{
        return try {
            LocalDateTime.parse(this.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e:Exception){
            //validCreatedAt : 생성일자의 패턴 : yyyy-MM-dd HH:mm:ss -> 에러 메세지 리턴
            false
        }

    }
    */
}
