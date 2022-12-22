package dev.projet_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    private final Product[] products;
    private final Context appCtx;
    private final RvClickHandler clickHandler;

    public ProductListAdapter(List<Product> productList, Context appCtx, RvClickHandler clickHandlingObject) {
        products = productList.toArray(new Product[]{});
        this.appCtx = appCtx;
        this.clickHandler = clickHandlingObject;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ViewHolder(view, clickHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setProduct(products[position], appCtx);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, price, unit;
        private Product product;

        public ViewHolder(@NonNull View itemView, RvClickHandler clickHandlerObject) {
            super(itemView);

            this.name = itemView.findViewById(R.id.item_name);
            this.price = itemView.findViewById(R.id.price);
            this.unit = itemView.findViewById(R.id.unit);

            itemView.setOnClickListener(view -> clickHandlerObject.deleteSelectedItem(product));
        }

        public void setProduct(Product product, Context appCtx) {
            this.product = product;
            setName(product.name);
            setPrice(product.price);
            setUnit(product.unitId, appCtx);
        }

        private void setName(String name) {
            this.name.setText(name);
            // Tried a fix but it somehow made it worse
            //this.name.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        }

        private void setPrice(float price) {
            this.price.setText(String.format(Locale.getDefault(), "%.2f", price));
        }

        private void setUnit(int unitId, Context appCtx) {
            this.unit.setText(Unit.getUnitList(appCtx)[unitId].getUnitStringRes());
        }
    }
}
