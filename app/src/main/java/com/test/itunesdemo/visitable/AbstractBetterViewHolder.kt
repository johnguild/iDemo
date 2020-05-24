package com.test.itunesdemo.visitable

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Main wrapper or parent for listing items Visitable Model.
 */
public abstract class AbstractBetterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(element: Visitable)

    /**
     * Retrieves string from StringRes
     *
     * @return String based on id.
     */
    protected fun getString(@StringRes stringRes: Int): String{
        return itemView.context.getString(stringRes)
    }
}