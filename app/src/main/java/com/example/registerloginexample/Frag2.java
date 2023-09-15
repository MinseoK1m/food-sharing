package com.example.registerloginexample;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Frag2 extends Fragment {

    private String login_id;
    private ImageView btn_want;
    private ImageView btn_add;
    private static final int ADD_PRODUCT_REQUEST = 1;
    private List<ProductItem> addedProductList = new ArrayList<>();
    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private LinearLayout previewLayout;
    private TextView refIdTextView;
    private TextView productNameTextView;
    private TextView quantityTextView;
    private TextView expiryDateTextView;
    private ImageView productImageView;
    private Uri photoUri;
    private static final int REQUEST_IMAGE_CAPTURE = 100;

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle2 = getArguments();
        if (bundle2 != null) {
            login_id = bundle2.getString("login_id");
            Log.d("Frag2", "Received login_id: " + login_id);
        }

        View view = inflater.inflate(R.layout.frag2, container, false);

        btn_want = view.findViewById(R.id.btn_want);
        btn_add = view.findViewById(R.id.btn_add);
        previewLayout = view.findViewById(R.id.previewLinearLayout);
        refIdTextView = view.findViewById(R.id.refIdTextView);
        productNameTextView = view.findViewById(R.id.productNameTextView);
        quantityTextView = view.findViewById(R.id.quantityTextView);
        expiryDateTextView = view.findViewById(R.id.expiryDateTextView);
        productImageView = view.findViewById(R.id.productImageView);

        btn_want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Want.class);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add.class);
                intent.putExtra("login_id", login_id);
                startActivityForResult(intent, ADD_PRODUCT_REQUEST); // startActivityForResult로 Add 액티비티를 호출
            }
        });

        addedProductList = new ArrayList<>();
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productAdapter = new ProductAdapter(addedProductList, getContext());
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productRecyclerView.setAdapter(productAdapter);


        getProductsFromServer();

        return view;
    }
    static class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
        private final List<ProductItem> productList;
        private final Context context;

        public ProductAdapter(List<ProductItem> productList, Context context) {
            this.productList = productList;
            this.context = context;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Log.d("ProductAdapter", "onBindViewHolder called for position: " + position); // 추가한 로그
            ProductItem productItem = productList.get(position);

            if (productItem.getImageUri() != null) {
                Glide.with(context)
                        .load(productItem.getImageUri())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_settings))
                        .into(holder.productImageView);
            } else {
                holder.productImageView.setImageDrawable(null);
            }

            holder.productNameTextView.setText("상품 이름: " + productItem.getProductName()); // 상품 이름 앞에 "상품 이름: "을 붙임
            holder.quantityTextView.setText("수량: " + productItem.getQuantity()); // 수량 앞에 "수량: "을 붙임
            holder.expiryDateTextView.setText("유통기한: " + productItem.getExpiryDate()); // 유통기한 앞에 "유통기한: "을 붙임
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        static class ProductViewHolder extends RecyclerView.ViewHolder {
            ImageView productImageView;
            TextView refIdTextView;
            TextView productNameTextView;
            TextView quantityTextView;
            TextView expiryDateTextView;

            public ProductViewHolder(@NonNull View itemView) {
                super(itemView);

                refIdTextView = itemView.findViewById(R.id.refIdTextView);
                productImageView = itemView.findViewById(R.id.productImageView);
                productNameTextView = itemView.findViewById(R.id.productNameTextView);
                quantityTextView = itemView.findViewById(R.id.quantityTextView);
                expiryDateTextView = itemView.findViewById(R.id.expiryDateTextView);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK && data != null) {
            Integer refId = data.getIntExtra("refId", 0);
            String productName = data.getStringExtra("productName");
            String quantity = data.getStringExtra("quantity");
            String expiryDate = data.getStringExtra("expiryDate");
            photoUri = data.getParcelableExtra("photoUri");

            boolean isDuplicateRefId = false;
            for (ProductItem item : addedProductList) {
                if (item.getRefId() == refId) {
                    isDuplicateRefId = true;
                    break;
                }
            }

            if (isDuplicateRefId) {
                Toast.makeText(getContext(), "중복된 refId입니다. 다른 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                ProductItem productItem = new ProductItem(refId, productName, quantity, expiryDate, photoUri);
                addedProductList.add(0, productItem);
                productAdapter.notifyItemInserted(0);

                Log.d("Frag2", "Added new product: " + productItem.getProductName());

                updatePreview(productItem);
                productRecyclerView.scrollToPosition(0);
            }
        }
    }

    private void getProductsFromServer() {
        // 서버에서 데이터 가져오는 코드를 넣으세요.
        String url = "http://3.209.169.0/get_products.php";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<ProductItem> productList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject productObject = response.getJSONObject(i);
                                int refId = productObject.getInt("ref_id");
                                String productName = productObject.getString("f_name");
                                String quantity = productObject.getString("f_count");
                                String expiryDate = productObject.getString("end_date");

                                ProductItem productItem = new ProductItem(refId, productName, quantity, expiryDate, null);
                                productList.add(productItem);

                                Log.d("Frag2", "refId: " + refId);
                                Log.d("Frag2", "productName: " + productName);
                                Log.d("Frag2", "quantity: " + quantity);
                                Log.d("Frag2", "expiryDate: " + expiryDate);

                            }


                            // 서버에서 받아온 데이터로 어댑터 업데이트
                            addedProductList.addAll(productList);
                            productAdapter.notifyDataSetChanged();

                            Log.d("Frag2", "서버에서 데이터를 성공적으로 파싱했습니다.");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "JSON 파싱 오류", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            String errorMessage = new String(error.networkResponse.data);

                            Log.e("ServerResponseError", "HTTP 상태 코드: " + statusCode);
                            Log.e("ServerResponseError", "오류 메시지: " + errorMessage);
                        } else {
                            Log.e("ServerResponseError", "네트워크 응답 오류 발생");
                        }

                        Toast.makeText(getContext(), "서버 응답 오류", Toast.LENGTH_SHORT).show();
                    }
                }

        );

        Volley.newRequestQueue(getContext()).add(request);
    }


    private void updatePreview(ProductItem productItem) {

        if (addedProductList.isEmpty()) {
            previewLayout.setVisibility(View.GONE);
        } else {
            previewLayout.setVisibility(View.VISIBLE);

            refIdTextView.setText("냉장고 번호: " + productItem.getRefId());
            productNameTextView.setText("상품 이름: " + productItem.getProductName());
            quantityTextView.setText("수량: " + productItem.getQuantity());
            expiryDateTextView.setText("유통기한: " + productItem.getExpiryDate());

            if (productItem.getImageUri() != null) {
                try {
                    Bitmap photoBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), productItem.getImageUri());
                    productImageView.setImageBitmap(photoBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                productImageView.setImageDrawable(null);
            }
        }
    }
}
