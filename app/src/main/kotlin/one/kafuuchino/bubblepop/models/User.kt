package one.kafuuchino.bubblepop.models

/**
 * Created by Junseok Oh on 2017-08-02.
 */

class User {
    var userType = 0
    var email: String? = null
    var name: String? = null
    var token: String? = null
    var profile: String? = null
    var profile_img: String? = null
    var facebook_id: String? = null

    constructor() {}

    constructor(email: String, name: String, token: String, profile: String, profile_img: String, facebook_id: String) {
        this.email = email
        this.name = name
        this.token = token
        this.profile = profile
        this.profile_img = profile_img
        this.facebook_id = facebook_id
    }
}
