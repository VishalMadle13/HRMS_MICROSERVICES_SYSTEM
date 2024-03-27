package com.github.vishal1029m.EmployeeService.entites

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.util.*
 import kotlin.collections.HashSet

@Entity
 class Department(
    @Id
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    var id: String = "",
    var department: String = "",
    var description: String = "",
    @OneToMany(mappedBy = "department", cascade = [CascadeType.ALL]) @JsonIgnoreProperties("employees")
    var employees: MutableSet<Employee> = mutableSetOf()
)
