package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.repository.mysql.MySQLToDoRepository
import com.jetbrains.handson.httpapi.repository.todo.ToDoRepository
import createToDo
import customerRouting
import deleteToDo
import getOrderRoute
import getSingleToDo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import listAllToDosRoute
import updateToDo
import listOrdersRoute
import totalizeOrderRoute


/*
import deleteToDo
import createToDo
import listAllToDosRoute
import getSingleToDo
import updateToDo
*/


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install(CallLogging)

    install(ContentNegotiation) {
        //json()

        gson {
            setPrettyPrinting()
        }

    }

    /*install(CORS) {
        anyHost()
    }*/




    registerCustomerRoutes()
    registerOrderRoutes()

    registerToDoRoutesDynamic()


}


fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}

fun Application.registerOrderRoutes() {
    routing {
        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}


fun Application.registerToDoRoutesDynamic(){

    routing {

        val repository: ToDoRepository = MySQLToDoRepository()

        createToDo( repository )
        updateToDo( repository )
        deleteToDo( repository )

        listAllToDosRoute( repository )
        getSingleToDo( repository )


    }

}


