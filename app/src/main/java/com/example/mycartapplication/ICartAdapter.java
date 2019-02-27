package com.example.mycartapplication;

public interface ICartAdapter {
    void onClickCard(CartItem cartItem, int position);

    void doAction(CartItem cartItem, int position);
}
