package br.edu.ifba.fontecerta

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifba.fontecerta.databinding.ActivityTelaSistemaBinding

class TelaSistema : AppCompatActivity() {

    private lateinit var binding: ActivityTelaSistemaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaSistemaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Registrar o BroadcastReceiver para monitorar a temperatura
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)

        // Botão Voltar
        binding.btnVoltar.setOnClickListener {
            finish()
        }

    }

    // BroadcastReceiver para capturar a temperatura da bateria
    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val temp = intent?.getIntExtra("temperature", 0) ?: 0
            val temperaturaCelsius = temp / 10.0f // Converter para °C
            binding.txtTemperatura.text = "Temperatura: $temperaturaCelsius°C"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver) // Evita vazamento de memória
    }
}