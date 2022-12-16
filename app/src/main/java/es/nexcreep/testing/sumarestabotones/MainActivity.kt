package es.nexcreep.testing.sumarestabotones

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.core.widget.addTextChangedListener
import es.nexcreep.testing.sumarestabotones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputNum.addTextChangedListener ( object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                handleTextChanges()
            }

        })

        binding.inputNum.onFocusChangeListener = View.OnFocusChangeListener {
            view, hasFocus -> buttonFocusHandler(view, hasFocus)
        }

        binding.counterPlus.setOnClickListener {
            incrementCounter()
            updateCounterText()
        }


        binding.counterMinus.setOnClickListener {
            decrementCounter()
            updateCounterText()
        }

        binding.clearEditFocusText.setOnClickListener {
            binding.inputNum.clearFocus()
        }

    }

    private fun handleTextChanges() {
        if (binding.inputNum.text.isEmpty() || binding.inputNum.text.isBlank()){
            counter = 0
            updateCounterText()
            return
        }

        try {
            val num = binding.inputNum.text.toString().toInt()
            if (num < 0){
                Toast.makeText(
                    applicationContext,
                    "El número no puede ser negativo.",
                    Toast.LENGTH_LONG).show()
                return
            }
            counter = num
            updateCounterText()

        } catch (e: NumberFormatException) {
            Toast.makeText(
                applicationContext,
                "Debe de ser un número.",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun buttonFocusHandler(v: View, hasFocus: Boolean) {
        if (hasFocus) {
            binding.counterPlus.setBackgroundColor(Color.CYAN)
            binding.counterMinus.setBackgroundColor(Color.CYAN)
        } else {
            binding.counterPlus.setBackgroundColor(getColor(R.color.purple_500))
            binding.counterMinus.setBackgroundColor(getColor(R.color.purple_500))
        }
    }

    private fun updateCounterText() { binding.counterText.text = "$counter" }
    private fun incrementCounter() { counter += 1 }
    private fun decrementCounter() { if (counter > 0) counter -= 1 }
}