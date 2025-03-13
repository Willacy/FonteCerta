package br.edu.ifba.fontecerta

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifba.fontecerta.databinding.ActivityTelaCalculadoraBinding

class TelaCalculadora : AppCompatActivity() {

    // Variáveis
    private lateinit var binding: ActivityTelaCalculadoraBinding
    private lateinit var pecasDAO: DAOPecas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTelaCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o banco de dados e o DAO
        pecasDAO = DAOPecas(this)

        // Lista de opções para so Spinners de quantidades
        val lista = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


        // Preencher os Spinners com as peças
        preencherSpinner("CPU", binding.spinnerCpu)
        preencherSpinner("GPU", binding.spinnerGpu)
        preencherSpinner("MB", binding.spinnerPlacaMae)
        preencherSpinner("RAM", binding.spinnerRAM)
        preencherSpinner("SSD", binding.spinnerSSD)
        preencherSpinner("HD", binding.spinnerHD)
        binding.spinnerRAMQuantidade.adapter =
            ArrayAdapter(this, R.layout.simple_spinner_item, lista)
        binding.spinnerQuantHD.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lista)
        binding.spinnerQuantSSD.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, lista)

        //
        binding.btnCalcular.setOnClickListener {
            calcularFonteIdeal()
        }
        binding.btnLimpar.setOnClickListener {
            limpar()
        }
    }

    private fun limpar() {
        binding.spinnerCpu.setSelection(0)
        binding.spinnerGpu.setSelection(0)
        binding.spinnerPlacaMae.setSelection(0)
        binding.spinnerRAM.setSelection(0)
        binding.spinnerSSD.setSelection(0)
        binding.spinnerHD.setSelection(0)
        binding.spinnerRAMQuantidade.setSelection(0)
        binding.spinnerQuantHD.setSelection(0)
        binding.spinnerQuantSSD.setSelection(0)
        binding.txtResultado.text = ""
        binding.txtResultado.setOnClickListener(null)

    }

    // Função para preencher os Spinners com as peças
    private fun preencherSpinner(categoria: String, spinner: Spinner) {
        // Obter as peças da categoria especificada
        val pecas = pecasDAO.listarPecasPorCategoria(categoria)
        // Extrair os nomes e os consumos das peças
        val nomesPecas = pecas.map { it.first }
        // Extrair os consumos das peças
        val consumoPecas = pecas.map { it.second }

        // Configurar o adaptador para o Spinner
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, nomesPecas)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Associar os consumos às peças no Spinner
        spinner.tag = consumoPecas
    }

    // Função para calcular a fonte ideal
    @SuppressLint("SetTextI18n")
    private fun calcularFonteIdeal() {
        // Obter a posição selecionada em cada Spinner
        val posicaoCpu = binding.spinnerCpu.selectedItemPosition
        val posicaoGpu = binding.spinnerGpu.selectedItemPosition
        val posicaoPlacaMae = binding.spinnerPlacaMae.selectedItemPosition
        val posicaoRam = binding.spinnerRAM.selectedItemPosition
        val posicaoSsd = binding.spinnerSSD.selectedItemPosition
        val posicaoHd = binding.spinnerHD.selectedItemPosition

        if (!(posicaoCpu == 0 && posicaoGpu == 0 && posicaoPlacaMae == 0 && posicaoRam == 0 && posicaoSsd == 0 && posicaoHd == 0)) {

            // Obter os consumos das peças
            val consumosCpu = binding.spinnerCpu.tag as List<Int>
            val consumosGpu = binding.spinnerGpu.tag as List<Int>
            val consumosPlacaMae = binding.spinnerPlacaMae.tag as List<Int>
            val consumosRam = binding.spinnerRAM.tag as List<Int>
            val consumosSsd = binding.spinnerSSD.tag as List<Int>
            val consumosHd = binding.spinnerHD.tag as List<Int>

            // obter o consumo de cada peça selecionada
            val cpu = consumosCpu[posicaoCpu]
            val gpu = consumosGpu[posicaoGpu]
            val placaMae = consumosPlacaMae[posicaoPlacaMae]
            val ram = consumosRam[posicaoRam]
            val ssd = consumosSsd[posicaoSsd]
            val hd = consumosHd[posicaoHd]

            // Obter a quantidade de cada peça selecionada
            val quantRam = binding.spinnerRAMQuantidade.selectedItem.toString().toInt()
            val quantSsd = binding.spinnerQuantSSD.selectedItem.toString().toInt()
            val quantHd = binding.spinnerQuantHD.selectedItem.toString().toInt()

            // Calcular a fonte ideal se todos os campos forem preenchidos
            val soma =
                (cpu + gpu + placaMae + (ram * quantRam) + (ssd * quantSsd) + (hd * quantHd))
            val fonteIdeal = soma + (soma * 0.20)



            if (posicaoRam != 0 && quantRam == 0) {
                val msg = "Coloque a quantidade de Memoria!"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } else if (posicaoSsd != 0 && quantSsd == 0) {
                val msg = "Coloque a quantidade de SSD!"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } else if (posicaoHd != 0 && quantHd == 0) {
                val msg = "Coloque a quantidade de HD!"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } else {
                binding.txtResultado.setOnClickListener(null)
                binding.txtResultado.text = "${fonteIdeal}w"

                // Passando valores para a pesquisa web
                if (fonteIdeal.toInt() < 200) {
                    pesquiseWeb(200)
                } else if (fonteIdeal.toInt() < 300) {
                    pesquiseWeb(300)
                } else if (fonteIdeal.toInt() < 400) {
                    pesquiseWeb(400)
                } else if (fonteIdeal.toInt() < 500) {
                    pesquiseWeb(500)
                } else if (fonteIdeal.toInt() < 600) {
                    pesquiseWeb(600)
                } else if (fonteIdeal.toInt() < 800) {
                    pesquiseWeb(800)
                } else {
                    pesquiseWeb(fonteIdeal.toInt())
                }
            }
        } else {
            val msg = "Escolha ao menos um componente!"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            limpar()
        }
    }
    // Pesquisando no site
    fun pesquiseWeb(valor: Int) {
        val url =
            "https://www.kabum.com.br/busca/fonte-de-alimentacao-${valor}W"
        binding.txtResultado.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}