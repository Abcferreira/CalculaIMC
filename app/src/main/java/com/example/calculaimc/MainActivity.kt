package com.example.calculaimc

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.calculaimc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcula.setOnClickListener {
            //validar os campos
            if (binding.etPeso.text.toString() != "" && binding.etAltura.text.toString() != "") {
                val imc = calcIMC(binding.etPeso.text.toString(), binding.etAltura.text.toString())

                //seta decimal em apenas 2 casas
                val df = DecimalFormat("#.00")
                val imcResp = "IMC: " + df.format(imc) + "\n" + checkIMC(imc)
                binding.tvResultado.text = imcResp
            } else {
                binding.tvResultado.text = "Valores nulos."
            }
            it.hideKeyboard()
        }
    }

    //calculo do IMC
    private fun calcIMC(peso: String, altura: String): Double =
        peso.toDouble() / (altura.toDouble() * altura.toDouble())

    //retorna uma string deacordo com o calculo
    private fun checkIMC(db: Double): String {
        return when (db) {
            in 0.0..17.0 -> "Muito abaixo do peso."
            in 17.1..18.49 -> "Abaixo do peso."
            in 18.5..24.99 -> "Peso normal."
            in 25.0..29.99 -> "Acima do peso."
            in 30.0..34.99 -> "Obesidade I."
            in 35.0..39.99 -> "Obesidade II(severa)."
            else -> "Obesidade III(mórbida)."
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}


