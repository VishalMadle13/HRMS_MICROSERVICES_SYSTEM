package com.github.vishal1029m.AttendanceService.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ResourceAlreadyExistException(private var resourceName: String) :
    RuntimeException(String.format("%s already exists", resourceName))
