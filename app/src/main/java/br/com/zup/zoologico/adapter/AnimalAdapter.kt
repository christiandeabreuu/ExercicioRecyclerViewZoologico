package br.com.zup.zoologico.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.zoologico.databinding.ItemAnimalBinding
import br.com.zup.zoologico.model.Animal

class AnimalAdapter(
    val listaAnimais : MutableList<Animal>,
    val irParaDetalheAnimal : (animal : Animal) -> Unit,
    ): RecyclerView.Adapter<AnimalAdapter.ViewHolder>(){
    class ViewHolder ( val binding : ItemAnimalBinding): RecyclerView.ViewHolder(binding.root){
        fun exibirAnimais(animal:Animal){
            binding.tvTextoCard.text = animal.getNome()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = listaAnimais[position]
        holder.exibirAnimais(animal)
        holder.binding.cvAnimal.setOnClickListener{
            irParaDetalheAnimal(animal)
        }
    }

    override fun getItemCount(): Int = listaAnimais.size

    fun atualizarListaAnimais(novaLista: MutableList<Animal>){
        listaAnimais.addAll(novaLista)
        notifyDataSetChanged()
    }

}