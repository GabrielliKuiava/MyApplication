package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Pessoa;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter {
    List<Pessoa> pessoaList = Collections.emptyList();
    Pessoa pessoaSelecionada;

    View.OnClickListener itemClick;

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PessoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ((PessoaViewHolder) holder).bindData(pessoaList.get(position));
    }

    @Override
    public int getItemCount() {
        return pessoaList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_lista;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
        notifyDataSetChanged();
    }

    public void setItemClick(View.OnClickListener itemClick) {
        this.itemClick = itemClick;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    class PessoaViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView nomeTextView;
        private TextView idadeTextView;

        public PessoaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            nomeTextView = itemView.findViewById(R.id.textView_nome);
            idadeTextView = itemView.findViewById(R.id.textView_idade);
        }

        public void bindData(final Pessoa pessoa) {
            nomeTextView.setText(pessoa.getNomeCompleto());
            idadeTextView.setText(pessoa.getIdade());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pessoaSelecionada = pessoa;
                    itemClick.onClick(v);
                }
            });
        }
    }
}

