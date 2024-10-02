package app.boboc.demogateway.repository

import app.boboc.demogateway.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<UserEntity, String> {
}