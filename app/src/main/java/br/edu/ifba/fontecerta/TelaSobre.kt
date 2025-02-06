package br.edu.ifba.fontecerta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifba.fontecerta.databinding.ActivityTelaSobreBinding

class TelaSobre : AppCompatActivity() {
    private lateinit var binding: ActivityTelaSobreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaSobreBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

}