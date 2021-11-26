package com.animesh.roy.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.animesh.roy.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var nicknameTextView: TextView
    private lateinit var doneButton: Button

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Animesh Roy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        editText = binding.nicknameEdit
        nicknameTextView = binding.nicknameText
        doneButton = binding.doneButton

        doneButton.setOnClickListener {
            addNickname(it)
        }

        nicknameTextView.setOnClickListener {
            updateNickname(it)
        }
    }


    private fun addNickname(view: View) {
        binding.apply {
            binding.myName?.nickName = editText.text.toString()
            editText.visibility = View.GONE
            view.visibility = View.GONE
            nicknameTextView.visibility = View.VISIBLE

            invalidateAll()
        }

        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname (view: View) {
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}