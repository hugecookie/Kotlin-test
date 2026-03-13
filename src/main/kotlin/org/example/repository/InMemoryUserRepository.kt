package org.example.repository

import org.example.model.User

class InMemoryUserRepository {
    private val users = mutableListOf<User>()

    fun create(user: User): User {
        users.add(user)
        return user
    }

    fun findAll(): List<User> = users.toList()

    fun findById(id: Int): User? = users.find { it.id == id }

    fun update(user: User): User? {
        val index = users.indexOfFirst { it.id == user.id }
        if (index == -1) {
            return null
        }

        users[index] = user
        return user
    }

    fun delete(id: Int): Boolean = users.removeIf { it.id == id }
}
