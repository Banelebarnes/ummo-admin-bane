package com.example.barnes.ummoqmasterdashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMaster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder>
{
    List<String> versionModels;
    List<String> versionModels2;
    Boolean isHomeList = false;
    ViewGroup viewGroup;
    public static List<String> homeActivitiesList = new ArrayList<String>();
    public static List<String> homeActivitiesSubList = new ArrayList<String>();
    Context context;
    OnItemClickListener clickListener;

    public void setHomeActivitiesList(Context context)
    {
        String[] listArray = context.getResources().getStringArray(R.array.home_activities);
        String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
        for (int i = 0; i < listArray.length; ++i)
        {
            homeActivitiesList.add(listArray[i]);
            homeActivitiesSubList.add(subTitleArray[i]);
        }
    }

    public SimpleRecyclerAdapter(Context context)
    {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(context);
    }

    public SimpleRecyclerAdapter(List<String> versionModels, List<String> versionModels2)
    {
        isHomeList = false;
        this.versionModels = versionModels;
        this.versionModels2 = versionModels2;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup1, int i)
    {
        viewGroup = viewGroup1;
        View view = LayoutInflater.from(viewGroup1.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    public void showChangeLangDialog()
    {

    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, int i)
    {
        if (isHomeList)
        {
            versionViewHolder.title.setText(homeActivitiesList.get(i));
            versionViewHolder.subTitle.setText(homeActivitiesSubList.get(i));
        }
        else
        {
            versionViewHolder.title.setText(versionModels.get(i));
            versionViewHolder.subTitle.setText(versionModels2.get(i));

            if (versionViewHolder.title.getText().toString().equals("q Name (Service)"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Name (Service)");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //versionViewHolder.subTitle.setText();
                                // Do something with value!
                                new QMaster((AnimateToolbar)v.getContext()).editName(input.getText().toString());
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.

                                //VersionModel.setQName("MOSAIC2.O");
                                //notifyDataSetChanged();
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Length"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Length");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                // Do something with value!
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Requirements"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Requirements");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                new QMaster((AnimateToolbar)v.getContext()).editRequirements(input.getText().toString());
                                // Do something with value!
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Start Time"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Start Time");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                new QMaster((AnimateToolbar)v.getContext()).editStart(input.getText().toString());

                                // Do something with value!
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q End Time"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q End Time");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                // Do something with value!
                                new QMaster((AnimateToolbar)v.getContext()).editEnd(input.getText().toString());
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Limit"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Limit");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                // Do something with value!

                                new QMaster((AnimateToolbar)v.getContext()).editLimit(input.getText().toString());
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Limit Length"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Limit Length");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                // Do something with value!
                                new QMaster((AnimateToolbar)v.getContext()).editLimitLength(input.getText().toString());
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Active"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Active");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                // Do something with value!
                                new QMaster((AnimateToolbar)v.getContext()).editActive(input.getText().toString());
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("q Town/City"))
            {
                versionViewHolder.fab.setImageResource(R.drawable.ic_create_white);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v)
                    {
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        alert.setTitle("q Town/City");
                        //alert.setMessage("Message");
                        // Set an EditText view to get user input
                        final EditText input = new EditText(v.getContext());
                        alert.setView(input);
                        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                // Do something with value!
                                new QMaster((AnimateToolbar)v.getContext()).editCity(input.getText().toString());
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });
                        alert.show();
                    }
                });
            }
            else if (versionViewHolder.title.getText().toString().equals("Manage Queue"))
            {
                versionViewHolder.fab.setVisibility(View.VISIBLE);
                versionViewHolder.fab.setImageResource(R.drawable.ic_action_name);
                versionViewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent swipe_cards = new Intent(v.getContext(), SwipeCards.class);
                        v.getContext().startActivity(swipe_cards);
                        //finish();
                        //overridePendingTransition(R.layout.fadein, R.layout.fadeout);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount()
    {
        if (isHomeList)
            return homeActivitiesList == null ? 0 : homeActivitiesList.size();
        else
            return versionModels == null ? 0 : versionModels.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CardView cardItemLayout;
        TextView title;
        TextView subTitle;
        FloatingActionButton fab;

        public VersionViewHolder(View itemView)
        {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_name);
            subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);
            fab = (FloatingActionButton) itemView.findViewById(R.id.btn_edit);

            if (isHomeList)
            {
                itemView.setOnClickListener(this);
            }
            else
            {
                //subTitle.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
