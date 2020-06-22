package com.example.projectku.nilai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectku.R;

import java.util.ArrayList;
import java.util.List;

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.NilaiViewHolder>  {
    private List<NilaiModel> listNilaiModel = new ArrayList<>();

    public NilaiAdapter(List<NilaiModel> listNilaiModel) {
        this.listNilaiModel = listNilaiModel;
    }

    private OnNilaiClickListener listener;

    public interface OnNilaiClickListener {
        public void onClick(View view, int position);
    }

    public void setListener(OnNilaiClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NilaiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vh = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_nilai, viewGroup, false);

        NilaiViewHolder viewHolder = new NilaiViewHolder(vh);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NilaiViewHolder nilaiViewHolder, int i) {

        NilaiModel item = listNilaiModel.get(i);
        nilaiViewHolder.txtTugas.setText(item.getTugas());
        nilaiViewHolder.txtNilai.setText(item.getNilai());
        nilaiViewHolder.txtJam.setText(item.getJammengerjakan());
    }

    @Override
    public int getItemCount() {
        return listNilaiModel.size();
    }

    public class NilaiViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTugas, txtNilai, txtJam;

        public NilaiViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTugas = itemView.findViewById(R.id.txtNamaMapelNilai);
            txtNilai = itemView.findViewById(R.id.txtNilai);
            txtJam = itemView.findViewById(R.id.txtTglMengerjakan);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}
