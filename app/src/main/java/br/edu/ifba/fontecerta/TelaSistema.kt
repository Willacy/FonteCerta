package br.edu.ifba.fontecerta

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

        // Botão Voltar
        binding.btnVoltar.setOnClickListener {
            finish()
        }

    }
}