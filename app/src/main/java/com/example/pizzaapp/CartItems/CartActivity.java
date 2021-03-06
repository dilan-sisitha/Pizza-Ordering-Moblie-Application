package com.example.pizzaapp.CartItems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzaapp.IpAdress;
import com.example.pizzaapp.MenuList.RecycleViewMenu;
import com.example.pizzaapp.Payment.PaymentType;
import com.example.pizzaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    Double totalPrice =0.0;
    Total total = new Total();
    TextView priceTxt;

    RecyclerView recyclerView;
    CartAdapter adapter;

    List<Cart> cartList;
    final static String url = IpAdress.ip+"/demo/allcart";

    private Button checkout;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        getData();





    }



    private void getData(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray carts = new JSONArray(response);

                            for (int i =0;i<carts.length();i++){

                                JSONObject cartobject = carts.getJSONObject(i);

                                int id = cartobject.getInt("orderId");
                                String pizza = cartobject.getString("pizza_type");
                                int quantity = cartobject.getInt("quantity");
                                Double price = cartobject.getDouble("price");
                                Double Unitprice = cartobject.getDouble("unitPrice");

                                totalPrice = totalPrice + price;
                                total.setTotalPrice(totalPrice);

                                Cart cart = new Cart(id,pizza,quantity,price,Unitprice);
                                cartList.add(cart);
                            }

                            adapter = new CartAdapter(CartActivity.this,cartList);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(cartList.size()==0){
                            priceTxt = findViewById(R.id.totalPrice);
                            priceTxt.setText("0.00");
                        }else {
                            priceTxt = findViewById(R.id.totalPrice);
                            priceTxt.setText(String.valueOf(Total.getTotalPrice()));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CartActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);


    }


    public void checkout(View view) {
        if(cartList.size()==0) {
            Toast.makeText(CartActivity.this,"add items to cart",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, PaymentType.class);
            startActivity(intent);
        }
        //Toast.makeText(CartActivity.this,"ffffffffff",Toast.LENGTH_SHORT).show();
    }

    public void goback(View view) {
        Intent intent = new Intent(this, RecycleViewMenu.class);
        startActivity(intent);
    }
}

