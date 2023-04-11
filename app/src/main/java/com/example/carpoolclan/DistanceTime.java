package com.example.carpoolclan;


import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


//import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.example.carpoolclan.BuildConfig.MAPS_API_KEY;
//@Component
public class DistanceTime {


    private static final String API_KEY=MAPS_API_KEY;
    static OkHttpClient client = new OkHttpClient();

    public String reformatSourceAndDestinationsFromDouble (double[] latLong){
        String reformatted = "";
        String comma = "%2C"; // , (comma between lat & long)
        String bar = "%7C"; // | (bar between multiple locations)

        // make sure even
        if (latLong.length % 2 ==0){

            for (int i = 0; i < latLong.length; i =i+2){
                reformatted += String.valueOf(latLong[i]);
                reformatted += comma;
                reformatted += String.valueOf(latLong[i+1]);

                if (!(i == latLong.length)){

                    reformatted += bar;
                }
            }
        }
        else{
            System.out.println("Error in data to be reformatted, missing lat long pairs");
        }


        return reformatted;
    }

    public  String reformatSourceAndDestinationsFromString (String[] latLong){
        String reformatted = "";
        String comma = "%2C"; // , (comma between lat & long)
        String bar = "%7C"; // | (bar between multiple locations)

        // make sure even
        if (latLong.length % 2 ==0){

            for (int i = 0; i < latLong.length; i =i+2){
                reformatted += (latLong[i]);
                reformatted += comma;
                reformatted += (latLong[i+1]);

                if (!(i == latLong.length)){

                    reformatted += bar;
                }
            }
        }
        else{
            System.out.println("Error in data to be reformatted, missing lat long pairs");
        }


        return reformatted;
    }


    public  String calculate(String source ,String destination) throws IOException {
        String url="https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="+source+"&destinations="+destination+"&key="+ API_KEY;
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();


    }

    public  double[] parseJsonIndexToDouble(String response, int originIndex, int destinationIndex){

        JSONParser parser = new JSONParser();
        double [] ans = new double [2];
        try {

            Object obj = parser.parse(response);
            JSONObject jsonobj=(JSONObject)obj;

            JSONArray dist=(JSONArray)jsonobj.get("rows");
            JSONObject obj2 = (JSONObject)dist.get(originIndex);
            JSONArray disting=(JSONArray)obj2.get("elements");
            JSONObject obj3 = (JSONObject)disting.get(destinationIndex);
            JSONObject obj4=(JSONObject)obj3.get("distance");
            JSONObject obj5=(JSONObject)obj3.get("duration");
            System.out.println(obj4.get("value"));
            System.out.println(obj5.get("value"));

            String temp1 = (obj4.get("value") +" ");
            String temp2 = (obj5.get("value")+ " ");

            ans[0] = (Double.parseDouble(temp1))/1000;
            ans[1] = (Double.parseDouble(temp2))/60;

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return ans;
    }


    public double [] reformatJsonResults(String[] values){

        double [] reformatted = new double[values.length];

        for (int i =0; i < values.length; i=i+2){

            String end1 = values[i];

            if ( end1.contains("km")){

                end1 = values[i].replace("km", "");
                end1 = end1.strip();

                if(end1.contains(",")){

                    end1 = end1.replaceAll(",","");
                }
                reformatted[i] = Double.parseDouble(end1);
            }
            if ( end1.contains("m")){
                end1 = end1.replace("m", "");
                end1 = end1.strip();

                // convert to km
                reformatted[i] = Double.parseDouble(end1)/1000;

            }


            // first value is km or meters (m)
            //reformatted[i] = Double.parseDouble(end1);

            // second value is in days hr min ...
            String input = values[i+1].strip();
            String adjusted = null;

            if(input.contains("days") || input.contains("day")){
                adjusted = input.replaceAll("\\s+(days|day)\\s+", "DT");
                adjusted = adjusted.replaceAll("\\s+(hours|hour)", "H");
                adjusted = adjusted.replaceAll("\\s+(mins|min)", "M");
            }
            else if(input.contains("hours") || input.contains("hour")){
                adjusted = "T" +input.replaceAll("\\s+(hours|hour)\\s+", "H");
                adjusted = adjusted.replaceAll("\\s+(mins|min)", "M");

            }
            else if(input.contains("mins") || input.contains("min")){
                adjusted = "T" + input.replaceAll("\\s+(mins|min)", "M");


            }
            adjusted = adjusted.strip();

            //So adjusted looks like 1H30M20S

            System.out.println(adjusted);

            Duration d = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                d = Duration.parse("P" + adjusted);
            }

            double result = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                result = d.toMinutes();
            }

            reformatted[i+1] = result;
        }

        return reformatted;
    }


}

