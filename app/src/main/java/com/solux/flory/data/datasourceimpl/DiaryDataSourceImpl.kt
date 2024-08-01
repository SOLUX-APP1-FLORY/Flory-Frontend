package com.solux.flory.data.datasourceimpl

import com.solux.flory.data.datasource.DiaryDataSource
import com.solux.flory.data.dto.BaseResponse
import com.solux.flory.data.dto.request.RequestDiaryWriteDto
import com.solux.flory.data.service.DiaryApiService
import javax.inject.Inject

class DiaryDataSourceImpl @Inject constructor(
    private val diaryApiService: DiaryApiService
) : DiaryDataSource {
    override suspend fun postDiary(requestDiaryWriteDto: RequestDiaryWriteDto): BaseResponse<String> {
        return diaryApiService.postDiary(requestDiaryWriteDto)
    }
}