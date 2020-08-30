package com.eaglesoft.carousel.framework.datasource.network.mappers

import android.util.Log
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.util.EntityMapper
import com.eaglesoft.carousel.framework.datasource.network.model.UserBaseNetworkEntity
import com.eaglesoft.carousel.framework.datasource.network.model.UserNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<UserNetworkEntity, User> {

    override fun mapFromEntity(entity: UserNetworkEntity): User {
        return User(
            /*id = entity.id,*/
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

    override fun mapToEntity(domainModel: User): UserNetworkEntity {
        return UserNetworkEntity(
            /*id = domainModel.id,*/
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


    fun mapFromEntityList(entities: List<UserNetworkEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapFromResultEntityList(entities: UserBaseNetworkEntity): List<User> {
        return entities.results?.map { mapFromEntity(it.user) }
    }

    companion object {
        private const val TAG = "NetworkMapper"
    }


}