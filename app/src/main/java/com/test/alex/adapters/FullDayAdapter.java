package com.test.alex.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.alex.Pojo.DaysModel;
import com.test.alex.R;

import java.util.ArrayList;

public class FullDayAdapter extends RecyclerView.Adapter<FullDayAdapter.mViewHolder>{

    private Context context;
    private ArrayList<DaysModel> data;

    public FullDayAdapter(Context context, ArrayList<DaysModel> data){
        this.context = context;
        this.data = data;
    }



    class mViewHolder extends RecyclerView.ViewHolder {

        protected TextView nameTrain, namePlace,
                timeStart, timeEnd, description, coachName, shortName, position;
        protected ImageView coachImage;
        protected View color;

        protected mViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTrain = itemView.findViewById(R.id.name);
            namePlace = itemView.findViewById(R.id.place);
            timeStart = itemView.findViewById(R.id.time_start);
            timeEnd = itemView.findViewById(R.id.time_end);
            description = itemView.findViewById(R.id.description);
            coachName = itemView.findViewById(R.id.name_instructor);
            shortName = itemView.findViewById(R.id.short_name);
            position = itemView.findViewById(R.id.position_inctructor);
            coachImage = itemView.findViewById(R.id.instructor_photo);
            color = itemView.findViewById(R.id.mcolored);
        }

    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.day_full_list, viewGroup, false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder vh, int position) {
        vh.nameTrain.setText(data.get(position).getName());
        vh.namePlace.setText(data.get(position).getPlace());
        vh.timeStart.setText(data.get(position).getStartTime());
        vh.timeEnd.setText(data.get(position).getEndTime());
        if (data.get(position).getDescription().trim().length() == 0) {
            vh.description.setText("(Информация отсутствует)");
        } else {
            vh.description.setText(data.get(position).getDescription());
        }
        vh.coachName.setText(data.get(position).getTeacher());
        vh.shortName.setText("(" + data.get(position).getTeacherV2().getShortName() + ")");
        vh.position.setText(data.get(position).getTeacherV2().getPosition());
        Picasso.get().load(data.get(position).getTeacherV2().getImageUrl()).error(R.drawable.ic_coach).into(vh.coachImage);
        vh.color.setBackgroundColor(Color.parseColor(data.get(position).getColor()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
