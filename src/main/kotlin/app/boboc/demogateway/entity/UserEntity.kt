package app.boboc.demogateway.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("service_user")
data class UserEntity(
    @Id
    val userId: String,
    val userName: String,
)
