package com.example.pizzaapp.CartItems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzaapp.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context Ctx;
    private List<Cart> cartList;

    public CartAdapter(Context ctx, List<Cart> cartList) {
        this.Ctx = ctx;
        this.cartList = cartList;
    }


    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.cart_items,null);
        CartViewHolder holder = new CartViewHolder(view);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position) {
        final Cart cart = cartList.get(position);
        cartViewHolder.txtPizzaName.setText(cart.getPizzpizza_type());
        cartViewHolder.txtQuantity.setText(String.valueOf(cart.getQuantity()));
        cartViewHolder.txtPrice.setText(String.valueOf(cart.getPrice()));



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteitem;
        TextView txtPizzaName;
        TextView txtQuantity;
        TextView txtPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteitem = itemView.findViewById(R.id.delete);
            txtPizzaName = itemView.findViewById(R.id.productNameView);
            txtQuantity = itemView.findViewById(R.id.productQuantityView);
            txtPrice = itemView.findViewById(R.id.productPriceView);


            deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(Ctx.getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();


                }
            });

        }
    }
}
