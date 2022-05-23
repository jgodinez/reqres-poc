package com.reqres.users.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reqres.component.ImageLoader
import com.reqres.recyclerview.RecyclerViewOnItemClickListener

internal class UsersAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClickListener: RecyclerViewOnItemClickListener<UserModel>? = null
) : RecyclerView.Adapter<UsersViewHolder>() {

    var users = listOf<UserModel>()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.create(parent, imageLoader).apply {
            setOnClickListener(onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        holder.bindTo(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}