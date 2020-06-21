package com.example.projectku.kelas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectku.R;
import com.example.projectku.materi.MateriModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.KelasViewHolder>  {
    private List<KelasModel> listKelasModel = new ArrayList<>();

    public KelasAdapter(List<KelasModel> listKelasModel) {
        this.listKelasModel = listKelasModel;
    }

    private OnKelasClickListener listener;

    public interface OnKelasClickListener {
        public void onClick(View view, int position);
    }

    public void setListener(OnKelasClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public KelasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vh = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_kelas, viewGroup, false);

        KelasViewHolder viewHolder = new KelasViewHolder(vh);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KelasViewHolder kelasViewHolder, int i) {

        KelasModel item = listKelasModel.get(i);
        kelasViewHolder.txtnomor.setText(item.getNomor());
        kelasViewHolder.txtnamaSiswa.setText(item.getNamaSiswa());

    }

    @Override
    public int getItemCount() {
        return listKelasModel.size();
    }

    public class KelasViewHolder extends RecyclerView.ViewHolder {
        public TextView txtnomor, txtnamaSiswa;

        public KelasViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnomor = itemView.findViewById(R.id.txtKelasNomor);
            txtnamaSiswa = itemView.findViewById(R.id.txtKelasNamaSiswa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }


}
