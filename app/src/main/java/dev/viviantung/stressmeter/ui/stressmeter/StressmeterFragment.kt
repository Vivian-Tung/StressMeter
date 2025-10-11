package dev.viviantung.stressmeter.ui.stressmeter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.viviantung.stressmeter.GridViewAdapter
import dev.viviantung.stressmeter.R
import dev.viviantung.stressmeter.ScoreMap
import dev.viviantung.stressmeter.databinding.FragmentStressmeterBinding
import dev.viviantung.stressmeter.ImgConfirmActivity

class StressmeterFragment : Fragment() {

    private var _binding: FragmentStressmeterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var gridView: GridView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val stressmeterViewModel =
            ViewModelProvider(this).get(StressmeterViewModel::class.java)

        _binding = FragmentStressmeterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStressmeter
        stressmeterViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        gridView = root.findViewById(R.id.img_grid)
        gridView.adapter = GridViewAdapter(requireContext(), stressmeterViewModel.selectedImages)
        root.findViewById<Button>(R.id.btn_more).setOnClickListener {
            stressmeterViewModel.refreshImages()
            gridView.adapter = GridViewAdapter(requireContext(), stressmeterViewModel.selectedImages)
        }

        // grid view listener
        gridView.setOnItemClickListener { parent, view, position, id ->
            // launch new activity to confirm the image, send the image and score to render
            val score = ScoreMap.getScore(position)
            val img = gridView.adapter.getItem(position) as Int
            val intent = Intent(requireContext(), ImgConfirmActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("image", img)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}