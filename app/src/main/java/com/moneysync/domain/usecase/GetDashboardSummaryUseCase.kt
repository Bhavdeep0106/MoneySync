package com.moneysync.domain.usecase

import com.moneysync.domain.model.DashboardSummary
import com.moneysync.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDashboardSummaryUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    operator fun invoke(): Flow<DashboardSummary> = repository.getDashboardSummary()
}
