package com.github.vishal1029m.AttendanceService.payloads

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Data
data class DepartmentDto(
    @field:NotNull(message = "Id can not be null")
    var id: String = "",

    @field:NotBlank(message = "Department name cannot be blank")
    var department: String = "",

    @field:NotBlank(message = "Description cannot be blank")
    var description: String = "",
)