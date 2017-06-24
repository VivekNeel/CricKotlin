@file:JvmName("ViewGroupExtensionUtil")

package cricket.`in`.cricket.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by vivek on 24/06/17.
 */

fun ViewGroup.inflateLayout(resId: Int): View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}
