package com.example.usagetracker

import android.app.Application
import android.app.usage.UsageStatsManager
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class UsageViewModel(application: Application) : AndroidViewModel(application) {

    private val _chartModelProducer = ChartEntryModelProducer()
    val chartModelProducer: ChartEntryModelProducer get() = _chartModelProducer

    private val _uiState = MutableStateFlow<UsageUiState>(UsageUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchUsageStats() {
        viewModelScope.launch {
            _uiState.value = UsageUiState.Loading
            val context = getApplication<Application>().applicationContext
            val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

            val time = System.currentTimeMillis()
            val stats = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                time - TimeUnit.DAYS.toMillis(1), // Look back 1 day
                time
            )

            if (stats.isNullOrEmpty()) {
                _uiState.value = UsageUiState.NoData
            } else {
                // Filter out apps with very little usage and system apps
                val processedStats = stats
                    .filter { it.totalTimeInForeground > TimeUnit.MINUTES.toMillis(1) }
                    .sortedByDescending { it.totalTimeInForeground }
                    .take(5) // Top 5 apps

                if (processedStats.isEmpty()) {
                    _uiState.value = UsageUiState.NoData
                } else {
                    val chartEntries = processedStats.mapIndexed { index, usageStats ->
                        MyChartEntry(
                            x = index.toFloat(),
                            y = TimeUnit.MILLISECONDS.toMinutes(usageStats.totalTimeInForeground).toFloat()
                        )
                    }
                    _chartModelProducer.setEntries(chartEntries)
                    _uiState.value = UsageUiState.Success(processedStats.map { it.packageName })
                }
            }
        }
    }
}

sealed class UsageUiState {
    object Loading : UsageUiState()
    object NoData : UsageUiState()
    data class Success(val labels: List<String>) : UsageUiState()
}

private data class MyChartEntry(
    override val x: Float,
    override val y: Float,
) : ChartEntry {
    override fun withY(y: Float) = MyChartEntry(x, y)
}
