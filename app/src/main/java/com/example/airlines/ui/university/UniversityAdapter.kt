package com.wallstreet.airline.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wallstreet.airline.data.entities.University
import com.wallstreet.airline.databinding.ItemUniversityBinding

class CharactersAdapter(private val listener: CharacterItemListener) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<University>()

    fun setItems(items: ArrayList<University>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemUniversityBinding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])
}


class CharacterViewHolder(private val itemBinding: ItemUniversityBinding, private val listener: CharactersAdapter.CharacterItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var university: University

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: University) {
        this.university = item
        itemBinding.name.text = item.country
        itemBinding.txtUniversity.text = item.name
        itemBinding.txtDomain.text = item.domains[0]
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(university.id)
    }
}

