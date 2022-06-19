package br.com.zup.zoologico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.zoologico.adapter.AnimalAdapter
import br.com.zup.zoologico.databinding.FragmentCadastroBinding
import br.com.zup.zoologico.model.Animal

class CadastroFragment : Fragment() {
    private lateinit var binding: FragmentCadastroBinding

    val animaisAdapter: AnimalAdapter by lazy { AnimalAdapter(mutableListOf(), this::irParaDetalheAnimal) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cadastrar()
        exibirRecyclerViews()

    }

    fun exibirRecyclerViews(){
        binding.rvAnimal.adapter = animaisAdapter
        binding.rvAnimal.layoutManager = LinearLayoutManager(context)
    }

    fun cadastrar(){
        binding.btnAdicionarAnimal.setOnClickListener{
            adicionarAnimal()
        }
    }

    fun adicionarAnimal() {
        val animal = recuperarCamposAnimal()

        animal?.let{
            val listaAnimal = mutableListOf<Animal>()
            listaAnimal.add(animal)
            animaisAdapter.atualizarListaAnimais(listaAnimal)
        }
    }

    fun recuperarCamposAnimal(): Animal? {
        val nome = binding.etTextoAnimal.text.toString()
        val descricao = binding.etTextoDescricao.text.toString()
        if (nome.isNotBlank() && descricao.isNotBlank()) {
            limparCampos()
            return Animal(nome, descricao)
        }
        mensagemErro(nome, descricao)
        return null
    }

    private fun limparCampos(){
        binding.etTextoAnimal.text.clear()
        binding.etTextoDescricao.text.clear()
    }

    fun mensagemErro(nome:String, descricao:String){
        binding.etTextoAnimal.error = if (nome.isBlank()) MENSAGEM_ERRO else null
        binding.etTextoDescricao.error = if (descricao.isBlank()) MENSAGEM_ERRO else null
    }

    fun irParaDetalheAnimal(animal : Animal){
        val action = CadastroFragmentDirections.actionCadastroFragment2ToAnimalCadastradoFragment(animal)
        NavHostFragment.findNavController(this).navigate(action)
    }
}