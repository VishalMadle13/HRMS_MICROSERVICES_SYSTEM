package com.github.vishal1029m.PerformanceReviewService.exceptions

import lombok.Getter
import lombok.Setter

@Setter
@Getter
class FeignClientCustomException(val status: Int?, message: String) : RuntimeException(message)
