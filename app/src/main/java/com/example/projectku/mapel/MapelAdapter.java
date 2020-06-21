package com.example.projectku.mapel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectku.R;


import java.util.ArrayList;
import java.util.List;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.MapelViewHolder>  {
    private List<MapelModel> listMapelModel = new ArrayList<>();

    public MapelAdapter(List<MapelModel> listMapelModel) {
        this.listMapelModel = listMapelModel;
    }

    private OnMapelClickListener listener;

    public interface OnMapelClickListener {
        public void onClick(View view, int position);
    }

    public void setListener(OnMapelClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MapelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vh = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_mapel, viewGroup, false);

        MapelViewHolder viewHolder = new MapelViewHolder(vh);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MapelViewHolder mapelViewHolder, int i) {

        MapelModel item = listMapelModel.get(i);
        mapelViewHolder.txtNamaPengajar.setText(item.getNamapengajar());
        mapelViewHolder.txtNamaMapel.setText(item.getNamaMapel());
        mapelViewHolder.txtHari.setText(item.getHari());
        mapelViewHolder.txtJam.setText(item.getJamMulai()+" - "+item.getJamSelesai());
    }

    @Override
    public int getItemCount() {
        return listMapelModel.size();
    }

    public class MapelViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaPengajar, txtHari, txtNamaMapel, txtJam;

        public MapelViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHari = itemView.findViewById(R.id.txtHari);
            txtNamaMapel = itemView.findViewById(R.id.txtNamaMapel);
            txtNamaPengajar = itemView.findViewById(R.id.txtPengajar);
            txtJam = itemView.findViewById(R.id.txtJamMapel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }


}
