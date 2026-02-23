package com.moneysync.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneysync.domain.model.DashboardSummary
import com.moneysync.domain.model.Transaction
import com.moneysync.domain.model.TransactionType
import com.moneysync.domain.repository.TransactionRepository
import com.moneysync.domain.usecase.AddTransactionUseCase
import com.moneysync.domain.usecase.GetDashboardSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getDashboardSummaryUseCase: GetDashboardSummaryUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val repository: TransactionRepository
) : ViewModel() {

    val dashboardSummary: StateFlow<DashboardSummary> = getDashboardSummaryUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DashboardSummary(0.0, 0.0, 0.0)
        )

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTransactions().collect { _transactions.value = it }
        }
    }

    fun addSampleIncome() {
        viewModelScope.launch {
            addTransactionUseCase(
                Transaction(
                    amount = 1000.0,
                    type = TransactionType.INCOME,
                    categoryId = 1,
                    note = "Sample income",
                    date = LocalDate.now()
                )
            )
        }
    }

    fun addSampleExpense() {
        viewModelScope.launch {
            addTransactionUseCase(
                Transaction(
                    amount = 250.0,
                    type = TransactionType.EXPENSE,
                    categoryId = 2,
                    note = "Sample expense",
                    date = LocalDate.now()
                )
            )
        }
    }
}
