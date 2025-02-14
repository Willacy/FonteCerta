package br.edu.ifba.fontecerta

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifba.fontecerta.databinding.ActivityTelaCalculadoraBinding

class TelaCalculadora : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCalculadoraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTelaCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cpu = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val gpu = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val placaMae = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val ram = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val hd = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val ssd = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val ramQuantidade = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        binding.spinnerCpu.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, cpu)
        binding.spinnerGpu.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, gpu)
        binding.spinnerPlacaMae.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, placaMae)
        binding.spinnerRAM.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, ram)
        binding.spinnerHD.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, hd)
        binding.spinnerSSD.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, ssd)
        binding.spinnerRAMQuantidade.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, ramQuantidade)

        binding.btnCalcular.setOnClickListener {
            var selectCpu = binding.spinnerCpu.selectedItem.toString().toInt()
            var selectGpu = binding.spinnerGpu.selectedItem.toString().toInt()
            var selectPlacaMae = binding.spinnerPlacaMae.selectedItem.toString().toInt()
            var selectRam = binding.spinnerRAM.selectedItem.toString().toInt()
            var selectHd = binding.spinnerHD.selectedItem.toString().toInt()
            var selectSsd = binding.spinnerSSD.selectedItem.toString().toInt()
            var selectRamQuantidade = binding.spinnerRAMQuantidade.selectedItem.toString().toInt()


            binding.txtResultado.text =
                (selectCpu + selectGpu + selectPlacaMae + selectRam + selectHd + selectSsd + selectRamQuantidade).toString()+"w"
        }
    }
}