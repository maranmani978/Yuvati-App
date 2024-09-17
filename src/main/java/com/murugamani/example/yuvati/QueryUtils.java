package com.murugamani.example.yuvati;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.murugamani.example.yuvati.data.Incidence;
import com.murugamani.example.yuvati.data.StateCrime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Murugamani on 3/18/2018.
 */

public class QueryUtils {

    private static final String API_KEY = "api-key";
    private static final String BASE_API_KEY = "579b464db66ec23bdd000001bb376116452f47485863ec8d449097f0";

    public static String buildUrl(String urls){
        Uri.Builder uriBuilder = Uri.parse(urls).buildUpon();
        uriBuilder.appendQueryParameter(API_KEY,BASE_API_KEY);
        URL url = null;

        try{
            url = new URL(uriBuilder.toString());
        }catch (MalformedURLException e){
            Log.e("Mani","Error"+e.getMessage());
        }

        Log.e("Mani",url.toString());

        String json = null;
        try {
            json = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private static String getResponseFromHttpUrl(URL url)throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream inputStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }


    public static List<StateCrime> extract1999Json(String json,int option){
        if (TextUtils.isEmpty(json)){
            return null;
        }

        List<StateCrime> stateCrimes = new ArrayList<>();

        try{
            JSONObject base = new JSONObject(json);

            JSONArray array = base.getJSONArray("records");

            String no,name,incidence,contribution,population,crimes,rank;

            int size=array.length(),i=0;
            if (option==1) {
                i=0;
                size = array.length();
            }else if (option == 2) {
                i=0;
                size = 26;
            }else if (option == 3) {
                i=26;
                size = 34;
            }else if (option == 4) {
                i=35;
                size=59;
            }else if(option == 5){
                i=34;
                size=35;
            }

            for (; i < size; i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                no = jsonObject.getString("si_no_");
                name = jsonObject.getString("states_ut_city");
                incidence = jsonObject.getString("incidence");
                contribution = jsonObject.getString("percentage_of_contribution_to_all_india_total");
                population = jsonObject.getString("estimated_mid_year_population_in_lakhs_");
                crimes = jsonObject.getString("rate_of_total_cognizable_crimes");
                rank = jsonObject.getString("rank_");

                StateCrime stateCrime = new StateCrime(no, name, incidence, contribution, population, crimes, rank);
                stateCrimes.add(stateCrime);
            }

        }catch (JSONException e){
            Log.e("json",e.getMessage());
        }

        return stateCrimes;
    }

    public static List<StateCrime> extract2000Json(String json,int option){
        if (TextUtils.isEmpty(json)){
            return null;
        }

        List<StateCrime> stateCrimes = new ArrayList<>();

        try{
            JSONObject base = new JSONObject(json);

            JSONArray array = base.getJSONArray("records");

            String no,name,incidence,contribution,population,crimes,rank;

            int size=array.length(),i=0;
            if (option==1) {
                i = 0;
                size = array.length();
            }else if (option == 2) {
                i = 0;
                size = 26;
            }else if (option == 3) {
                i = 26;
                size = 34;
            }else if (option == 4) {
                i = 35;
                size = 59;
            }else if (option == 5){
                i = 34;
                size = 35;
            }

            for (; i < size; i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                no = jsonObject.getString("si_no_");
                name = jsonObject.getString("state_ut_city");
                incidence = jsonObject.getString("incidence");
                contribution = jsonObject.getString("percentage_of_contribution_to_all___india_total");
                population = jsonObject.getString("estimated_mid_year_population_in_lakhs_");
                crimes = jsonObject.getString("rate_of_total_cognizable_crimes");
                rank = jsonObject.getString("rank_");

                StateCrime stateCrime = new StateCrime(no, name, incidence, contribution, population, crimes, rank);
                stateCrimes.add(stateCrime);
            }

        }catch (JSONException e){
            Log.e("Mani",e.getMessage());
        }

        return stateCrimes;
    }


    public static List<Incidence> extractListIncidence2000(String json,int option){

        if (TextUtils.isEmpty(json)){
            return null;
        }

        List<Incidence> incidences = new ArrayList<>();

        try{

            JSONObject root = new JSONObject(json);

            JSONArray array = root.getJSONArray("records");

            int size=array.length(),i=0;
            if (option==1) {
                i = 0;
                size = array.length();
            }else if (option == 2) {
                i = 0;
                size = 26;
            }else if (option == 3) {
                i = 26;
                size = 34;
            }else if (option == 4) {
                i = 35;
                size = 59;
            }else if (option == 5){
                i = 34;
                size = 35;
            }

            for (;i < size;i++){

                JSONObject current = array.getJSONObject(i);

                String name = current.getString("state_ut_city");
                String total = current.getString("total___incidence");

                Incidence incidence = new Incidence(name,null,null,null,null,null,null,null
                        ,null,null,null,null,null,null
                        ,null,null,null,null,null,null
                        ,null,null,null,null,null,null
                        ,null,null,null,null,null,null
                        ,total,null,null );

                incidences.add(incidence);
            }

        }catch (JSONException e){
            Log.e("inc 2000 json : ",e.getMessage());
        }

        return incidences;
    }

}
