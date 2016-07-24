package com.example.barnes.ummoqmasterdashboard.ummoAPI;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.barnes.ummoqmasterdashboard.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by barnes on 3/6/16.
 */
public class QMaster
{
    private Activity callingActivity;
    private String uCellNumber;
    private boolean registered=false;
    private String uName;


    public void register(final String name,final String cell,final String email,final String qService,final String category) {
        try {

            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/register";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",cell);
            formPoster.add("service",qService);
            formPoster.add("email",email);
            formPoster.add("category",category);
            formPoster.add("name",name);
            formPoster.add("data", "data");
            Log.e("REGISTER", urlString);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            //response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        //((QMasterListener) callingActivity).userRegistered(objString);
                        //This would mean the registration was compleate
                        //
                        //JSONObject obj = new JSONObject(objString);
                        //Set the Shared Preferences for User Name and CellNumber
                        Log.e("REGISTER",objString);
                        if(objString.equals("SUCCESS")){
                            SharedPreferences sp = PreferenceManager
                                    .getDefaultSharedPreferences(callingActivity);
                            sp.edit().putString(callingActivity.getString(R.string.PREF_USER_NAME), name).apply();
                            sp.edit().putString(callingActivity.getString(R.string.PREF_USER_CELLNUMBER), cell).apply();
                            sp.edit().putBoolean(callingActivity.getString(R.string.PREF_USER_REGISTERED), true).apply();
                        }


                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).registered(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        Log.e("RegistraionE",ioe.toString());
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).registrationError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }

    public boolean isRegistered() {
        return registered;
    }


    public void createQ(String frame,String qname,String qTag) {
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/createQ";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("qframe",frame);
            formPoster.add("qName",qname);
            formPoster.add("tag",qTag);
            formPoster.add("data", "data");
            Log.e("MY CELL",uCellNumber);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).qCreated(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).createQError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }

    public void destroyQ() {
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/destroyQ";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).qDestroyed(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onQDestroyError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }

    public void getQUpdates(String caller) {
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/updates";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            Log.e("GETUPDATES","Called by "+caller);
            uCellNumber=getCellNumb();
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\n');
                            Log.e("Updates", response.toString());
                        }
                        rd.close();

                        final String objString = response.toString();

                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("Object", objString);
                                ((QMasterListener) callingActivity).updatesRecieved(objString);
                            }
                        });




                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }



    public void getMyQ(String caller) {
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            Log.e("GETUPDATES","Called by "+caller);
            uCellNumber=getCellNumb();
            formPoster.add("id",uCellNumber);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\n');
                            Log.e("Updates", response.toString());
                        }
                        rd.close();

                        final String objString = response.toString();

                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("Object", objString);
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });




                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }



    public void dQUser(String caller) {
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/deQUser";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).userDQd(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUserDQError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }
    public QMaster(Activity activity){
        setCallingActivity(activity);
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(callingActivity);
        registered = sp.getBoolean(callingActivity.getString(R.string.PREF_USER_REGISTERED), false);
        if (registered) {
            uName = sp.getString(callingActivity.getString(R.string.PREF_USER_NAME), "NO NAME");
            uCellNumber = sp.getString(callingActivity.getString(R.string.PREF_USER_CELLNUMBER), "NO NUMBER");
            //   Intent intent = new Intent(Register.this, MainActivity.class);
            //  startActivity(intent);
            //  finish();
            Log.d("prefs", "registered");
        }
    }

    public void setCallingActivity(Activity activity) {
        callingActivity = activity;
    }


    public void setName() {

    }



    public void setCellNumber() {

    }

    public String getName() {
        return uName;
    }

    public String getCellNumb() {
        return uCellNumber;
    }

    public void editName(String name){
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editName";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("name",name);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }


    }

    public void editRequirements(String requirement){

        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editRequirements";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("requirements",requirement);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }

    public void editStart(String start){
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editStart";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("start",start);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }



    }

    public void editEnd(String end){

        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editEnd";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("end",end);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }

    public void editLimit(String limit){
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editLimit";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("limit",limit);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }



    }

    public void editLimitLength(String limitLength){
        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editLimitLength";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("limitLength",limitLength);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }



    }

    public void editActive(String active){

        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editActive";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("active",active);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }

    public void editCity(String city){

        try {
            String urlString = callingActivity.getString(R.string.SERVER_URL)+"qMaster/editCity";
            final FormPoster formPoster = new FormPoster(new URL(urlString));
            formPoster.add("cellnum",uCellNumber);
            formPoster.add("city",city);
            //Log.e("DEQUEING", caller);
            //formPoster.add("ucellnum",ucellnum);
            formPoster.add("data", "data");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = formPoster.post();

                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        final StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
                        String line;
                        while((line = rd.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();

                        final String objString = response.toString();
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener) callingActivity).myQRecieved(objString);
                            }
                        });


                    }

                    catch (final IOException ioe){
                        callingActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((QMasterListener)callingActivity).onUpdtaesError(ioe.toString());
                            }
                        });
                    }

                }
            });

            thread.start();


        }

        catch (MalformedURLException me){
            Log.e("NetWork Exception", me.toString());
        }




    }
}
