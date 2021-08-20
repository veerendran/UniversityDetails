package com.wallstreet.airline.ui.characterdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.wallstreet.airline.data.entities.University
import com.wallstreet.airline.data.repository.UniversityRepository
import com.wallstreet.airline.utils.Resource

class UniversityDetailViewModel @ViewModelInject constructor(
    private val repository: UniversityRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val university: LiveData<Resource<University>> = _character


    fun start(id: Int) {
        _id.value = id
    }

}
