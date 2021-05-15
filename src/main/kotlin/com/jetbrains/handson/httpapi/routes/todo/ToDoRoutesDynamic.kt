import com.jetbrains.handson.httpapi.models.todo.ToDoDraft
import com.jetbrains.handson.httpapi.repository.todo.ToDoRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


    fun Route.listAllToDosRoute(toDoRepo: ToDoRepository) {
        get("/todos") {


            //call.respond(toDoRepo)
            call.respond( toDoRepo.getAllToDos() )


        }
    }

    fun Route.getSingleToDo(toDoRepo: ToDoRepository) {

        get("todos/{id}") {


            val idParam = call.parameters["id"]?.toIntOrNull()

            if (idParam == null) {

                call.respond(
                    HttpStatusCode.BadRequest,
                    "id parameter has to be a number"
                )

                return@get
            }

            var dataToDo = toDoRepo.getToDo(idParam)

            if (dataToDo == null) {
                call.respond(
                    HttpStatusCode.NotFound,
                    "$idParam not found"
                )


            } else {
                call.respond(dataToDo)

            }


        }

    }

fun Route.createToDo( toDoRepo: ToDoRepository ){

    post("/createTodo") {


        val toDoDraft = call.receive<ToDoDraft>()

        val toDoData = toDoRepo.addToDo(toDoDraft)

        call.respond( HttpStatusCode.OK, toDoData )


    }



}

    fun Route.updateToDo(toDoRepo: ToDoRepository) {

        put("/updateTodo/{id}") {

            val toDoDraft = call.receive<ToDoDraft>()

            val toDoID = call.parameters["id"]?.toIntOrNull()

            if( toDoID == null ){
                call.respond(
                        HttpStatusCode.NotFound,
                        "$toDoID id not found"
                )
                return@put
            }

            val toDoData = toDoRepo.updateToDo( toDoID, toDoDraft )

            call.respond(toDoData)


        }
    }


    fun Route.deleteToDo(toDoRepo: ToDoRepository) {

        delete("/deleteToDo/{id}") {

            val toDoID = call.parameters["id"]?.toIntOrNull()

            if (toDoID == null) {

                call.respond(
                    HttpStatusCode.BadRequest,
                    "Id must be number"
                )

                return@delete

            }

            val removed = toDoRepo.removeToDo(toDoID)

            if (removed) {

                call.respond(HttpStatusCode.OK, "ToDo Removed with ID $toDoID")

            } else {
                call.respond(HttpStatusCode.NotFound, "$toDoID id is already deleted")

            }


        }


    }


