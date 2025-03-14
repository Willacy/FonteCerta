package br.edu.ifba.fontecerta

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
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

        // Exibir nome do dispositivo
        val deviceName = Build.MODEL
        binding.txtNomeDispositivo.text = "Dispositivo: $deviceName"

        // Exibir memória RAM disponível e total
        val memoryInfo = ActivityManager.MemoryInfo()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(memoryInfo)
        val availableMemory = memoryInfo.availMem / 1024 / 1024  // em MB
        val totalMemory = memoryInfo.totalMem / 1024 / 1024  // em MB
        binding.txtMemoriaRAM.text = "Memória RAM: $availableMemory MB / $totalMemory MB"

        // Exibir armazenamento disponível e total
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val totalBlocks = stat.blockCountLong
        val availableBlocks = stat.availableBlocksLong
        val totalStorage = totalBlocks * blockSize / (1024 * 1024 * 1024)  // em GB
        val availableStorage = availableBlocks * blockSize / (1024 * 1024 * 1024)  // em GB
        binding.txtArmazenamento.text = "Armazenamento: $availableStorage GB / $totalStorage GB"



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