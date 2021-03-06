package com.wheny.whenynetlibrary.api

import com.wheny.whenynetlibrary.data.BaseResp
import com.wheny.whenynetlibrary.entity.UserInfo
import io.reactivex.Flowable
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WhenYApiService {
    /**
     * 用户信息
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("user/personal_list_info")
    fun getPersonalListInfo(@Field("cur_page") page: Int): Call<Response<String?>?>?


    @Headers("Content-Type: application/json")//需要添加头
    @POST("/umc/doService")
    fun login(@Body req : RequestBody) : Flowable<BaseResp<UserInfo?>>

    @Headers("Content-Type: application/json")//需要添加头
    @POST("/umc/doService")
    fun login1(@Body req : RequestBody) : Deferred<BaseResp<UserInfo?>>


}