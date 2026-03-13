package org.example

import org.example.model.User
import org.example.repository.InMemoryUserRepository
import org.example.service.UserService

fun main() {
    val userService = UserService(InMemoryUserRepository())

    println("=== CREATE ===")
    userService.createUser(User(id = 1, name = "Kim", email = "kim@example.com"))
    userService.createUser(User(id = 2, name = "Lee", email = "lee@example.com"))
    println(userService.getAllUsers())

    println("\n=== READ ONE ===")
    println(userService.getUserById(1))

    println("\n=== UPDATE ===")
    userService.updateUser(1, "Kim Minsu", "minsu@example.com")
    println(userService.getUserById(1))

    println("\n=== DELETE ===")
    userService.deleteUser(2)
    println(userService.getAllUsers())
}
