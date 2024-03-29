package com.github.vishal1029m.PerformanceReviewService.services.impl


import com.github.vishal1029m.PerformanceReviewService.entites.PerformanceReview
import com.github.vishal1029m.PerformanceReviewService.exceptions.ResourceAlreadyExistException
import com.github.vishal1029m.PerformanceReviewService.exceptions.ResourceNotFoundException
import com.github.vishal1029m.PerformanceReviewService.`external services`.EmployeeService
import com.github.vishal1029m.PerformanceReviewService.payloads.EmployeeDto
import com.github.vishal1029m.PerformanceReviewService.payloads.performanceReview.PerformanceReviewDto
import com.github.vishal1029m.PerformanceReviewService.repositories.PerformanceReviewRepo
import com.github.vishal1029m.PerformanceReviewService.services.PerformanceReviewService
 import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.stream.Collectors


@Component
class PerformanceReviewServiceImpl(
    @Autowired val performanceReviewRepo: PerformanceReviewRepo,
    @Autowired val employeeService: EmployeeService,
    @Autowired val mapper: ModelMapper,
) : PerformanceReviewService {

    override fun getAllPerformanceReview(): List<PerformanceReviewDto> {
        return performanceReviewRepo
            .findAll()
            .stream()
            .map { performanceReview: PerformanceReview ->
                toDto(performanceReview)
            }
            .collect(Collectors.toList())
    }

    override fun getPerformanceReviewById(performanceReviewId: Long): PerformanceReviewDto? {
        val performanceReview: PerformanceReview = performanceReviewRepo
            .findById(performanceReviewId)
            .orElseThrow {
                ResourceNotFoundException("PerformanceReview", "Id", performanceReviewId)
            }
        return toDto(performanceReview)
    }

    override fun getPerformanceReviewByEmployee(employeeId: Long): List<PerformanceReviewDto> {
        val employee: EmployeeDto =  employeeService.getEmployeeById(employeeId).body!!

        val performanceReviews: List<PerformanceReview> =
            performanceReviewRepo
                .findByEmployeeId(employeeId)

        return performanceReviews
            .stream()
            .map { performanceReview ->
                toDto(performanceReview)
            }
            .collect(Collectors.toList())
    }

    override fun getPerformanceReviewByEmployeeBetweenDate(
        employeeId: Long,
        startDate: LocalDate?,
        endDate: LocalDate?
    ): List<PerformanceReviewDto> {
        if (startDate == null || endDate == null) return getPerformanceReviewByEmployee(employeeId)
        val employee: EmployeeDto = employeeService.getEmployeeById(employeeId).body!!

        val result: List<PerformanceReview> =
            performanceReviewRepo
                .findByEmployeeIdAndReviewDateBetween(employeeId, startDate, endDate)
        return result
            .stream()
            .map { pr ->
                mapper.map(pr, PerformanceReviewDto::class.java)
            }
            .collect(Collectors.toList())
    }


    override fun addPerformanceReview(performanceReviewDto: PerformanceReviewDto): PerformanceReviewDto {
        println(performanceReviewDto.toString())
        val employee: EmployeeDto = employeeService.getEmployeeById(performanceReviewDto.employeeId!!).body!!
        val performanceReview: PerformanceReview = dtoToEntity(performanceReviewDto)

        val savedPerformanceReview: PerformanceReview = performanceReviewRepo.save(performanceReview)
        return toDto(savedPerformanceReview)
    }

    override fun updatePerformanceReview(
        performanceReviewId: Long,
        performanceReviewDto: PerformanceReviewDto
    ): PerformanceReviewDto {
        performanceReviewRepo
            .findById(performanceReviewId)
            .orElseThrow {
                ResourceNotFoundException("PerformanceReview", "Id", performanceReviewId)
            }
        val employee: EmployeeDto = employeeService.getEmployeeById(performanceReviewDto.employeeId!!).body!!
        val performanceReview: PerformanceReview = dtoToEntity(performanceReviewDto)

        performanceReview.id = performanceReviewId
        val updatedPerformanceReview: PerformanceReview = performanceReviewRepo.save(performanceReview)
        return toDto(updatedPerformanceReview)

    }

    override fun deletePerformanceReview(performanceReviewId: Long) {
        performanceReviewRepo
            .findById(performanceReviewId)
            .orElseThrow {
                ResourceNotFoundException("PerformanceReview", "Id", performanceReviewId)
            }
        return performanceReviewRepo.deleteById(performanceReviewId)
    }

    private fun toDto(performanceReview: PerformanceReview): PerformanceReviewDto {
        val performanceReviewDto: PerformanceReviewDto = PerformanceReviewDto()
        performanceReviewDto.id = performanceReview.id
        performanceReviewDto.reviewDate = performanceReview.reviewDate
        performanceReviewDto.comments = performanceReview.comments
        performanceReviewDto.rating = performanceReview.rating
        performanceReviewDto.employeeId = performanceReview.employeeId
        return performanceReviewDto
    }

    private fun dtoToEntity(performanceReviewDto: PerformanceReviewDto): PerformanceReview {
        val performanceReview: PerformanceReview = PerformanceReview()
        performanceReview.id = performanceReviewDto.id
        performanceReview.reviewDate = performanceReviewDto.reviewDate
        performanceReview.comments = performanceReviewDto.comments
        performanceReview.rating = performanceReviewDto.rating
        employeeService.getEmployeeById(performanceReviewDto.employeeId!!).body!!
        performanceReview.employeeId =  performanceReviewDto.employeeId
        return performanceReview
    }
}