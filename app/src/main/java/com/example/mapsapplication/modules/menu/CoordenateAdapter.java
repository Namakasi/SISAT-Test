package com.example.mapsapplication.modules.menu;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapsapplication.R;
import com.example.mapsapplication.core.OnItemClickListener;
import com.example.mapsapplication.model.Coordenate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class CoordenateAdapter extends RecyclerView.Adapter<CoordenateAdapter.CoordenateViewHolder> {

    private List<Coordenate> coordenateList;
    private Context context;
    private OnItemClickListener clickListener;

    public CoordenateAdapter(Context context, OnItemClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CoordenateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoordenateViewHolder(LayoutInflater.from(context).inflate(R.layout.item_coordenates, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoordenateViewHolder holder, int position) {
        holder.title.setText(context.getString(R.string.text_group_title, position+1));
        holder.coordenate = coordenateList.get(position);
        if(position == 0)
            holder.imageButton.setVisibility(View.GONE);
        holder.setWacthers();
        holder.setClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return coordenateList.size();
    }

    public void removeItem(int position){
        coordenateList.remove(position);
        notifyDataSetChanged();
    }

    public void setItems(List<Coordenate> coordenateList){
        this.coordenateList = coordenateList;
        notifyDataSetChanged();
    }

    class CoordenateViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextInputEditText latitude;
        private TextInputEditText longitude;
        private TextInputLayout latitudeLayout;
        private TextInputLayout longitudeLayout;
        private ImageButton imageButton;
        private Coordenate coordenate;

        public CoordenateViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.label_title);
            latitude = itemView.findViewById(R.id.edit_latitude);
            longitude = itemView.findViewById(R.id.edit_longitude);
            imageButton = itemView.findViewById(R.id.image_delete);
            latitudeLayout = itemView.findViewById(R.id.label_latitude);
            longitudeLayout = itemView.findViewById(R.id.label_longitude);
        }

        public void setWacthers() {
            latitude.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length() > 1){
                        coordenate.setLatitude(Double.parseDouble(charSequence.toString()));
                        if(!coordenate.isLatitudeValuesValid())
                            latitudeLayout.setError(context.getString(R.string.error_latitude));
                        else
                            latitudeLayout.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            longitude.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length() > 1){
                        coordenate.setLongitude(Double.parseDouble(charSequence.toString()));
                        if(!coordenate.isLongitudeValuesValid())
                            longitudeLayout.setError(context.getString(R.string.error_longitude));
                        else
                            longitudeLayout.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        public void setClickListener(final OnItemClickListener listener){
            imageButton.setOnClickListener(view -> listener.onClick(imageButton, getAdapterPosition()));
        }
    }
}
