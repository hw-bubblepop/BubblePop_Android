package one.kafuuchino.bubblepop.utils

import one.kafuuchino.bubblepop.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Junseok Oh on 2017-08-02.
 */
interface NetworkAPI {
    @GET("/auth/facebook/token")
    fun loginByFacebook(@Query("access_token") token: String) : Call<User>
}
