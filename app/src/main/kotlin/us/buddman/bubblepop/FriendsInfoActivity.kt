package us.buddman.bubblepop

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_friends_info.*
import kotlinx.android.synthetic.main.activity_study_info_view.*
import us.buddman.bubblepop.models.User
import android.content.Intent
import android.net.Uri
import android.view.View
import us.buddman.bubblepop.utils.CredentialsManager
import us.buddman.bubblepop.utils.TextUtils


class FriendsInfoActivity : BaseActivity() {

    lateinit var user: User
    @SuppressLint("MissingPermission")
    override fun setDefault() {
        user = intent.getSerializableExtra("userData") as User

        nameText.text = user.nickname
        emailText.text = user.email
        phoneNumberText.text = user.phone
        messageText.text = user.messageStr
        positionText.text = user.jobPosition + " / " + user.organization
        phoneCallGo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + user.phone))
            startActivity(intent)
        }
        phoneSmsGo.setOnClickListener {
            val intentsms = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + user.phone))
            startActivity(intentsms)
        }
        profileImage.setImageURI(Uri.parse(TextUtils.getServerString(user.thumbnail)), applicationContext)
        cardImage.setImageURI(Uri.parse(TextUtils.getServerString(user.cards[0])), applicationContext)
        if (user._id == CredentialsManager.instance.activeUser.second._id) chatGo.visibility = View.GONE
        chatGo.setOnClickListener {
            // TODO Go to Chat
        }
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_friends_info
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }
}
