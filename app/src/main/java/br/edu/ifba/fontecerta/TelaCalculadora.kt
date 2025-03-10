package br.edu.ifba.fontecerta

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifba.fontecerta.databinding.ActivityTelaCalculadoraBinding

class TelaCalculadora : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCalculadoraBinding
    private lateinit var DAOPecas: DAOPecas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTelaCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DAOPecas = DAOPecas(this)
        val lista = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


        // Preencher os Spinners com as peças
        preencherSpinner("CPU", binding.spinnerCpu)
        preencherSpinner("GPU", binding.spinnerGpu)
        preencherSpinner("MB", binding.spinnerPlacaMae)
        preencherSpinner("RAM", binding.spinnerRAM)
        preencherSpinner("SSD", binding.spinnerSSD)
        preencherSpinner("HD", binding.spinnerHD)
        binding.spinnerRAMQuantidade.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lista)
        binding.spinnerQuantHD.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lista)
        binding.spinnerQuantSSD.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lista)

        binding.btnCalcular.setOnClickListener {
            calcularFonteIdeal()
        }

    }

    private fun preencherSpinner(categoria: String, spinner: Spinner) {
        val pecas = DAOPecas.listarPecasPorCategoria(categoria)
        val nomesPecas = pecas.map { it.first }

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, nomesPecas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun calcularFonteIdeal() {
        val cpu = binding.spinnerCpu.selectedItem.toString()
        val gpu = binding.spinnerGpu.selectedItem.toString()
        val placaMae = binding.spinnerPlacaMae.selectedItem.toString()
        val ram = binding.spinnerRAM.selectedItem.toString()
        val ssd = binding.spinnerSSD.selectedItem.toString()
        val hd = binding.spinnerHD.selectedItem.toString()

        val consumoTotal = DAOPecas.listarPecasPorCategoria("CPU").sumOf { it.second } +
                DAOPecas.listarPecasPorCategoria("GPU").sumOf { it.second } +
                DAOPecas.listarPecasPorCategoria("Placa-Mãe").sumOf { it.second } +
                DAOPecas.listarPecasPorCategoria("RAM").sumOf { it.second } +
                DAOPecas.listarPecasPorCategoria("SSD").sumOf { it.second } +
                DAOPecas.listarPecasPorCategoria("HD").sumOf { it.second }

        binding.txtResultado.text = "${consumoTotal + (consumoTotal * 0.15)} Watts"

    }
}