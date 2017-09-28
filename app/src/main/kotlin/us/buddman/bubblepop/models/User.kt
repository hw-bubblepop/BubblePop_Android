package us.buddman.bubblepop.models

import java.io.Serializable

/**
 * Created by Junseok Oh on 2017-08-02.
 */

class User() : Serializable {
    var _id: String = ""
    var thumbnail: String = ""
    var email: String = ""
    var password: String = ""
    var nickname: String = ""
    var age: Int = 0
    var location: Int = 0
    var accountType: String = ""
    var heavencard: String = ""
    var phone: String = ""
    var organization: String = ""
    var message: String = ""
    var jobPosition: String = ""
    var messageStr = ""
        get() {
            if(message == "") return "한줄 메세지가 없습니다."
            else return message
        }

    constructor(email: String, nickname: String) : this() {
        this.email = email
        this.nickname = nickname
    }


}
