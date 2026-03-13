package org.example.service

import org.example.model.User
import org.example.repository.InMemoryUserRepository

class UserService(
    private val userRepository: InMemoryUserRepository
) {
    fun createUser(user: User): User {
        require(userRepository.findById(user.id) == null) { "이미 존재하는 ID입니다: ${user.id}" }
        return userRepository.create(user)
    }

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Int): User {
        return userRepository.findById(id)
            ?: throw NoSuchElementException("사용자를 찾을 수 없습니다: $id")
    }

    fun updateUser(id: Int, name: String, email: String): User {
        val existingUser = getUserById(id)
        val updatedUser = existingUser.copy(name = name, email = email)

        return userRepository.update(updatedUser)
            ?: throw IllegalStateException("사용자 수정에 실패했습니다: $id")
    }

    fun deleteUser(id: Int) {
        check(userRepository.delete(id)) { "삭제할 사용자가 없습니다: $id" }
    }
}
