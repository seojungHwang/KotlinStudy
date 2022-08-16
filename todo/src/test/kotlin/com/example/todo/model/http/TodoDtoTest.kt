package com.example.todo.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.validation.Validation

class TodoDtoTest {

    //buildDefaultValidatorFactory -> 빌더 패턴과 팩토리 오브젝트 패턴이 동시에 적용되어 있음
    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){

        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2022-08-16 16:00:00"
        }

        val result = validator.validate(todoDto)

        Assertions.assertEquals(true, result.isEmpty())
    }

}