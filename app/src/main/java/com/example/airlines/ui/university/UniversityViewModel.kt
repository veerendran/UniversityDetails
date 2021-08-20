package com.wallstreet.airline.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wallstreet.airline.data.entities.University
import com.wallstreet.airline.data.repository.UniversityRepository
import com.wallstreet.airline.utils.Resource

class UniversityViewModel @ViewModelInject constructor(
    private val repository: UniversityRepository
) : ViewModel() {

    private val _country = MutableLiveData<String>()

    private val _character = _country.switchMap { country ->
        repository.getCharacters(country)
    }
    val characters: LiveData<Resource<List<University>>> = _character

     fun delete(){
        repository.deleteDB()
    }

    fun update(country: String) {
        _country.value = country
    }

}
