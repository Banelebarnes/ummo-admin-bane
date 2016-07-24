package com.example.barnes.ummoqmasterdashboard.ummoAPI;

/**
 * Created by barnes on 3/6/16.
 */
public class JoinedQ
{
    private String qName;
    private String qId;
    private String ttdq;
    private String tltdq;
    private String cellnumb;
    private String myPos;
    private int qLength;
    private String myAlphanumCode;
    //private Qfragment fragment;

    public JoinedQ(){

    }

    public JoinedQ(String name,String id, String ttq, String ltq, String myp,int lenth,String alp){
        qName=name;
        qId=id;
        ttdq=ttq;
        tltdq=ltq;
        myPos=myp;
        qLength=lenth;
        myAlphanumCode=alp;
        //    fragment=f;
    }

    public String getqName(){
        return qName;
    }

    public String getqId(){
        return qId;
    }

    public String getTtdq(){
        return ttdq;
    }

    public String getTltdq(){
        return tltdq;
    }

    public String getMyAlphanumCode(){
        return myAlphanumCode;
    }

    public String getMyPos(){
        return myPos;
    }

 /*   public Qfragment getFragment(){
        return fragment;
    }

    public void setName(String name){
        qName = name;
    }

    public void setqId(String id){
        qId = id;

    }

    public void setTtdq(String tdq){
        ttdq = tdq;
    }

    public void setTltdq(String _tltdq){
        tltdq=_tltdq;
    }

    public void setFragment(Qfragment _fragment){
        fragment=_fragment;
    }

    public JoinedQ(JSONObject object,String cell){
        try {
            cellnumb = cell;
            qName=object.getJSONObject("managedQ").getString("qName");
            qId=object.getString("qId");
            myPos=object.getJSONObject("managedQ").getJSONObject("qErs").getJSONObject(cellnumb).getString("position");
            myAlphanumCode=object.getJSONObject("managedQ").getJSONObject("qErs").getJSONObject(cellnumb).getString("numCode");
        }

        catch (JSONException jse){
            Log.e("JOINEDQ", jse.toString());
        }
    }*/
}
