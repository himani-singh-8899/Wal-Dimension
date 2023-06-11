package com.example.wal_dimension;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wal_dimension.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;

    ProductAdapter productAdapter;
    ArrayList<Product> products;
    private static final int CAMERA_REQUEST_CODE = 123;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1001;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private Button button;

    private DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(MainActivity.this, "Fire Base Connection Success", Toast.LENGTH_LONG).show();

        reff = FirebaseDatabase.getInstance().getReference().child("wal-dimension");

        reff.child("test").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String age = snapshot.getValue(String.class);
                    System.out.println("test: "+age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error!!");
            }
        });

        initCategories();
        initProducts();
        initSlider();
    }

    void initCategories() {
        categories = new ArrayList<>();
        List<String> footballImages = Arrays.asList("football.png", "elect.png", "furnish.png","jewels.png","kitchen.png","garden.png","cloth.png","travel.png");
        categories.add(new Category("Outdoor", "football", "#18ab4e", "Some Desc", 1));
        categories.add(new Category("Fashion", "cloth", "#f7d003", "Some Desc", 2));
        categories.add(new Category("Electronics", "elect", "#fb0504", "Some Desc", 3));
        categories.add(new Category("Furniture", "furnish", "#4186ff", "Some Desc", 4));
        categories.add(new Category("Kitchen", "kitchen", "#BF360C", "Some Desc", 5));
        categories.add(new Category("Jewellery", "jewels", "#ff870e", "Some Desc", 6));
        categories.add(new Category("Garden", "garden", "#ff6f52", "Some Desc", 7));
        categories.add(new Category("Travel", "travel", "#7825c6", "Some Desc", 8));
        categoryAdapter = new CategoryAdapter(this, categories);

        for (Category category : categories) {
            int imageResId = getResources().getIdentifier(category.getIcon(), "drawable", getPackageName());
            category.setId(imageResId);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    void initProducts(){
        products = new ArrayList<>();
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));
        products.add(new Product("BasketBall Shoes", "placeholder", "some Desc", 12, 12, 1, 1));

        for (Product product : products) {
            int placeholderResId = getResources().getIdentifier("placeholder", "drawable", getPackageName());
            product.setImage(String.valueOf(placeholderResId));
        }


        productAdapter = new ProductAdapter(this, products);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);


    }


    private void initSlider(){
        int banner1ResId = getResources().getIdentifier("banner_2", "drawable", getPackageName());
        int banner2ResId = getResources().getIdentifier("banner_3", "drawable", getPackageName());
        int banner3ResId = getResources().getIdentifier("banner_1", "drawable", getPackageName());

        CarouselItem banner1 = new CarouselItem(banner1ResId, "" );
        CarouselItem banner2 = new CarouselItem(banner2ResId, "" );
        CarouselItem banner3 = new CarouselItem(banner3ResId, "" );

        binding.carousel.addData(banner1);
        binding.carousel.addData(banner2);
        binding.carousel.addData(banner3);
    }
}
