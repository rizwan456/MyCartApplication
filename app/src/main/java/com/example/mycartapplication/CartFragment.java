package com.example.mycartapplication;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mycartapplication.databinding.CartFragmentBinding;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment implements ICartAdapter {

    CartFragmentBinding cartFragmentBinding;
    CartAdapter cartAdapter;
    List<CartItem> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cartFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);

        setUp();
        return cartFragmentBinding.getRoot();
    }

    private void setUp() {

        cartFragmentBinding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cartFragmentBinding.recycleView.setAdapter(cartAdapter = new CartAdapter(list = generateCartList(), getActivity(), this));
    }


    public List<CartItem> generateCartList() {
        List<CartItem> list = new ArrayList<>();
        list.add(new CartItem("Apple", "https://media.istockphoto.com/photos/red-apple-with-leaf-picture-id683494078?k=6&m=683494078&s=612x612&w=0&h=aVyDhOiTwUZI0NeF_ysdLZkSvDD4JxaJMdWSx2p3pp4=", 20));
        list.add(new CartItem("Banana", "https://media.istockphoto.com/photos/banana-picture-id636739634?k=6&m=636739634&s=612x612&w=0&h=BQ9Z6DobjFzclh3LN7nKSljrRqycJPCq65CS8rtUHU4=", 50));
        list.add(new CartItem("Orange", "https://media.istockphoto.com/photos/orange-fruit-isolated-on-white-picture-id477836156?k=6&m=477836156&s=612x612&w=0&h=so0IENCIE95_bgdadhstzVSBoAOqEyAnwr1TirAXdsY=", 70));
        list.add(new CartItem("Mango", "https://images-na.ssl-images-amazon.com/images/I/41EvGpCFECL.jpg", 100));
        list.add(new CartItem("Carrot", "https://www.culturedfoodlife.com/wp-content/uploads/2017/04/Carrot.png", 60));
        list.add(new CartItem("WaterMellon", "https://i5.walmartimages.ca/images/Large/805/2_r/6000196088052_R.jpg", 30));
        return list;
    }

    @Override
    public void onClickCard(CartItem cartItem, int position) {
        Toast.makeText(getActivity(), cartItem.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doAction(CartItem cartItem, final int position) {
        if (list.get(position).isProcessing) {
            Toast.makeText(getActivity(), "Busy", Toast.LENGTH_SHORT).show();
            return;
        }

        list.get(position).isProcessing = true;

        cartAdapter.notifyItemChanged(position);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.get(position).isProcessing = false;
                list.get(position).isAdded = list.get(position).isAdded ? false : true;
                cartAdapter.notifyItemChanged(position);
            }
        }, 1500);
    }
}
