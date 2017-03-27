package com.swaroop.shopperstop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.swaroop.shopperstop.databinding.ActivityHomeBinding;
import com.swaroop.shopperstop.model.Product;
import com.swaroop.shopperstop.recycler.OnItemSelectedListener;
import com.swaroop.shopperstop.recycler.ProductAdapter;
import com.swaroop.shopperstop.recycler.ProductItemPaddingDecoration;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CartActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private List<Product> fakeProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        fakeProducts = Product.createFakeProducts();

        initRecycler(binding.productsRecycler);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initRecycler(RecyclerView productsRecycler) {
        productsRecycler.setHasFixedSize(true);

        productsRecycler.setAdapter(new ProductAdapter(fakeProducts));
        productsRecycler.addItemDecoration(new ProductItemPaddingDecoration(this));
        productsRecycler.addOnItemTouchListener(new OnItemSelectedListener(this) {
            @Override
            public void onItemSelected(RecyclerView.ViewHolder holder, int position) {
                OrderDialogFragment.newInstance(fakeProducts.get(position)).show(getSupportFragmentManager(), null);
            }
        });
    }
}
