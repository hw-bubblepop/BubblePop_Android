package us.buddman.bubblepop.utils

import us.buddman.bubblepop.models.User
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Junseok Oh on 2017-08-02.
 */
interface NetworkAPI {

    @POST("/auth/login")
    @FormUrlEncoded
    fun loginByLocal(email : String, password : String) : Call<User>

}
