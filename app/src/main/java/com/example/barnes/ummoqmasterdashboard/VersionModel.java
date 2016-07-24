package com.example.barnes.ummoqmasterdashboard;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class VersionModel {
    public String name;
    public String name2;

    public static final String[] data = {"Service Name", "Length of Queue", "Requirements for Service",
            "Start Time", "End Time", "Limit","Minimum Length of Queue", "Active", "Town/City", "Manage Queue"};

    static String[] stray =  {"Name not Loaded yet", "length not loaded yet", "Requirements not loaded yet",
            "time not loaded yet", "time not loaded yet", "...","*", "not sure", "Town not loaded yet", "Manage Que"};

    public static List<String> data2 = Arrays.asList(stray);

    public static void setTown(String town){
        data2.set(8,town);
    }
    public static void setQactive(String qactive){
        data2.set(7,qactive);
    }
    public static void setQLimitLength(String qLimitLength){
        data2.set(6,qLimitLength);
    }
    public static void setQlimit(String qlimit){
        data2.set(5,qlimit);
    }
    public static void setEndTime(String endTime){
        data2.set(4,endTime);
    }
    public static void setQrequirements(String _require){
        data2.set(2,_require);
    }
    public static void setStartTime(String _start){
        data2.set(3,_start);
    }
    public static void setQlength(String _qlength){
        data2.set(1,_qlength);
    }

    public static final String[] data3 = {"Standard Bank Withdrawals", "40", "Account Number, ID",
            "8:30pm", "4:30pm", "No","*", "Yes", "Mbabane", "Manage Que"};

    public static final String[] data4 = {"Standard Bank Foreign Exchange", "5", "ID",
            "8:30pm", "4:30pm", "No","*", "Yes", "Mbabane", "Manage Que"};

    VersionModel(String name, String name2){
        this.name = name;
        this.name2 = name2;
    }

    public static void setQName(String _qname){
        data2.set(0,_qname);
    }


}

