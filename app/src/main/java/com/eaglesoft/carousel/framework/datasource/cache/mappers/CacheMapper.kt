package com.eaglesoft.carousel.framework.datasource.cache.mappers

import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.util.EntityMapper
import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<UserCacheEntity, User> {

    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(
            id = entity.id,
            gender = entity.gender,
            email = entity.email,
            picture = entity.picture,
            username = entity.username,
            registered = entity.registered,
            dob = entity.dob,
            phone = entity.phone,
            cell = entity.cell,
            SSN = entity.SSN
        )
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {
        return UserCacheEntity(
            id = domainModel.id ?: 1,
            gender = domainModel.gender,
            email = domainModel.email,
            picture = domainModel.picture,
            username = domainModel.username,
            registered = domainModel.registered,
            dob = domainModel.dob,
            phone = domainModel.phone,
            cell = domainModel.cell,
            SSN = domainModel.SSN
        )
    }

    fun mapFromEntityList(entities: List<UserCacheEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }
}











