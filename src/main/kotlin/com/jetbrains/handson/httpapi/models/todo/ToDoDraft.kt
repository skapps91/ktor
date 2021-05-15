package com.jetbrains.handson.httpapi.models.todo

/*import kotlinx.serialization.Serializable

@Serializable*/
data class ToDoDraft(

    val title: String ,
    val done: Int

)

data class ToDoDraft2(

    val title: String ,
    val done: String

)


data class LoginRequest(
        val username: String,
        val password: String
)
data class LoginResponse(
        val ok: Boolean,
        val message: String
)