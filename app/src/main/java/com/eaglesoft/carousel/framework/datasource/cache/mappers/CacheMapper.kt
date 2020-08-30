package com.eaglesoft.carousel.framework.datasource.cache.mappers

import com.eaglesoft.carousel.business.domain.models.Location
import com.eaglesoft.carousel.business.domain.models.Name
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.util.EntityMapper
import com.eaglesoft.carousel.framework.datasource.cache.model.UserCacheEntity
import com.google.gson.Gson
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<UserCacheEntity, User> {

    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(
            id = entity.id,
            gender = entity.gender,
            name = nameMapFromEntity(entity.name),
            location = locationMapFromEntity(entity.location),
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
            id = domainModel.id,
            name = nameMapToEntity(domainModel.name),
            location = locationMapToEntity(domainModel.location),
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

    fun nameMapFromEntity(nameEntity: String): Name {
        return Gson().fromJson(nameEntity, Name::class.java)
    }

    fun locationMapFromEntity(locationEntity: String): Location {
        return Gson().fromJson(locationEntity, Location::class.java)
    }

    fun nameMapToEntity(nameEntity: Name): String {
        return Gson().toJson(nameEntity)
    }

    fun locationMapToEntity(locationEntity: Location): String {
        return Gson().toJson(locationEntity)
    }

}











