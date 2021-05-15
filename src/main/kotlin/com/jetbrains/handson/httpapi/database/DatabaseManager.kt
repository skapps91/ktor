package com.jetbrains.handson.httpapi.database

import com.jetbrains.handson.httpapi.database.todo.DBToDoTable
import com.jetbrains.handson.httpapi.database.todo.DBTodoEntity
import com.jetbrains.handson.httpapi.models.todo.ToDo
import com.jetbrains.handson.httpapi.models.todo.ToDoDraft
import com.jetbrains.handson.httpapi.models.todo.ToDoDraft2
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {


    //config
    private val hostName = "localhost"
    private val databaseName = "ktorm"
    private val userName = "root"
    private val password = ""

    //database
    private val ktormDB: Database

    init {

        //JDBC Url:
        val jdbcUrl = "jdbc:mysql://$hostName:3306/$databaseName?user=$userName&password=$password&useSSL=false"
        ktormDB = Database.connect ( jdbcUrl )


    }


    fun getAllToDos(): List<DBTodoEntity> {

        return ktormDB.sequenceOf( DBToDoTable ).toList()

    }

    fun getToDo( id:Int ): DBTodoEntity? {

        return ktormDB.sequenceOf( DBToDoTable )
                .firstOrNull {  it.id eq id  }

    }


    fun addToDo( draft: ToDoDraft ): ToDo {

       val insertedID = ktormDB.insertAndGenerateKey( DBToDoTable ){

            set( DBToDoTable.title, draft.title )
            set( DBToDoTable.done, draft.done )

        } as Int

        return ToDo( insertedID, draft.title, draft.done )

    }

    fun updateToDo( id: Int, draft: ToDoDraft ): Boolean {

        val updatedRows = ktormDB.update( DBToDoTable ){

            set( DBToDoTable.title, draft.title )
            set( DBToDoTable.done, draft.done )

            where {
                it.id eq id
            }

        }

        return updatedRows > 0
    }


    fun deleteToDo( id: Int ): Boolean{

        val deletedRows = ktormDB.delete( DBToDoTable ){
            it.id eq id
        }
        return deletedRows > 0

    }





}