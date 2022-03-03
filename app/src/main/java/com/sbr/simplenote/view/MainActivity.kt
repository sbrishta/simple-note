package com.sbr.simplenote.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sbr.simplenote.R
import com.sbr.simplenote.util.SimpleNoteApplication
import com.sbr.simplenote.viewmodel.WordViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: WordListAdapter
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModel.WordViewModelFactory((application as SimpleNoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val bottomSheet = AddNewItemFragment()
            bottomSheet.show(
                supportFragmentManager,
                "ModalBottomSheet"
            )
        }
        observeWordData()
    }

    private fun observeWordData() {
        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }
    }
}