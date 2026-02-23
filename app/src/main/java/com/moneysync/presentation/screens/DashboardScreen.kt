package com.moneysync.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneysync.presentation.components.SummaryCard
import com.moneysync.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val summary by viewModel.dashboardSummary.collectAsStateWithLifecycle()

    Scaffold { padding ->
        DashboardContent(
            contentPadding = padding,
            totalIncome = summary.totalIncome,
            totalExpense = summary.totalExpense,
            netBalance = summary.netBalance,
            onAddIncome = viewModel::addSampleIncome,
            onAddExpense = viewModel::addSampleExpense
        )
    }
}

@Composable
private fun DashboardContent(
    contentPadding: PaddingValues,
    totalIncome: Double,
    totalExpense: Double,
    netBalance: Double,
    onAddIncome: () -> Unit,
    onAddExpense: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SummaryCard(title = "Total Income", amount = totalIncome)
        SummaryCard(title = "Total Expense", amount = totalExpense)
        SummaryCard(title = "Net Balance", amount = netBalance)

        Button(onClick = onAddIncome) {
            Text("Add Sample Income")
        }

        Button(onClick = onAddExpense) {
            Text("Add Sample Expense")
        }
    }
}
