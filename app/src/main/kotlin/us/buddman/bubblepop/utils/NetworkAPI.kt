package us.buddman.bubblepop.utils

import android.support.annotation.Nullable
import okhttp3.ResponseBody
import us.buddman.bubblepop.models.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Junseok Oh on 2017-08-02.
 */
interface NetworkAPI {

    @POST("/auth/login")
    @FormUrlEncoded
    fun loginByLocal(
            @Field("email") email: String,
            @Field("password") password: String
    ): Call<User>

    @POST("/auth/register")
    @FormUrlEncoded
    fun registerByLocal(
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("nickname") nickname: String,
            @Field("age") age: Int,
            @Field("location") location: Int,
            @Field("star") star: ArrayList<Int>): Call<User>

    @POST("/user/update/nickname")
    @FormUrlEncoded
    fun updateNickname(
            @Field("_id") _id: String,
            @Field("nickname") nickname: String): Call<ResponseBody>

    @POST("/user/update/password")
    @FormUrlEncoded
    fun updatePassword(
            @Field("_id") _id: String,
            @Field("password") password: String): Call<ResponseBody>


}
