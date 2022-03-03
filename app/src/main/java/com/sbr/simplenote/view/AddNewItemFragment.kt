package com.sbr.simplenote.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sbr.simplenote.R
import com.sbr.simplenote.model.Word
import com.sbr.simplenote.util.SimpleNoteApplication
import com.sbr.simplenote.viewmodel.WordViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNewItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNewItemFragment : BottomSheetDialogFragment() {

    private lateinit var editWordView: EditText
    private lateinit var wordViewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        wordViewModel = activity?.let {
//            ViewModelProvider.of(it)[WordViewModel::class.java]
//        } ?: throw Exception("Activity is null")

        val app = SimpleNoteApplication.instance
        val factory = WordViewModel.WordViewModelFactory(app.repository)
        wordViewModel = ViewModelProvider(this,factory).get(WordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editWordView = view.findViewById(R.id.edit_word)

        val button = view.findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            if (TextUtils.isEmpty(editWordView.text)|| TextUtils.getTrimmedLength(editWordView.text) <= 0) {
                //show empty
                dismiss()
            } else {
                val noteText = editWordView.text.toString()
                wordViewModel.insert(word = Word(noteText))
                dismiss()
            }
        }
    }
}
