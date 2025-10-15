package dev.viviantung.stressmeter.ui.results

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.viviantung.stressmeter.R
import dev.viviantung.stressmeter.databinding.FragmentResultsBinding
import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null

    private lateinit var lineChart: LineChart

    private lateinit var tableLayout: TableLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resultsViewModel =
            ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        lineChart = root.findViewById<LineChart>(R.id.lineChart)
        tableLayout = root.findViewById(R.id.scoreTable)

        val textView: TextView = binding.textResults
        resultsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // probably call rendering line chart and rendering table
        // first need to load the data
        val scores = resultsViewModel.loadScores(requireContext())
        // for loop to read each item? and its based on instance also
        // observer scores from view model
        resultsViewModel.scores.observe(viewLifecycleOwner) { scoreList ->
            if (!scoreList.isNullOrEmpty()) {
                renderChart(scoreList)
                //  addHeaderRow()
                renderTable(scoreList)
            }
        }
        // load scores
        resultsViewModel.loadScores(requireContext())



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // fcn to render the lin chart
    private fun renderChart(scoreList: List<Pair<String, Int>>) {
        // convert pairs into chart points in loop

        val entries = scoreList.mapIndexed { index, pair ->
            Entry(index.toFloat(), pair.second.toFloat())
        }

        // create lines
        val dataSet = LineDataSet(entries, "Stress Level").apply {
            color = Color.BLUE
            setCircleColor(Color.DKGRAY)
            lineWidth = 2f
            circleRadius = 4f
            valueTextSize = 10f
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }

        lineChart.data = LineData(dataSet)

        // Style the chart axes & description
        lineChart.apply {
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            axisRight.isEnabled = false
            animateX(700)
            description.isEnabled = false
        }

        lineChart.invalidate() // refresh

    }

    // fcn to render the able
    private fun renderTable(scoreList: List<Pair<String, Int>>) {
        // this should just render the table, should get the data from the view model
        // clear it first
        if (tableLayout.childCount > 0) {
            tableLayout.removeViews(0, tableLayout.childCount) // remove all previous rows
        }

        // iterate and add new row for each score
        for ((timestamp, score) in scoreList) {
            val row = TableRow(requireContext())
            val timeStampView = TextView(requireContext()).apply {
                text = timestamp
                setPadding(8, 8, 8, 8)
            }

            val scoreView = TextView(requireContext()).apply {
                text = score.toString()
                setPadding(8, 8, 8, 8)
            }

            // add UI tweaks to it
            timeStampView.background = ResourcesCompat.getDrawable(resources, R.drawable.rectangle, null)
            scoreView.background =  ResourcesCompat.getDrawable(resources, R.drawable.rectangle, null)
            row.addView(timeStampView)
            row.addView(scoreView)
            tableLayout.addView(row)
        }
    }
}