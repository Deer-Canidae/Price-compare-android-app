package dev.projet_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final Product[] products;

    public ProductListAdapter(List<Product> productList) {
        products = productList.toArray(new Product[]{});
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setProduct(products[position]);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, price, unit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.item_name);
            this.price = itemView.findViewById(R.id.price);
            this.unit = itemView.findViewById(R.id.unit);
        }

        public void setProduct(Product product) {
            setName(product.name);
            setPrice(product.price);
            setUnit(product.unitId);
        }

        private void setName(String name) {
            this.name.setText(name);
        }

        private void setPrice(float price) {
            this.price.setText(String.format(Locale.getDefault(), "%.2f", price));
        }

        private void setUnit(int stringId) {
            this.unit.setText(stringId);
        }
    }
}
