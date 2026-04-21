package com.example.binarytodecimalconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var input: EditText
    private lateinit var output: TextView  // TextView
    private lateinit var submit: Button
    private lateinit var reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        setupListeners()
    }
 // Method to bind views
    private fun bindViews() {
        input  = findViewById(R.id.editText)
        output = findViewById(R.id.output)  // TextView
        submit = findViewById(R.id.submit)
        reset  = findViewById(R.id.reset)
    }

    // Method to set up listeners
    private fun setupListeners() {
        submit.setOnClickListener { convertDecimalToBinary() }
        reset.setOnClickListener  { clearFields() }

        input.doAfterTextChanged { text ->
            submit.isEnabled = !text.isNullOrBlank()
        }
    }
// Method to convert decimal to binary
    private fun convertDecimalToBinary() {
        val inputText = input.text.toString().trim()

        if (inputText.isBlank()) {
            showError("Please enter a number.")
            return
        }

        val number = inputText.toLongOrNull()

        when {
            number == null -> showError("Invalid number entered.")
            number < 0     -> showError("Please enter a non-negative number.")
            else           -> output.text = number.toString(radix = 2)
        }
    } // end of convertDecimalToBinary()

 // Method to clear input and output fields
    private fun clearFields() {
        input.text.clear()
        output.text = ""
        input.requestFocus()
    } // end of clearFields()

    // Method to show error message
    private fun showError(message: String) {
        input.error = message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    } // end of showError()
}