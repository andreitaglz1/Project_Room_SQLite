package com.andrea.roomsqlite

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CiudadViewModel(private val repository: CiudadRepository):ViewModel() {

    val allCiudad : LiveData<List<Ciudad>> = repository.allCiudad.asLiveData()

    fun insert(ciudad: Ciudad) = viewModelScope.launch {
        repository.insert(ciudad)
    }
}

class CiudadViewModelFactory(private val repository: CiudadRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadViewModel::class.java)){
            return CiudadViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}