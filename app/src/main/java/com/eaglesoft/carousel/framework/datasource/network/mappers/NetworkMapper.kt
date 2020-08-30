package com.eaglesoft.carousel.framework.datasource.network.mappers

import com.eaglesoft.carousel.business.domain.models.Location
import com.eaglesoft.carousel.business.domain.models.Name
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.util.EntityMapper
import com.eaglesoft.carousel.framework.datasource.network.model.LocationNetworkEntity
import com.eaglesoft.carousel.framework.datasource.network.model.NameNetworkEntity
import com.eaglesoft.carousel.framework.datasource.network.model.UserBaseNetworkEntity
import com.eaglesoft.carousel.framework.datasource.network.model.UserNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<UserNetworkEntity, User> {

    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
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

    override fun mapToEntity(domainModel: User): UserNetworkEntity {
        return UserNetworkEntity(
            gender = domainModel.gender,
            name = nameMapToEntity(domainModel.name),
            location = locationMapToEntity(domainModel.location),
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


    fun mapFromEntityList(entities: List<UserNetworkEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapFromResultEntityList(entities: UserBaseNetworkEntity): List<User> {
        return entities.results.map { mapFromEntity(it.user) }
    }

    private fun nameMapToEntity(nameEntity: Name): NameNetworkEntity {
        return NameNetworkEntity(
            title = nameEntity.title,
            first = nameEntity.first,
            last = nameEntity.last
        )
    }

    private fun locationMapToEntity(locationEntity: Location): LocationNetworkEntity {
        return LocationNetworkEntity(
            street = locationEntity.street,
            city = locationEntity.city,
            state = locationEntity.state,
            zip = locationEntity.zip,
        )
    }

    private fun nameMapFromEntity(nameEntity: NameNetworkEntity): Name {
        return Name(
            title = nameEntity.title,
            first = nameEntity.first,
            last = nameEntity.last
        )
    }

    private fun locationMapFromEntity(locationEntity: LocationNetworkEntity): Location {
        return Location(
            street = locationEntity.street,
            city = locationEntity.city,
            state = locationEntity.state,
            zip = locationEntity.zip,
        )
    }

    companion object {
        private const val TAG = "NetworkMapper"
    }


}