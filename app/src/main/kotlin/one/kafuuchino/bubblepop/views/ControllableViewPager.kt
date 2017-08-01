package one.kafuuchino.bubblepop.views

/**
 * Created by Junseok Oh on 2017-07-16.
 */

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class ControllableViewPager : ViewPager {
    private var swipeEnabled: Boolean = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (swipeEnabled) {
            return super.onInterceptTouchEvent(ev)
        } else {
            if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
                // ignore move action
            } else {
                if (super.onInterceptTouchEvent(ev)) {
                    super.onTouchEvent(ev)
                }
            }
            return false
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (swipeEnabled) {
            return super.onTouchEvent(ev)
        } else {
            return MotionEventCompat.getActionMasked(ev) != MotionEvent.ACTION_MOVE && super.onTouchEvent(ev)
        }
    }

    fun setPagingswipeEnabled(swipeEnabled: Boolean) {
        this.swipeEnabled = swipeEnabled
    }
}
