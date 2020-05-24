package com.test.itunesdemo.visitable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Main Recycler Adapter.
 * Visitable approach let us use only 1 type of adapter for
 * different recycler instance.
 *
 * We are avoiding the getItemViewType being bombarded with
 * different types and some other cases creates different adapter
 * for different recycler.
 */
class VisitableAdapter(
    private val typeFactory: TypeFactory,
    private val visitableListener: VisitableListener
) : RecyclerView.Adapter<AbstractBetterViewHolder>() {

    var elements = arrayListOf<Visitable>()

    /**
     * NOTE: This function should not be edited.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBetterViewHolder {
        val context = parent.context
        /**
         * attention: [viewType] as resource
         */
        val contactView = LayoutInflater.from(context).inflate(viewType, parent, false)
        return typeFactory.createViewHolder(contactView, viewType, visitableListener)
    }

    /**
     * NOTE: This function should not be edited.
     */
    override fun onBindViewHolder(holder: AbstractBetterViewHolder, position: Int) {
        holder.bind(elements[position])
    }

    /**
     * NOTE: This function should not be edited.
     */
    override fun getItemViewType(position: Int): Int {
        return elements[position].type(typeFactory)
    }

    /**
     * NOTE: This function should not be edited.
     */
    override fun getItemCount(): Int {
        return elements.size
    }


    /**
     * update the listing value of the adapter
     */
    fun updateList(list: ArrayList<Visitable>){
        elements = list
        notifyDataSetChanged()
    }

    /**
     * adds new visitable instance to the list
     */
    fun addItem(item: Visitable) {
        val position = elements.count()
        elements.add(item)
        notifyItemInserted(position)
    }


}