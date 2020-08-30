package com.eaglesoft.carousel.framework.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.eaglesoft.carousel.R
import com.eaglesoft.carousel.business.domain.models.User
import kotlinx.android.synthetic.main.item_user_carousel.view.*

class UserItemAdapter(
    private val context: Context?
) : RecyclerView.Adapter<UserItemAdapter.UserViewHolder>() {
    var userList: MutableList<User>? = null

    init {
        userList = arrayListOf()
    }

    fun setData(list: MutableList<User>?) {
        userList = list
        notifyDataSetChanged()
    }

    fun removeTopItem() {
        userList?.removeAt(0)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_user_carousel, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList?.get(position))
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User?) {
            itemView.iv_profile.load(user?.picture) {
                crossfade(true)
                error(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }
            setUserDetails(
                context?.getString(R.string.label_my_name_is), user?.username
            )
            itemView.rg_bottom.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.rb_name -> {
                        setUserDetails(
                            context?.getString(R.string.label_my_name_is), user?.username
                        )
                    }
                    R.id.rb_dob -> {
                        setUserDetails(
                            context?.getString(R.string.label_my_dob_is),
                            user?.dob.toString()
                        )
                    }
                    R.id.rb_location -> {
                        setUserDetails(
                            context?.getString(R.string.label_my_address_is), user?.email
                        )
                    }
                    R.id.rb_call -> {
                        setUserDetails(
                            context?.getString(R.string.label_my_phone_is), user?.phone
                        )
                    }
                    R.id.rb_password -> {
                        setUserDetails(
                            context?.getString(R.string.label_my_password), user?.SSN
                        )
                    }
                }
            }
        }

        private fun setUserDetails(title: String?, description: String?) {
            itemView.tv_title.text = title
            itemView.tv_description.text = description
        }
    }
}