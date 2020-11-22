package com.demoapp.listdatagame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class RecyclerGameAdapter extends RecyclerView.Adapter<RecyclerGameAdapter.ViewHolder> {

    private ArrayList<Game> daftarGame;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerGameAdapter(ArrayList<Game> daftarGame, Context context) {
       this.daftarGame = daftarGame;
       this.context = context;
       appDatabase = Room.databaseBuilder(context.getApplicationContext(),
               AppDatabase.class, "db_Game").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ID, Nama;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.id);
            Nama = itemView.findViewById(R.id.nama);
            item = itemView.findViewById(R.id.cvMain);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, final int position) {

        final String getID = daftarGame.get(position).getId();
        final String getNama = daftarGame.get(position).getNama();

        holder.ID.setText(getID);
        holder.Nama.setText(getNama);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = appDatabase.gameDAO().selectDetailGame(daftarGame.get(position).getId());
                context.startActivity(new Intent(context, DetailDataActivity.class).putExtra("detail", game));
            }
        });

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext()).setTitle("Pilih Aksi").setItems(menuPilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                onEditData(position, context);
                                break;

                            case 1:
                                onDeleteData(position);
                                break;

                        }
                    }
                });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }

    private void onEditData (int position, Context context) {
        context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarGame.get(position)));
        ((Activity)context).finish();
    }

    private void onDeleteData (int position) {
        appDatabase.gameDAO().deleteGame(daftarGame.get(position));
        daftarGame.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarGame.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return daftarGame.size();
    }


}
