package com.jetbrains.handson.httpapi.repository.mysql

import com.jetbrains.handson.httpapi.database.DatabaseManager
import com.jetbrains.handson.httpapi.models.todo.ToDo
import com.jetbrains.handson.httpapi.models.todo.ToDoDraft
import com.jetbrains.handson.httpapi.models.todo.ToDoDraft2
import com.jetbrains.handson.httpapi.repository.todo.ToDoRepository

class MySQLToDoRepository : ToDoRepository {

    private val database = DatabaseManager()





    override fun getAllToDos(): List<ToDo> {

        return database.getAllToDos()
            .map {  ToDo( it.id, it.title, it.done )  }

    }

    override fun getToDo(id: Int): ToDo? {

        return database.getToDo( id )
                ?.let { ToDo( it.id, it.title, it.done ) }

    }

    override fun addToDo(draft: ToDoDraft): ToDo {

        return database.addToDo( draft )

    }


    override fun removeToDo(id: Int): Boolean {

        return database.deleteToDo( id )


    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {

        return database.updateToDo( id, draft )

    }




}