package com.github.vishal1029m.PayrollService.utils

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ApiResponse(
    val message: String? = null,
    val success: Boolean = false
)

