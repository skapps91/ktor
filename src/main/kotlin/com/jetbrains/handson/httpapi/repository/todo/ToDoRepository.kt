package com.jetbrains.handson.httpapi.repository.todo

import com.jetbrains.handson.httpapi.models.todo.ToDo
import com.jetbrains.handson.httpapi.models.todo.ToDoDraft

interface ToDoRepository {

    fun getAllToDos(): List<ToDo>
    fun getToDo( id: Int ): ToDo?

    fun addToDo(draft: ToDoDraft ): ToDo
    fun updateToDo( id: Int,  draft: ToDoDraft ): Boolean
    fun removeToDo( id: Int ): Boolean


}
