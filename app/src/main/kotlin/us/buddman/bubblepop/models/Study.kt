package us.buddman.bubblepop.models

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Junseok on 2017-09-23.
 */
data class Study(
        var title: String,
        var content: String,
        var like: Int,
        var comment: Int,
        var date: Date
) {
    var dateStr: String = ""
        get() = SimpleDateFormat("yyyy.MM.dd").format(date)
    var likeStr: String = ""
        get() = like.toString()
    var commentStr: String = ""
        get() = comment.toString()
}