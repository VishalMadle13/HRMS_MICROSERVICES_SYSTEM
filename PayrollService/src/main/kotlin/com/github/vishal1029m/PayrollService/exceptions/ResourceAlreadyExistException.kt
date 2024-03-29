package com.github.vishal1029m.PayrollService.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ResourceAlreadyExistException(private var resourceName: String) :
    RuntimeException(String.format("%s already exists", resourceName))
