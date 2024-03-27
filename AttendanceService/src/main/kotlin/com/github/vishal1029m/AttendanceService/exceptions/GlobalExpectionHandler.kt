package com.github.vishal1029m.AttendanceService.exceptions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import com.github.vishal1029m.AttendanceService.utils.ApiResponse
import org.springframework.boot.json.JsonParser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.function.Consumer

@RestControllerAdvice
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundExceptionHandler(ex: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        val message = ex.message
        val apiResponse = ApiResponse(message, false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceAlreadyExistException::class)
    fun resourceNotFoundExceptionHandler(ex: ResourceAlreadyExistException): ResponseEntity<ApiResponse> {
        val message = ex.message
        val apiResponse = ApiResponse(message, false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlleMethodArgNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val response: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { objectError: ObjectError ->
            val fieldName = (objectError as FieldError).field
            val message = objectError.getDefaultMessage()
            response[fieldName] = message
        })
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(FeignClientCustomException::class)
    fun handleFeignException(ex: FeignClientCustomException): ResponseEntity<ApiResponse> {
        val message = ex.message ?: "Unknown Error Occurred"
        val apiResponse = ApiResponse(message, false)
        return ResponseEntity(apiResponse,   HttpStatus.NOT_FOUND)
    }
}

