package com.example.mycartapplication;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycartapplication.databinding.CartViewHolderBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CartItem> cartItemList;
    Context context;
    ICartAdapter iCartAdapter;

    public CartAdapter(List<CartItem> cartItemList, Context context, ICartAdapter iCartAdapter) {
        this.cartItemList = cartItemList;
        this.context = context;
        this.iCartAdapter = iCartAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CartViewHolderBinding cartViewHolderBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.cart_item, viewGroup, false);
        return new CartViewHolder(cartViewHolderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof CartViewHolder) {
            CartViewHolder cartViewHolder = (CartViewHolder) viewHolder;
            cartViewHolder.bindData(cartItemList.get(i), i);
        }
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }


    class CartViewHolder extends RecyclerView.ViewHolder {
        private CartViewHolderBinding cartViewHolderBinding;

        public CartViewHolder(CartViewHolderBinding cartViewHolderBinding) {
            super(cartViewHolderBinding.getRoot());
            this.cartViewHolderBinding = cartViewHolderBinding;
        }

        public void bindData(final CartItem cartItem, final int pos) {
            if (cartViewHolderBinding == null) return;
            cartViewHolderBinding.imageView.setImageURI(Uri.parse(cartItem.imgURL));
            cartViewHolderBinding.nameTXT.setText(cartItem.name);
            cartViewHolderBinding.priceTXT.setText("" + cartItem.price);

            cartViewHolderBinding.imgAction.setImageResource(cartItem.isAdded ? R.drawable.ic_remove_black_24dp : R.drawable.ic_add_black_24dp);
            cartViewHolderBinding.imgAction.setVisibility(cartItem.isProcessing ? View.GONE : View.VISIBLE);
            cartViewHolderBinding.progressBar.setVisibility(cartItem.isProcessing ? View.VISIBLE : View.GONE);

            cartViewHolderBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCartAdapter.onClickCard(cartItem, pos);
                }
            });


            cartViewHolderBinding.imgAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCartAdapter.doAction(cartItem, pos);
                }
            });
        }
    }
}
