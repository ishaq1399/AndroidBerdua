package com.example.projectku.materi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectku.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MateriViewHolder> {
    private List<MateriModel> listMateriModel = new ArrayList<>();

    public MateriAdapter(List<MateriModel> listMateriModel) {
        this.listMateriModel = listMateriModel;
    }

    private OnMateriClickListener listener;

    public interface OnMateriClickListener {
        public void onClick(View view, int position);
    }

    public void setListener(OnMateriClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MateriViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vh = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_materi, viewGroup, false);

        MateriViewHolder viewHolder = new MateriViewHolder(vh);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MateriViewHolder materiViewHolder, int i) {

        MateriModel item = listMateriModel.get(i);
        materiViewHolder.txtNamaPengajar.setText(item.getNamapengajar());
        materiViewHolder.txtNamaMateri.setText(item.getNamamateri());
        Picasso.get().load(item.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(materiViewHolder.imageMateri);
    }

    @Override
    public int getItemCount() {
        return listMateriModel.size();
    }

    public class MateriViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageMateri;
        public TextView txtNamaMateri, txtNamaPengajar;

        public MateriViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMateri = itemView.findViewById(R.id.imageMateri);
            txtNamaMateri = itemView.findViewById(R.id.txtNamaMateri);
            txtNamaPengajar = itemView.findViewById(R.id.txtNamapengajar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }


}
