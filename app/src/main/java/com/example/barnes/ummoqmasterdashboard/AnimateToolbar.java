package com.example.barnes.ummoqmasterdashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;
import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMaster;
import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMasterListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnimateToolbar extends AppCompatActivity implements View.OnClickListener, QMasterListener
{
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    SimpleRecyclerAdapter simpleRecyclerAdapter;

    private static final int NATIVE_THEME = Integer.MIN_VALUE;
    private int mTheme = -1;
    private Toast mToast = null;
    private List<String> mCheckedItems = new ArrayList<String>();

    List<String> listData = new ArrayList<String>();
    List<String> listData2 = new ArrayList<String>();
    List<String> listData3 = new ArrayList<String>();
    List<String> listData4 = new ArrayList<String>();

    @Override
    public void qCreated(String string) {

    }

    @Override
    public void registrationError(String string) {

    }

    @Override
    public void createQError(String string) {

    }

    @Override
    public void registered(String string) {

    }

    @Override
    public void qDestroyed(String string) {

    }

    @Override
    public void userMoved(String string) {

    }

    @Override
    public void userDQd(String string) {

    }

    @Override
    public void feedBackRecieved(String string) {

    }

    @Override
    public void myQRecieved(String string) {
        try {
            JSONObject object = new JSONObject(string);
            String qLength = String.valueOf(object.getJSONObject("managedQ").getJSONObject("qErs").length());
            VersionModel.setQlength(qLength);
        }
        catch (JSONException jse){
            Log.e("JSONE",jse.toString());
        }

        try {
            JSONObject object = new JSONObject(string);
            String qActive = object.getJSONObject("managedQ").getString("qActive");
            VersionModel.setQactive(qActive);
        }
        catch (JSONException jse){
            Log.e("JSONE",jse.toString());
        }
        try {
            JSONObject object = new JSONObject(string);
            String city = object.getJSONObject("managedQ").getString("city");
            VersionModel.setTown(city);
        }
        catch (JSONException jse){
            Log.e("JSONE",jse.toString());
        }

        try {
            JSONObject object = new JSONObject(string);
            String requirements = object.getJSONObject("managedQ").getString("qRequirements");
            VersionModel.setQrequirements(requirements);
        }
        catch (JSONException jse){
            Log.e("JSONE",jse.toString());
        }
        try {
            JSONObject object = new JSONObject(string);
            String qname = object.getJSONObject("managedQ").getString("qName");



            //String qStart = object.getJSONObject("managedQ").getJSONObject("qFrame").getString("start");
            //String qEnd = object.getJSONObject("managedQ").getJSONObject("qFrame").getString("end");
            VersionModel.setQName(qname);


            simpleRecyclerAdapter.notifyDataSetChanged();

        }

        catch (JSONException jse){
            Log.e("RECIEVEDQ",jse.toString());
        }
    }

    @Override
    public void updatesRecieved(String string) {

    }


    @Override
    public void onUpdtaesError(String string) {

    }

    @Override
    public void onFeedBackError(String string) {

    }

    @Override
    public void onUserMoveError(String string) {

    }

    @Override
    public void onUserDQError(String string) {

    }

    @Override
    public void onQDestroyError(String sting) {

    }

    //End Overloads

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_toolbar);
        QMaster qMaster = new QMaster(this);
        qMaster.getMyQ("ONCREATE");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Ummo");
        ImageView header = (ImageView) findViewById(R.id.header);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
        /*FloatingActionButton account_fab = (FloatingActionButton) findViewById(R.id.accounts);
        //account_fab.setBackgroundColor(getResources().getColor(R.color.material_login_register_color));
        account_fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showMultiChoiceListAlertDialog();
            }
        });*/

        mTheme = R.style.Base_Theme_AlertDialogPro_Material_Light;
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener()
        {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette)
            {
                mutedColor = palette.getMutedColor(R.color.black);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        int ct = 0;
        for (int i = 0; i < VersionModel.data.length; i++)
        {
            listData.add(VersionModel.data[ct]);
            ct++;
            if (ct == VersionModel.data.length)
            {
                ct = 0;
            }
        }

        int dt = 0;
        for (int i = 0; i < VersionModel.data2.size(); i++)
        {
            listData2.add(VersionModel.data2.get(dt));
            dt++;
            if (dt == VersionModel.data2.size())
            {
                dt = 0;
            }
        }

        int dt1 = 0;
        for (int i = 0; i < VersionModel.data3.length; i++)
        {
            listData3.add(VersionModel.data3[dt1]);
            dt1++;
            if (dt1 == VersionModel.data3.length)
            {
                dt1 = 0;
            }
        }

        int dt2 = 0;
        for (int i = 0; i < VersionModel.data4.length; i++)
        {
            listData4.add(VersionModel.data4[dt2]);
            dt2++;
            if (dt2 == VersionModel.data4.length)
            {
                dt2 = 0;
            }
        }

        setSimpleRecyclerAdapter(listData, VersionModel.data2);
        findViewById(R.id.accounts).setOnClickListener(this);
    }

    public void setSimpleRecyclerAdapter(List<String> listData, List<String> listData1)
    {
        //listData = new ArrayList<String>();
        //listData1 = new ArrayList<String>();
        simpleRecyclerAdapter = new SimpleRecyclerAdapter(listData,listData1);
        recyclerView.setAdapter(simpleRecyclerAdapter);
    }

    private AlertDialog.Builder createAlertDialogBuilder()
    {
        if (mTheme == -1)
        {
            return new AlertDialog.Builder(AnimateToolbar.this);
        }

        return new AlertDialogPro.Builder(AnimateToolbar.this, mTheme);
    }

    private void showToast(CharSequence toastText)
    {
        if (mToast != null)
        {
            mToast.cancel();
        }
        mToast = Toast.makeText(AnimateToolbar.this, toastText, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void showMultiChoiceListAlertDialog()
    {




        final String[] list = new String[]{""};
        final List<ItemModel> data = new ArrayList<>();
        createAlertDialogBuilder().setTitle("Select Queue To Manage").setMultiChoiceItems(list, new boolean[]{false, false, false},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked && which == 0) {
                            mCheckedItems.add(list[which]);
                            setSimpleRecyclerAdapter(listData, listData2);
                            mCheckedItems.remove(list[1]);
                            mCheckedItems.remove(list[2]);
                            dialog.cancel();
                        } else if (isChecked && which == 1) {
                            mCheckedItems.add(list[which]);
                            setSimpleRecyclerAdapter(listData, listData3);
                            mCheckedItems.remove(list[0]);
                            mCheckedItems.remove(list[2]);
                            dialog.cancel();
                        } else if (isChecked && which == 2) {
                            mCheckedItems.add(list[which]);
                            setSimpleRecyclerAdapter(listData, listData4);
                            mCheckedItems.remove(list[0]);
                            mCheckedItems.remove(list[1]);
                            dialog.cancel();
                        } else {
                            mCheckedItems.remove(list[which]);
                            //setSimpleRecyclerAdapter(listData, listData2);
                        }
                        //showToast(list[which] + " is " + (isChecked ? "checked" : "unchecked" + "."));
                    }
                })
                .setPositiveButton("Manage Queue", new ButtonClickedListener(""))
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.accounts:
                //showMultiChoiceListAlertDialog();
                Intent i = new Intent(this,Main_A_Activity.class);
                startActivity(i);
                break;
        }
    }

    private class ButtonClickedListener implements DialogInterface.OnClickListener
    {
        private CharSequence mShowWhenClicked;
        public ButtonClickedListener(CharSequence showWhenClicked)
        {
            mShowWhenClicked = showWhenClicked;
        }
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            //showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
            Intent i = new Intent(AnimateToolbar.this, QCreateQForm.class);
            startActivity(i);
        }
    }
}