package edu.miu.cvbuilderapp.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val avatar: Int
) : java.io.Serializable