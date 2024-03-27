package com.github.vishal1029m.EmployeeService.exceptions

import lombok.Getter
import lombok.Setter
import java.util.Objects


@Getter
@Setter
class ResourceNotFoundException(private var resourceName: String?, private var fieldName: String?, private var fieldValue: Any?) :
    RuntimeException(
        String.format(
            "%s not found with %s : %s",
            resourceName,
            fieldName,
            fieldValue?.toString() ?: "NULL"
        )
    )
