package com.example.usagetracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer

@Composable
fun UsageLineGraph(
    chartModelProducer: ChartEntryModelProducer,
    labels: List<String>
) {
    val bottomAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
            value, _ ->
        labels.getOrNull(value.toInt()) ?: ""
    }

    Column {
        Text("Top 5 Apps by Usage (in minutes)", style = MaterialTheme.typography.headlineSmall)
        Chart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            chart = lineChart(),
            chartModelProducer = chartModelProducer,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(
                valueFormatter = bottomAxisValueFormatter,
                labelRotationDegrees = 90f
            ),
        )
    }
}
