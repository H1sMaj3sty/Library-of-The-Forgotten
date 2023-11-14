package com.example.libraryoftheforgotten;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ItemData> values;
    private LayoutInflater inflater;
    public ItemAdapter(Context context, ArrayList<ItemData> values) {
        this.context = context;
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData data = values.get(position);
        holder.titleText.setText(data.itemTitle);
        holder.authorText.setText(data.itemAuthor);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, authorText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.text1);
            authorText=itemView.findViewById(R.id.text2);
        }
    }
}
