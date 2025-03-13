package br.edu.ifba.fontecerta

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifba.fontecerta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.btnSobre.setOnClickListener {
            val i = Intent(this, TelaSobre::class.java)
            startActivity(i)
        }
        binding.btnCalcularPotencia.setOnClickListener {
            val i = Intent(this, TelaCalculadora::class.java)
            startActivity(i)
        }
        binding.btnSistema.setOnClickListener {
            val i = Intent(this, TelaSistema::class.java)
            startActivity(i)
        }
    }
}