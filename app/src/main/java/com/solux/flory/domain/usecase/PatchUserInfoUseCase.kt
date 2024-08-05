package com.solux.flory.domain.usecase

import com.solux.flory.data.dto.request.RequestUserInfoDto
import com.solux.flory.domain.repository.SignUpRepository
import javax.inject.Inject

class PatchUserInfoUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(requestUserInfoDto: RequestUserInfoDto) =
        signUpRepository.patchUserInfo(requestUserInfoDto)
}