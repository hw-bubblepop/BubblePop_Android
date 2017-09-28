package us.buddman.bubblepop.utils

import android.app.Application
import android.content.Context
import android.net.Uri
import com.facebook.FacebookSdk
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_friends_info.*

/**
 * Created by Junseok Oh on 2017-07-09.
 */

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Fresco.initialize(context);
    }

    companion object {
        var context: Context? = null
    }

}
