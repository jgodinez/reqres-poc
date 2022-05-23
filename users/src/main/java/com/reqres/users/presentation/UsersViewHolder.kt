package com.reqres.users.presentation

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.reqres.component.ImageLoader
import com.reqres.recyclerview.BaseViewHolder
import com.reqres.recyclerview.RecyclerViewOnItemClickListener
import com.reqres.users.R
import com.reqres.users.databinding.UserItemLayoutBinding
import com.reqres.viewbinding.viewBinding

internal class UsersViewHolder private constructor(
    private val binding: UserItemLayoutBinding,
    private val imageLoader: ImageLoader
) : BaseViewHolder<UserModel>(binding.root), View.OnClickListener {

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader): UsersViewHolder {
            val binding = parent.viewBinding(UserItemLayoutBinding::inflate)
            return UsersViewHolder(binding, imageLoader)
        }
    }

    private lateinit var user: UserModel
    private var onItemClickListener: RecyclerViewOnItemClickListener<UserModel>? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun bindTo(model: UserModel) {
        user = model

        with(binding) {
            tvName.text = user.fullName
            tvEmail.text = user.email
            imageLoader.load(user.avatar)
                .placeholder(R.drawable.ic_avatar)
                .centerCrop(Gravity.CENTER)
                .into(ivAvatar)
        }
    }

    override fun onClick(v: View?) {
        onItemClickListener?.onClick(user)
    }

    fun setOnClickListener(onItemClickListener: RecyclerViewOnItemClickListener<UserModel>?) {
        this.onItemClickListener = onItemClickListener
    }
}