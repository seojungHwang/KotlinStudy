package com.example.todo.controller.api.todo.handler

import com.example.todo.controller.api.todo.TodoApiController
import com.example.todo.model.http.Error
import com.example.todo.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, requst: HttpServletRequest): ResponseEntity<ErrorResponse> {

        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach{ errorObj ->
            Error().apply {
                this.field = (errorObj as FieldError).field
                this.message = errorObj.defaultMessage
                this.value = errorObj.rejectedValue
            }.apply {
                errors.add(this)
            }
        } //forEach end

        val errorRes = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = requst.method
            this.message = ""
            this.path = requst.requestURI
            this.timestamp = LocalDateTime.now()
            this.error = errors
        }
        return ResponseEntity.badRequest().body(errorRes)
    }
}