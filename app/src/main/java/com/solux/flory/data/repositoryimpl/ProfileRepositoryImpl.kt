package com.solux.flory.data.repositoryimpl

import com.solux.flory.data.datasource.ProfileDataSource
import com.solux.flory.data.dto.request.RequestProfileModifyDto
import com.solux.flory.data.mapper.toProfileUserEntity
import com.solux.flory.domain.entity.ProfileUserEntity
import com.solux.flory.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Result<ProfileUserEntity> {
        return runCatching {
            profileDataSource.getProfile().result?.toProfileUserEntity() ?: ProfileUserEntity(
                "","","",""
            )
        }
    }

    override suspend fun patchProfile(
        nickname: String,
        gender: String,
        birthdate: String
    ): Result<Unit> {
        return runCatching {
            profileDataSource.patchProfile(RequestProfileModifyDto(nickname, gender, birthdate))
        }
    }
}