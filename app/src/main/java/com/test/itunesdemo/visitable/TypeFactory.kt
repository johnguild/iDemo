package com.test.itunesdemo.visitable

import android.view.View
import com.test.itunesdemo.ui.home.items.ItemTrack
import com.test.itunesdemo.visitable.AbstractBetterViewHolder
import com.test.itunesdemo.visitable.VisitableListener

/**
 * Item Factory for Visitable listings.
 */
interface TypeFactory {

    fun createViewHolder(parent: View, type: Int, itemListener: VisitableListener): AbstractBetterViewHolder

    fun type(compact: ItemTrack.Compact): Int

}