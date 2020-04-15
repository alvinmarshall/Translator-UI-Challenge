package com.cheise_proj.translator_ui_challenge.source

import androidx.lifecycle.LiveData

interface IRepository<T> {
    fun get(): LiveData<List<T>>

    fun get(identifier: Int): LiveData<T>

    suspend fun save(data: T)

    suspend fun saveList(dataList: List<T>)

    suspend fun update(data: T)

    suspend fun delete(data: T)

}