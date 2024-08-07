package com.solux.flory.data.service

import com.solux.flory.data.dto.BaseResponse
import com.solux.flory.data.dto.request.RequestSignUpDto
import com.solux.flory.data.dto.request.RequestUserInfoDto
import com.solux.flory.data.dto.response.ResponseSignUpDto
import com.solux.flory.data.dto.response.ResponseUserInfoDto
import com.solux.flory.data.service.ApiKeyStorage.API
import com.solux.flory.data.service.ApiKeyStorage.MEMBER
import com.solux.flory.data.service.ApiKeyStorage.SIGNUP
import com.solux.flory.data.service.ApiKeyStorage.V1
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface SignUpApiService {
    @POST("/$API/$V1/$SIGNUP")
    suspend fun postSignUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>

    @PATCH("/$API/$V1/$MEMBER")
    suspend fun patchUserInfo(
        @Body requestUserInfoDto: RequestUserInfoDto
    ): BaseResponse<ResponseUserInfoDto>
}