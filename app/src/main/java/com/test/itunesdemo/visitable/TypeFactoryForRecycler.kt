package com.test.itunesdemo.visitable

import android.view.View
import com.test.itunesdemo.ui.home.items.ItemTrack
import java.lang.RuntimeException

/**
 * Type Factory of Visitable Listing for RecyclerView.
 */
class TypeFactoryForRecycler: TypeFactory {


    override fun type(compact: ItemTrack.Compact): Int = ItemTrack.LAYOUT

    /**
     * Determine the correct type for the visitable
     *
     * usage ex. typeFactory.createViewHolder(contactView, viewType, visitableListener)
     * @see com.test.itunesdemo.visitable.VisitableAdapter
     *
     * @return AbstractBetterViewHolder instance base on type
     */
    override fun createViewHolder(
        parent: View,
        type: Int,
        itemListener: VisitableListener
    ): AbstractBetterViewHolder {
        return when(type){
            ItemTrack.LAYOUT -> ItemTrack(parent, itemListener)
            else -> throw TypeNotSupportedException.create(String.format("LayoutType: %d", type))
        }
    }


    class TypeNotSupportedException(message: String): RuntimeException(message) {
        companion object{
            fun create(message: String): TypeNotSupportedException {
                return TypeNotSupportedException(message)
            }
        }
    }

}