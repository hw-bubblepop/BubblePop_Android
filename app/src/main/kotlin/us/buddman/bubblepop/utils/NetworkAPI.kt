package us.buddman.bubblepop.utils

import android.support.annotation.Nullable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import us.buddman.bubblepop.models.User
import retrofit2.Call
import retrofit2.http.*
import us.buddman.bubblepop.models.ChatRoom

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

    @POST("/auth/auto")
    @FormUrlEncoded
    fun loginByAuto(
            @Field("id") _id: String
    ): Call<User>

    @POST("/auth/register")
    @FormUrlEncoded
    fun registerByLocal(
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("nickname") nickname: String,
            @Field("age") age: Int,
            @Field("location") location: Int,
            @Field("star") star: ArrayList<Int>,
            @Field("phone") phoneNum: String,
            @Field("jobPosition") jobPosition: String,
            @Field("organization") organization: String): Call<User>

    @POST("/user/update/nickname")
    @FormUrlEncoded
    fun updateNickname(
            @Field("id") _id: String,
            @Field("nickname") nickname: String): Call<ResponseBody>

    @POST("/user/update/password")
    @FormUrlEncoded
    fun updatePassword(
            @Field("id") _id: String,
            @Field("password") password: String): Call<ResponseBody>

    @POST("/user")
    @FormUrlEncoded
    fun findUserById(
            @Field("id") _id: String): Call<User>

    @POST("/user")
    @FormUrlEncoded
    fun findUserByEmail(
            @Field("email") email: String): Call<User>

    @POST("/user")
    @FormUrlEncoded
    fun findUserByPhone(
            @Field("phone") phone: String): Call<User>

    @POST("/social/add")
    @FormUrlEncoded
    fun addFriend(
            @Field("id") _id: String, @Field("target_id") targetId: String): Call<User>

    @POST("/social/list")
    @FormUrlEncoded
    fun findMyFriend(
            @Field("id") _id: String): Call<ArrayList<User>>

    @POST("/user/add/card")
    @Multipart
    fun updateMyCard(
            @Part("id") id: RequestBody,
            @Part("card\"; filename=\"card.jpg\" ") card: RequestBody
    ): Call<User>

    @POST("/chat/create")
    @FormUrlEncoded
    fun createChat(@Field("chattype") chattype: String, @Field("title") title: String): Call<ChatRoom>

    @POST("/chat/adduser")
    @FormUrlEncoded
    fun addUserToChat(@Field("chat_room_id") room_id: String, @Field("user_id") user_id: String): Call<ChatRoom>

    @POST("/user/chats")
    @FormUrlEncoded
    fun getChatList(@Field("id") _id: String): Call<ArrayList<ChatRoom>>

}
