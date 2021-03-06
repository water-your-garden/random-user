package com.example.randomuser.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuser.R
import com.example.randomuser.databinding.UserItemBinding
import com.example.randomuser.domain.UserModel

class UserListAdapter(private val onClickListener: OnClickListener) :
    PagedListAdapter<UserModel, UserListAdapter.UserViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userItemBinding = DataBindingUtil.inflate<UserItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent,
            false
        )
        return UserViewHolder(userItemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(user!!)
        }
        if (user != null) {
            holder.bind(user!!)
        }

    }

    class UserViewHolder(private var binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel) {
            binding.user = userModel
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.uuid == newItem.uuid
        }

    }

    class OnClickListener(val clickListener: (userModel: UserModel) -> Unit) {
        fun onClick(userModel: UserModel) = clickListener(userModel)
    }
}
