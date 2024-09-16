package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.Model.ItemsModel
import com.example.onlineshop.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart

class CartAdapter(
    private val listItemsSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.Viewholder>() {
    inner class Viewholder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item = listItemsSelected[position]

        with(holder.binding) {
            titleTxt.text = item.title
            feeEachTime.text = "$${item.price}"
            totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
            numberItemTxt.text = item.numberInCart.toString()


            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(pic)

            plusCartBtn.setOnClickListener {
                managmentCart.plusItem(
                    listItemsSelected,
                    position,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener.onChanged()
                        }

                    })
            }

            minusCartBtn.setOnClickListener {
                managmentCart.minusItem(
                    listItemsSelected,
                    position,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener.onChanged()
                        }

                    })
            }
        }
    }

    override fun getItemCount(): Int = listItemsSelected.size
}