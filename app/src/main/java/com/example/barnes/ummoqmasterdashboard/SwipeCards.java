package com.example.barnes.ummoqmasterdashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;
import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMaster;
import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMasterListener;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by barnes on 2/7/16.
 */
public class SwipeCards extends AppCompatActivity implements QMasterListener
{
    private SwipeDeck cardStack;
    TextView name;
    TextView alphaNums;

    private Toolbar mToolbar;
    //private DrawerLayout mDrawerLayout;
    //NavigationView mNavigationView;
    FrameLayout mContentFrame;

    //private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private int mCurrentSelectedPosition;


    private static final String PREFERENCES_FILE = "mymaterialapp_settings";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    final ArrayList<String> testData = new ArrayList<>();
    final SwipeDeckAdapter adapter = new SwipeDeckAdapter(testData, this);

    int[] mMaterialColors;
    String[] names;
    String[] alphas;
    Random RANDOM = new Random();
    MaterialLetterIcon mIcon;


    //Qmaster Listener Overides start here

    @Override
    public void qCreated(String string) {

    }

    @Override
    public void registered(String string) {

    }

    @Override
    public void qDestroyed(String string) {

    }

    @Override
    public void userDQd(String string) {

    }

    @Override
    public void userMoved(String string) {

    }

    @Override
    public void feedBackRecieved(String string) {

    }

    @Override
    public void myQRecieved(String string) {

    }

    @Override
    public void updatesRecieved(String string) {

        Log.d("YayUpdates",string);

        try{
            JSONArray qers= new JSONArray(string);

            //Log.d("Names",qersJSON.names().get(0).toString());
            for(int j=0;j<qers.length();j++) {
                for (int i = 0; i < qers.length(); i++) {
                    if (qers.getJSONObject(i).getInt("position") > testData.size()) {

                    } else {
                        if(!testData.contains(qers.getJSONObject(i).toString())) {
                            JSONObject jobject = qers.getJSONObject(i);
                            //jobject.put("id",qersJSON.names().get(i).toString());
                            testData.add(jobject.getInt("position"), jobject.toString());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }

        catch (JSONException jse){
            Log.e("UPDATESJS",jse.toString());
        }

    }

    //When sheets goes wrong execute the code below
    //I pray it doesnt get to this

    @Override
    public void createQError(String string) {

    }

    @Override
    public void registrationError(String string) {

    }

    @Override
    public void onQDestroyError(String sting) {

    }

    @Override
    public void onUserDQError(String string) {

    }

    @Override
    public void onUserMoveError(String string) {


    }

    @Override
    public void onFeedBackError(String string) {

    }

    @Override
    public void onUpdtaesError(String string) {
        Log.e("ONUPDATES",string);
    }

    //QMaster Listener Overrides WEnd Here



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_deck);
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        setUpToolbar();
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        //mUserLearnedDrawer = Boolean.valueOf(readSharedSetting(this, PREF_USER_LEARNED_DRAWER, "false"));
        //if (savedInstanceState != null) {
          //  mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            //mFromSavedInstanceState = true;
        //}
        //setUpNavDrawer();

        //mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);
        //TextView nametv = (TextView)mNavigationView.getHeaderView(0).findViewById(R.id.nametv);
        //nametv.setText(new QMaster(this).getName());
        /*mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        Snackbar.make(mContentFrame, "Item One", Snackbar.LENGTH_SHORT).show();
                        mCurrentSelectedPosition = 0;
                        return true;
                    case R.id.navigation_item_2:
                        Snackbar.make(mContentFrame, "Item Two", Snackbar.LENGTH_SHORT).show();
                        mCurrentSelectedPosition = 1;
                        return true;
                    default:
                        return true;
                }
            }
        });*/


        final QMaster qMaster = new QMaster(this);
        //TextView nametv = (TextView)findViewById(R.id.nametv);
        //nametv.setText("hello World");

        qMaster.getQUpdates("CARDS ONCREATE");

        /*final ArrayList<String> testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");
        */
        final View view = getCurrentFocus();
        final ViewGroup parent = (ViewGroup) getWindow().getDecorView().getRootView();

        BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);

        cardStack.setAdapter(adapter);
        //final View finalV;
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position)
            {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);
                //testData.add("5");
                //cardStack.setAdapter(adapter);
            }

            @Override
            public void cardSwipedRight(int position)
            {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);
                //testData.add("5");
                //cardStack.setAdapter(adapter);
                qMaster.dQUser("CARD SWIPERIGHT");
            }
            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
            }
        });
        // cardStack.setLeftImage(R.id.left_image);
        // cardStack.setRightImage(R.id.right_image);
        /*FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.fab);
        //Button btn3 = (Button) findViewById(R.id.button3);
        FAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //testData.add("a sample string.");
                //adapter.notifyDataSetChanged();
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(SwipeCards.this);
                builderSingle.setIcon(R.drawable.ic_launcher);
                builderSingle.setTitle("FNB:Withdrawals");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        SwipeCards.this, android.R.layout.simple_selectable_list_item);
                for (int i = 0;i < alphas.length;i++)
                {
                    //arrayAdapter.add(alphas[RANDOM.nextInt(alphas.length)]);
                    arrayAdapter.add(alphas[i]);
                }

                builderSingle.setNegativeButton(
                        "cancel", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });

                builderSingle.setAdapter(
                        arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(SwipeCards.this);
                                builderInner.setMessage(strName);
                                builderInner.setTitle("Your Selected Item is");
                                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builderInner.show();
                            }
                        });
                builderSingle.show();
            }
        });*/
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cardStack.swipeTopCardLeft(380);
            }
        });
        FloatingActionButton btn2 = (FloatingActionButton) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cardStack.swipeTopCardRight(380);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class SwipeDeckAdapter extends BaseAdapter
    {
        private List<String> data;
        private Context context;
        public SwipeDeckAdapter(List<String> data, Context context)
        {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View v = convertView;
            if (v == null) {
                try {
                    JSONObject jsonObject = new JSONObject(testData.get(position));
                    LayoutInflater inflater = getLayoutInflater();
                    // normally use a viewholder
                    v = inflater.inflate(R.layout.test_card2, parent, false);
                    name = (TextView) v.findViewById(R.id.name);
                    //name.setText("Banele");
                    alphaNums = (TextView) v.findViewById(R.id.alphNumeric);
                    mIcon = (MaterialLetterIcon) v.findViewById(R.id.icon);
                    //Resources res = this.getResources();

                    mMaterialColors = v.getContext().getResources().getIntArray(R.array.colors);
                    names = v.getContext().getResources().getStringArray(R.array.string_array_name);
                    alphas = v.getContext().getResources().getStringArray(R.array.alphanums);

                    mIcon.setShapeColor(mMaterialColors[position]);
                    mIcon.setInitials(true);
                    mIcon.setInitialsNumber(2);
                    mIcon.setLetterSize(18);

                    String rand_name = jsonObject.getString("name");
                    mIcon.setLetter(rand_name);
                    name.setText(rand_name);
                    alphaNums.setText(jsonObject.getString("numCode"));
                }

                catch (JSONException jse){
                    Log.e("INFLATINGCARDS",jse.toString());
                }
            }


            //((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //String item = (String)getItem(position);
                    //Log.i("MainActivity", item);
                }
            });
            return v;
        }
    }


    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }
    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }
}