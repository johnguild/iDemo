package com.test.itunesdemo.visitable


/**
 * Polymorphic type for recycler item instance.
 *
 * All recycler item must be a descendant of this class
 *
 * @see com.test.itunesdemo.visitable.VisitableAdapter
 * @see com.test.itunesdemo.visitable.AbstractBetterViewHolder
 * @see com.test.itunesdemo.ui.home.items.ItemTrack.bind
 */
interface Visitable {
    fun type(typeFactory: TypeFactory): Int
}

/**
 * Polymorphic listener for recycler item instance
 *
 * All recycler item view holder listeners must implement this.
 *
 *
 * @see com.test.itunesdemo.visitable.AbstractBetterViewHolder
 * @see com.test.itunesdemo.ui.home.items.ItemTrack.Listener
 */
interface VisitableListener