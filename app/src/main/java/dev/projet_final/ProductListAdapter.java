package dev.projet_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, price, unit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.item_name);
            this.price = itemView.findViewById(R.id.price);
            this.unit = itemView.findViewById(R.id.unit);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setPrice(double price) {
            this.price.setText(String.format(Locale.getDefault(), "%.2f", price));
        }

        public void setUnit(int stringId) {
            this.unit.setText(stringId);
        }
    }
}
