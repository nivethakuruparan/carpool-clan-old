package com.example.carpoolclan;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import com.google.android.gms.maps.model.LatLng;
import static com.example.carpoolclan.BuildConfig.MAPS_API_KEY;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;


public class DirectionsAPI {

    private static final String API_KEY= MAPS_API_KEY;
    static OkHttpClient client = new OkHttpClient();

    // decodes polyline returning list of lat longs along line
    public static List<LatLng> decode(final String encodedPath) {
        int len = encodedPath.length();

        // For speed we preallocate to an upper bound on the final length, then
        // truncate the array before returning.
        final List<LatLng> path = new ArrayList<LatLng>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new LatLng(lat * 1e-5, lng * 1e-5));
        }

        return path;
    }

    public static String reformatSourceAndDestinationsFromString (String[] latLong){
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

                    //reformatted += bar;
                }
            }
        }
        else{
            System.out.println("Error in data to be reformatted, missing lat long pairs");
        }


        return reformatted;
    }

    public static Route parse(JSONObject jObject){



        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        Route r = null;
        String o = null;
        try {

            jRoutes = jObject.getJSONArray("routes");

            /** Traversing all routes */
            for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");

                o = ( (JSONObject)jRoutes.get(i)).getJSONObject("overview_polyline").optString("points");


                /** Traversing all legs */
                for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");
                    System.out.println("this here 123");

                    String dist_total = ((JSONObject) jLegs.get(j)).getJSONObject("distance").optString("value");
                    String duration_total = ((JSONObject) jLegs.get(j)).getJSONObject("duration").optString("value");

                    String start_lat_total = ((JSONObject) jLegs.get(j)).getJSONObject("start_location").optString("lat");
                    String start_lon_total = ((JSONObject) jLegs.get(j)).getJSONObject("start_location").optString("lng");

                    String end_lat_total = ((JSONObject) jLegs.get(j)).getJSONObject("end_location").optString("lat");
                    String end_lon_total = ((JSONObject) jLegs.get(j)).getJSONObject("end_location").optString("lng");


                    r = new Route(Double.parseDouble(start_lat_total), Double.parseDouble(start_lon_total),
                            Double.parseDouble(end_lat_total), Double.parseDouble(end_lon_total),
                            Integer.parseInt(dist_total), Integer.parseInt(duration_total));

                    /** Traversing all steps */
                    for(int k=0;k<jSteps.length();k++){

                        String html_instructions = ((JSONObject) jSteps.get(k)).getString("html_instructions");
                        //String travel_mode = ((JSONObject) jSteps.get(k)).getString("travel_mode");
                        //String maneuver = ((JSONObject) jSteps.get(k)).optString("maneuver");

                        String distance_text = ((JSONObject) jSteps.get(k)).getJSONObject("distance").getString("text");
                        String distance_value = ((JSONObject) jSteps.get(k)).getJSONObject("distance").optString("value");

                        String duration_text = ((JSONObject) jSteps.get(k)).getJSONObject("duration").getString("text");
                        String duration_value = ((JSONObject) jSteps.get(k)).getJSONObject("duration").optString("value");

                        String start_lat = ((JSONObject) jSteps.get(k)).getJSONObject("start_location").optString("lat");
                        String start_lon = ((JSONObject) jSteps.get(k)).getJSONObject("start_location").optString("lng");

                        String end_lat = ((JSONObject) jSteps.get(k)).getJSONObject("end_location").optString("lat");
                        String end_lon = ((JSONObject) jSteps.get(k)).getJSONObject("end_location").optString("lng");


                        String polyline = "";
                        polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");

                        System.out.println(html_instructions);
                        System.out.println(distance_text);
                        System.out.println(distance_value);
                        System.out.println(duration_text);
                        System.out.println(duration_value);
                        System.out.println(start_lat +", " + start_lon);
                        System.out.println(end_lat +", "+ end_lon);
                        System.out.println(polyline);
                        System.out.println("----------------------------------");

                        r.steps.add(new Step(Double.parseDouble(start_lat), Double.parseDouble(start_lon),
                                Double.parseDouble(end_lat), Double.parseDouble(end_lon),
                                Integer.parseInt(distance_value), Integer.parseInt(duration_value),
                                html_instructions, polyline));


                        //List<LatLng> list = decodePoly(polyline);


                        /** Traversing all points *h/
                         for(int l=0;l<list.size();l++){
                         HashMap<String, String> hm = new HashMap<String, String>();
                         hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                         hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                         path.add(hm);
                         }
                         */
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        r.overviewPolyline = o;

        return r;
    }

    public static String calculateFromAddress(String source ,String destination) throws IOException {
        String url="https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="+source+"&destinations="+destination+"&key="+ API_KEY;
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();


    }

    public static String calculate(String origLatng[] ,String destlatlng[]) throws IOException {

        String origin = reformatSourceAndDestinationsFromString(origLatng);
        String destination = reformatSourceAndDestinationsFromString(destlatlng);

        String url= "https://maps.googleapis.com/maps/api/directions/json?units=metric&origin="+origin+"&destination="+destination +"&key="+ API_KEY;
        System.out.println(url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        /* how to parse into route object

        System.out.println ("THIS IS PARSER");

        JSONObject jsonObject = new JSONObject(response.body().string());
        parse(jsonObject); */

        return response.body().string();


    }

    public static Route getRoute(double latA, double lngA, double latB, double lngB){

        String[] A = new String[]{String.valueOf(latA), String.valueOf(lngA)};
        String[] B = new String[]{String.valueOf(latB), String.valueOf(lngB)};
        Route r = null;

        try {
            String response = calculate(A,B);
            JSONObject jsonObject = new JSONObject(response);
            r = parse(jsonObject);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return r;
    }

    // time is time elapsed while travelling from A to B
    public static double[] approximateAlongRoute(Route r, double timeInSeconds) throws IOException{

        double[] location = new double[2];


        if(timeInSeconds <0.5 && timeInSeconds >= 0){
            return new double[]{r.start_location_lat, r.start_location_lng};
        }

        // ensure time is less than time required for route
        if(timeInSeconds < 0 || timeInSeconds > r.duration){
            System.out.println("ERROR TIME IN SECONDS IS NOT POSSIBLE");
            System.out.println("time provided: "+ timeInSeconds);
            System.out.println("time required for route: " + r.duration);

            if(timeInSeconds >0 && (timeInSeconds - r.duration) < 3){
                System.out.println("Location was less than 3 seconds away from end, should be at end");
                return new double[]{r.end_location_lat, r.end_location_lng};
            }

            return null;

        }

        // go through each step of route
        int time =0;

        for( Step s: r.steps){

            if( timeInSeconds > time && timeInSeconds < (time+s.duration)){

                System.out.println("Car is between " + s.start_location_lat +", " + s.start_location_lng + " and " + s.end_location_lat + ", " + s.end_location_lng);

                // found step where car left off
                double timeOnStep = timeInSeconds - time;

                // decode this polyline
                List<LatLng>  loc = decode(s.polyline);
                int numloc = loc.size();
                int wholeIntervals = numloc /10;
                int remainder = numloc % 10;

                System.out.println("polyline has " + numloc + " points");
                System.out.println("whole intervals: " + wholeIntervals + " remainder: " + remainder);
                System.out.println("We are at time: " + timeOnStep);

                // test 10 locations at a time
                String[] dest = new String[20];
                String[] origin = new String[]{String.valueOf(s.start_location_lat), String.valueOf(s.start_location_lng)};

                DistanceTime API = new DistanceTime();

                String originFormatted = API.reformatSourceAndDestinationsFromString(origin);

                double[] timesRequired = new double[numloc];

                int latlngIndex =0;
                int timeIndex =0;
                int comparelast =0;
                for (int i = 0; i < wholeIntervals; i++) {

                    for (int j = 0; j < 10; j++) {

                        dest[(j*2)] = String.valueOf(loc.get(latlngIndex).latitude);
                        dest[(j*2)+1] = String.valueOf(loc.get(latlngIndex).longitude);
                        latlngIndex++;
                    }

                    // dest populated
                    String destinationFormatted = API.reformatSourceAndDestinationsFromString(dest);

                    // request
                    String response = API.calculate(originFormatted, destinationFormatted);

                    // populate times to get there
                    for (int j = 0; j < 10; j++) {
                        // ans returned in min, convert to seconds
                        timesRequired[timeIndex] = (API.parseJsonIndexToDouble(response, 0, j)[1])*60;
                        timeIndex++;
                    }

                    if (i > 0){
                        comparelast =1;
                    }

                    System.out.println("This is timesrequired: " + Arrays.toString(timesRequired));

                    // check times required to see if ans was produced this time
                    for (int j = 0; j < 9  + (comparelast); j++) {
                        if(Math.abs(timeOnStep - timesRequired[i*10+ j - comparelast]) < Math.abs(timeOnStep - timesRequired[i*10 + j +1 - comparelast]) ){
                            // next ans is worse than current, let current be ans
                            System.out.println("Car found in batches of 10 while requesting");
                            System.out.println("Car is at " + loc.get(i*10+ j - comparelast).latitude +", " +loc.get(i*10+ j - comparelast).longitude);
                            //return lat long at the current point
                            return new double[]{loc.get(i*10+ j - comparelast).latitude, loc.get(i*10+ j - comparelast).longitude};

                        }
                    }
                }
                System.out.println("at rem");
                System.out.print("This is latlngIndex: " + latlngIndex);
                // check remainders
                dest = new String[remainder*2];
                for (int i = 0; i < dest.length/2; i++) {
                    dest[(i*2)] = String.valueOf(loc.get(latlngIndex).latitude);
                    dest[(i*2)+1] = String.valueOf(loc.get(latlngIndex).longitude);
                    latlngIndex++;

                }
                // dest populated
                String destinationFormatted = API.reformatSourceAndDestinationsFromString(dest);

                // request
                String response = API.calculate(originFormatted, destinationFormatted);
                System.out.println("works");

                // populate times to get there
                for (int j = 0; j < remainder; j++) {
                    // ans returned in min, convert to seconds
                    timesRequired[timeIndex] = (API.parseJsonIndexToDouble(response, 0, j)[1])*60;
                    timeIndex++;
                }

                System.out.println("\nThis is timesrequired after remainder: "+ Arrays.toString(timesRequired));
                // check remainders
                for (int i = timesRequired.length - remainder; i < timesRequired.length -1; i++) {
                    if(Math.abs(timeOnStep - timesRequired[i]) < Math.abs(timeOnStep - timesRequired[i+1])){
                        // next ans is worse than current, let current be ans
                        System.out.println("Car found in remainder batch");
                        System.out.println("Car is at " + loc.get(i).latitude +", " +loc.get(i).longitude);
                        //return lat long at the current point
                        return new double[]{loc.get(i).latitude, loc.get(i).longitude};

                    }


                }

                System.out.println("Wasnt found to be between points of polyline, returning last point");
                return new double[]{loc.get(numloc-1).latitude, loc.get(numloc-1).longitude};


            }
            time = time+s.duration;



        }


        System.out.println("Something went wrong getting a location along a polyline");
        return location; // this should never happen, it should always return while finding;
    }


}
