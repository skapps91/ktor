package com.jetbrains.handson.httpapi.database.todo

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object DBToDoTable: Table<DBTodoEntity>("todo"){

    val id = int( "id" ).primaryKey().bindTo { it.id }
    val title = varchar( "title" ).bindTo { it.title }
    val done = int( "done" ).bindTo { it.done }

}

interface DBTodoEntity: Entity<DBTodoEntity> {


    companion object : Entity.Factory<DBTodoEntity>()

    val id: Int
    val title: String
    val done: Int


}

