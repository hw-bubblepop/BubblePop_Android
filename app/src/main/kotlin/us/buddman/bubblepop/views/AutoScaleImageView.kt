package us.buddman.bubblepop.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Junseok on 2017-09-24.
 */
class AutoScaleImageView : ImageView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val width = measuredWidth
//        val height = measuredWidth * (5 / 9)
//        setMeasuredDimension(width, height)
//        super.onMeasure(width, height)
//    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            val width = measuredWidth
            val height = (measuredHeight * (5 / 9))
            setMeasuredDimension(width, height)
    }

}