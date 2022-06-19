package br.com.zup.zoologico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.zoologico.databinding.FragmentAnimalCadastradoBinding
import br.com.zup.zoologico.databinding.ItemAnimalBinding


class AnimalCadastradoFragment : Fragment() {
private lateinit var binding: FragmentAnimalCadastradoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalCadastradoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receberDados()

    }

    fun receberDados(){
        val args = AnimalCadastradoFragmentArgs.fromBundle(requireArguments())
        binding.tvTipoAnimal.text = args.animal.getNome()
        binding.tvDetalheDescricaoAnimal.text = args.animal.getDescricao()

    }



}