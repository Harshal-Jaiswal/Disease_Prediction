package com.example.mysymptomanalyzer.SymptomAnalyzer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysymptomanalyzer.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mysymptomanalyzer.R.color.colorPrimary;
import static com.example.mysymptomanalyzer.R.color.colorPrimaryDark;
import static com.example.mysymptomanalyzer.R.color.colorWhite;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.MySymptomAdapter> {


    private List<String> symtoms;

    private List<String> selectedSymptoms;

    public SymptomAdapter(List<String> symtoms) {
        this.symtoms = symtoms;
        this.selectedSymptoms = new ArrayList<>();
    }

    public List<String> getSelectedList() {
        return this.selectedSymptoms;
    }

    public void addToList(List<String> symtoms) {
        this.symtoms.addAll(symtoms);
    }

    @NonNull
    @Override
    public MySymptomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.symotom_holder, parent, false);
        return new MySymptomAdapter(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MySymptomAdapter holder, final int position) {
        holder.tv_symptom.setText(symtoms.get(position));
        holder.cv_symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedSymptoms.contains(symtoms.get(position))) {
                    holder.cv_symptom.setCardBackgroundColor(Color.parseColor("#ffffff"));
                    holder.tv_symptom.setTextColor(Color.parseColor("#000000"));
                    selectedSymptoms.remove(symtoms.get(position));
                } else {
                    holder.cv_symptom.setCardBackgroundColor(Color.parseColor("#008577"));
                    holder.tv_symptom.setTextColor(Color.parseColor("#ffffff"));
                    selectedSymptoms.add(symtoms.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return symtoms != null ? symtoms.size() : 0;
    }

    public class MySymptomAdapter extends RecyclerView.ViewHolder {
        private final TextView tv_symptom;
        private final CardView cv_symptom;

        public MySymptomAdapter(@NonNull View itemView) {
            super(itemView);
            tv_symptom = itemView.findViewById(R.id.tv_symptom);
            cv_symptom = itemView.findViewById(R.id.cv_Symptom);
        }
    }
}
