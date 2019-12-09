package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;*/


import androidx.appcompat.app.AppCompatActivity;

//import com.google.api.services.translate.v3beta1.model.TranslateTextRequest;
//import com.google.api.services.translate.v3beta1.model.TranslateTextResponse;
//import com.google.api.services.translate.v3beta1.model.Translation;

/*import org.apache.commons.lang3.ObjectUtils;*/

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Options extends AppCompatActivity {
    final private String c = "Characters";
    //final private String p = "Pinyin";
    final private String e = "English";
    //private static String[] pinyinArray;
    private static String[] charactersArray;
    final private String projectId =  "final-261121";
    final private String location = "global";
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/x-www-form-urlencoded");
    private final String url = "https://yodish.p.rapidapi.com/yoda.json?text=Master%20Obiwan%20has%20lost%20a%20planet.";
    private String theTrans = "nothing";
    private int theIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        addListenerOnButton();

        //Start.setOnClickListener(unused -> startActivity(new Intent(this, Flashcards.class)));
    }
   /*public String translate(String[] englishArray) {
       List englishList = Arrays.asList(englishArray);
       TranslateTextRequest a = new TranslateTextRequest();


       TranslateTextRequest request = a.setContents(englishList);
       List<String> b = a.getContents();


       request.setSourceLanguageCode("en-US");
       request.setTargetLanguageCode("zh-Hans");

       String sourceLangCode = request.getSourceLanguageCode();
       String targetLangCode = request.getTargetLanguageCode();

   }*/
    // Translating Text
   /*public static List<Translation> translateText(
           String projectId, String location, String targetLanguage, String text) throws IOException {

       // Initialize client that will be used to send requests. This client only needs to be created
       // once, and can be reused for multiple requests. After completing all of your requests, call
       // the "close" method on the client to safely clean up any remaining background resources.
       try (TranslationServiceClient client = TranslationServiceClient.create()) {
           LocationName parent = LocationName.of(projectId, location);
           TranslateTextRequest request =
                   TranslateTextRequest.newBuilder()
                           .setParent(parent.toString())
                           .setTargetLanguageCode(targetLanguage)
                           .addContents(text)
                           .build();

           TranslateTextResponse response = client.translateText(request);

           return response.getTranslationsList();

           // Display the translation for each input text provided
           /*for (Translation translation : response.getTranslationsList()) {
               System.out.printf("Translated text: %s\n", translation.getTranslatedText());
           }
       }
   }*/

   /*public String callInternet(String toPost) {
       if (toPost == null) {
           theTrans = "toPost is null";
           return theTrans;
       }
       OkHttpClient client = new OkHttpClient();


       if (toPost == null) {
           Log.e("null", "toPost is null");
       } else {
           Log.i("works", "toPost isn't null");
       }
       //RequestBody body = RequestBody.create(toPost, MEDIA_TYPE_MARKDOWN);


       //MediaType.parse(toPost)
       Request request = new Request.Builder()
               .url(getURL(toPost))
               .post(null)
               .addHeader("x-rapidapi-host", "yodish.p.rapidapi.com")
               .addHeader("x-rapidapi-key", "16cc401140mshd664bc186fff096p1861ebjsn3d18e2fbdf33")
               .addHeader("content-type", "application/x-www-form-urlencoded")
               .build();

       try {
           Response response = client.newCall(request).execute();
           ResponseBody myResponse = response.body();
           Gson gson = new Gson();
           String jsonData = myResponse.string();
           try {
               JSONObject Jobject = new JSONObject(jsonData);
               JSONObject jarray = Jobject.getJSONObject("contents");
               String translated = jarray.getString("translated");
               System.out.println(translated);
               return translated;
               //JsonArray allGames = result.get("games").getAsJsonArray();
           } catch (JSONException j) {
               Log.e("json", "json wack");
               j.printStackTrace();
           }

       } catch (IOException e) {

           e.printStackTrace();
       }
       String failed = "didn't work?";
       return failed;
   }*/
   /*public void callIntwCallback(String toPost) {
       OkHttpClient client = new OkHttpClient();

       Call post(String url, String MEDIA_TYPE_MARKDOWN, Callback callback) {
           RequestBody body = RequestBody.create(JSON, json);
           Request request = new Request.Builder()
                   .url(url)
                   .post(body)
                   .build();
           Call call = client.newCall(request);
           call.enqueue(callback);
           return call;
       }
   }*/

    public String getURL(String str) {
        String[] splitIt = str.split(" ");
        String result = "https://yodish.p.rapidapi.com/yoda.json?text=";
        for (int i = 0; i < splitIt.length; i++) {
            if (i == splitIt.length - 1) {
                result = result + splitIt[i];
                return result;
            }
            result = result + splitIt[i] + "%20";
        }
        return result;
    }


    public void run(String toPost) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(toPost, MEDIA_TYPE_MARKDOWN);

        Request request = new Request.Builder()
                .url(getURL(toPost))
                .post(body)
                .addHeader("x-rapidapi-host", "yodish.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "16cc401140mshd664bc186fff096p1861ebjsn3d18e2fbdf33")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        System.out.println("BEFOREonFAILEure");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("onFAILEure");
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                Options.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ResponseBody myResponse = response.body();
                        //Gson gson = new Gson();
                        String jsonData = myResponse;
                        //System.out.println("output" + jsonData);
                        try {
                            JSONObject Jobject = new JSONObject(jsonData);
                            JSONObject jarray = Jobject.getJSONObject("contents");
                            String translated = jarray.getString("translated");
                            System.out.println(translated);
                            charactersArray[theIndex] = translated;
                            System.out.println("this is characters array" + charactersArray[theIndex]);
                            theIndex++;
                            //JsonArray allGames = result.get("games").getAsJsonArray();
                        } catch (JSONException j) {
                            Log.e("json", "json wack");
                            j.printStackTrace();
                        }
                    }
                });

            }
        });
    }
    public String[] yodaLang(String[] array) {
        //String[] splitIt = array.split(" ");
        String[] result = new String[5];
        for (int i = 0; i < array.length; i++) {
            String[] splitIt = array[i].split(" ");
            if (splitIt.length == 1) {
                result[i] = splitIt[0] + " hmmmmmmm";
                continue;
            }
            String add = "";
            String temp = splitIt[0];
            splitIt[0] = splitIt[splitIt.length -1];
            splitIt[splitIt.length -1] = temp;
            for (int j = 0; j < splitIt.length; j++) {
                add = add + " " + splitIt[j];
            }
            result[i] = add;

        }
        return result;
    }




    public void addListenerOnButton(){

        RadioGroup side1Options = findViewById(R.id.side1Options);
        RadioGroup side2Options = findViewById(R.id.side2Options);
        Button Start = findViewById(R.id.Start);
        Intent received = getIntent();
        String[] englishArray = received.getStringArrayExtra("englishArray");
        Intent intent = new Intent(this, Flashcards.class);

        Start.setOnClickListener((View v) -> {

            //@Override
            //public void onClick(View v) {
            // get selected radio button from radioGroup

            int selectedId = side1Options.getCheckedRadioButtonId(); // could be these
            int selectedId2 = side2Options.getCheckedRadioButtonId();

            if (selectedId == R.id.CharactersSide1) {
                //method translate eng->characters
                int i = 0;
                charactersArray = new String[]{"", "", "", "", ""};
                for (String text : englishArray) {
                    try {
                        run(text);
                        //charactersArray[i] = theTrans;

                    } catch (NullPointerException e) {
                        Log.e("null", "yes");
                    } catch (IOException e) {
                        Log.e("oi", "io");
                    }


                    i++;
                }
                charactersArray = yodaLang(englishArray);
                intent.putExtra("charactersArray", charactersArray);

                //set side1 intent to Characters

                intent.putExtra("side1", c);
            }

            if (selectedId == R.id.Englishside1) {
                //set side1 intent to English
                intent.putExtra("side1", e);
                intent.putExtra("englishArray", englishArray);
            }

            if (selectedId2 == R.id.CharactersSide2) {
                ///method translate eng->characters
                int i = 0;
                charactersArray = new String[]{"", "", "", "", ""};
                for (String text : englishArray) {
                    try {
                        run(text);

                        //charactersArray[i] = theTrans;

                    } catch (NullPointerException e) {
                        Log.e("null", "yes");
                    } catch (IOException e) {
                        Log.e("io", "io");
                    }

                    i++;
                }
                charactersArray = yodaLang(englishArray);
                intent.putExtra("charactersArray", charactersArray);

                //set side1 intent to Characters

                intent.putExtra("side2", c);
            }

            if (selectedId2 == R.id.EnglishSide2) {
                //set side2 intent to English
                intent.putExtra("side2", e);
                intent.putExtra("englishArray", englishArray);
            }


            //go to flashcards class
            startActivity(intent);

            //}

        });

    }
}









/*package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;


import androidx.appcompat.app.AppCompatActivity;

//import com.google.api.services.translate.v3beta1.model.TranslateTextRequest;
//import com.google.api.services.translate.v3beta1.model.TranslateTextResponse;
//import com.google.api.services.translate.v3beta1.model.Translation;

/*import org.apache.commons.lang3.ObjectUtils;*/

/*import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;*/
/*
public class Options extends AppCompatActivity {
    final private String c = "Characters";
    //final private String p = "Pinyin";
    final private String e = "English";
    //private static String[] pinyinArray;
    public String[] charactersArray = new String[]{"", "", "", "", ""};
    final private String projectId =  "final-261121";
    final private String location = "global";
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/x-www-form-urlencoded");
    private final String url = "https://yodish.p.rapidapi.com/yoda.json?text=Master%20Obiwan%20has%20lost%20a%20planet.";
    private String theTrans = "nothing";
    private int theIndex = 0;
    private String[] englishArray;
    //private Intent intent = new Intent(this, Flashcards.class);*/


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        addListenerOnButton();
        //Intent intent = new Intent(this, Flashcards.class);

        //Start.setOnClickListener(unused -> startActivity(new Intent(this, Flashcards.class)));
    }

    public String getURL(String str) {
        String[] splitIt = str.split(" ");
        String result = "https://yodish.p.rapidapi.com/yoda.json?text=";
        for (int i = 0; i < splitIt.length; i++) {
            if (i == splitIt.length - 1) {
                result = result + splitIt[i];
                return result;
            }
            result = result + splitIt[i] + "%20";
        }
        return result;
    }

    //make run pass a string array, with for loop inside run

    public void run(String[] toPost) throws IOException {

        OkHttpClient client = new OkHttpClient();

        int index = 0;

        RequestBody body = RequestBody.create(toPost[index], MEDIA_TYPE_MARKDOWN);

        Request request = new Request.Builder()
                .url(getURL(toPost[index]))
                .post(body)
                .addHeader("x-rapidapi-host", "yodish.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "16cc401140mshd664bc186fff096p1861ebjsn3d18e2fbdf33")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("onFAILEure");
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Options.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ResponseBody myResponse = response.body();
                        //Gson gson = new Gson();
                        String jsonData = myResponse;
                        //System.out.println("output" + jsonData);
                        try {

                            JSONObject jObject = new JSONObject(jsonData);
                            JSONObject jarray = jObject.getJSONObject("contents");
                            theTrans = jarray.getString("translated");
                            //String theName = englishArray[theIndex];
                            //ntent.putExtra(theName, theTrans);
                            //System.out.println("in run " + theTrans);
                            charactersArray[theIndex] = theTrans;
                            System.out.println("this is characters array" + charactersArray[theIndex]);
                            theIndex++;
                            System.out.println("please " + charactersArray[0]);

                            //intent.putExtra("charactersArray", charactersArray);


                            //System.out.println(translated);
                            //charactersArray[theIndex] = translated;
                            //System.out.println("this is characters array" + charactersArray[theIndex]);
                            //theIndex++;
                            //JsonArray allGames = result.get("games").getAsJsonArray();
                        } catch (JSONException j) {
                            Log.e("json", "json wack");
                            j.printStackTrace();
                        }
                    }
                });

            }
        });
    }



    public void addListenerOnButton(){

        RadioGroup side1Options = findViewById(R.id.side1Options);
        Intent intent = new Intent(this, Flashcards.class);
        RadioGroup side2Options = findViewById(R.id.side2Options);
        Button Start = findViewById(R.id.Start);
        Intent received = getIntent();
        englishArray = received.getStringArrayExtra("englishArray");
        String[] charactersArray = new String[englishArray.length];
        if (charactersArray.length == 0) {
            return;
        }
        System.out.println("array " + charactersArray[theIndex]);
        Start.setOnClickListener((View v) -> {

            //@Override
            //public void onClick(View v) {
            // get selected radio button from radioGroup

            int selectedId = side1Options.getCheckedRadioButtonId(); // could be these
            int selectedId2 = side2Options.getCheckedRadioButtonId();

            if (selectedId == R.id.CharactersSide1) {
                //method translate eng->characters
                int i = 0;
                for (String text : englishArray) {
                    try {
                        run(text);
                        charactersArray[i] = theTrans;
                        //System.out.println("transtrans " + theTrans);
                        //System.out.println("this is characters array" + charactersArray[i]);
                        //charactersArray[i] = theTrans;
                        //theIndex++;

                    } catch (NullPointerException e) {
                        Log.e("null", "yes");
                    } catch (IOException e) {
                        Log.e("oi", "io");
                    }
                    i++;
                }
                intent.putExtra("charactersArray", charactersArray);


                //set side1 intent to Characters
                intent.putExtra("side1", c);
            }

            if (selectedId == R.id.Englishside1) {
                //set side1 intent to English
                intent.putExtra("side1", e);
                intent.putExtra("englishArray", englishArray);
            }

            if (selectedId2 == R.id.CharactersSide2) {
                ///method translate eng->characters
                int i = 0;
                //charactersArray = new String[]{"", "", "", "", ""};
                for (String text : englishArray) {
                    try {
                        run(text);
                        //String trans = callInternet(text);
                        //charactersArray[i] = trans;
                        charactersArray[i] = theTrans;
                        //System.out.println("this is characters array" + charactersArray[i]);
                        //theIndex++;
                        //charactersArray[i] = theTrans;


                    } catch (NullPointerException e) {
                        Log.e("null", "yes");
                    } catch (IOException e) {
                        Log.e("io", "io");
                    }

                    i++;
                }
                //System.out.println("before intent " + charactersArray[0]);
                intent.putExtra("charactersArray", charactersArray);

                //set side1 intent to Characters
                intent.putExtra("side2", c);
            }

            if (selectedId2 == R.id.EnglishSide2) {
                //set side2 intent to English
                intent.putExtra("side2", e);
                intent.putExtra("englishArray", englishArray);
            }

            System.out.println("before startactivty " + charactersArray[0]);
            //go to flashcards class
            startActivity(intent);

            //}

        });

    }
}*/


    /*public void run(String toPost) throws IOException {

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(toPost, MEDIA_TYPE_MARKDOWN);

        Request request = new Request.Builder()
                .url(getURL(toPost))
                .post(body)
                .addHeader("x-rapidapi-host", "yodish.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "16cc401140mshd664bc186fff096p1861ebjsn3d18e2fbdf33")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        System.out.println("BEFOREonFAILEure");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("onFAILEure");
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                Options.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ResponseBody myResponse = response.body();
                        //Gson gson = new Gson();
                        String jsonData = myResponse;
                        //System.out.println("output" + jsonData);
                        try {
                            JSONObject jObject = new JSONObject(jsonData);
                            JSONObject jarray = jObject.getJSONObject("contents");
                            theTrans = jarray.getString("translated");
                            //String theName = englishArray[theIndex];
                            //ntent.putExtra(theName, theTrans);
                            System.out.println("in run " + theTrans);
                            charactersArray[theIndex] = theTrans;
                            System.out.println("this is characters array" + charactersArray[theIndex]);
                            theIndex++;
                            System.out.println("please " + charactersArray[0]);

                            intent.putExtra("charactersArray", charactersArray);


                            //System.out.println(translated);
                            //charactersArray[theIndex] = translated;
                            //System.out.println("this is characters array" + charactersArray[theIndex]);
                            //theIndex++;
                            //JsonArray allGames = result.get("games").getAsJsonArray();
                        } catch (JSONException j) {
                            Log.e("json", "json wack");
                            j.printStackTrace();
                        }
                    }
                });

            }
        });
    }*/

    /*public String translate(String[] englishArray) {
        List englishList = Arrays.asList(englishArray);
        TranslateTextRequest a = new TranslateTextRequest();


        TranslateTextRequest request = a.setContents(englishList);
        List<String> b = a.getContents();


        request.setSourceLanguageCode("en-US");
        request.setTargetLanguageCode("zh-Hans");

        String sourceLangCode = request.getSourceLanguageCode();
        String targetLangCode = request.getTargetLanguageCode();

    }*/
// Translating Text
    /*public static List<Translation> translateText(
            String projectId, String location, String targetLanguage, String text) throws IOException {

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            LocationName parent = LocationName.of(projectId, location);
            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setTargetLanguageCode(targetLanguage)
                            .addContents(text)
                            .build();

            TranslateTextResponse response = client.translateText(request);

            return response.getTranslationsList();

            // Display the translation for each input text provided
            /*for (Translation translation : response.getTranslationsList()) {
                System.out.printf("Translated text: %s\n", translation.getTranslatedText());
            }
        }
    }*/

    /*public String callInternet(String toPost) {
        if (toPost == null) {
            theTrans = "toPost is null";
        }
        OkHttpClient client = new OkHttpClient();


        if (toPost == null) {
            Log.e("null", "toPost is null");
        } else {
            Log.i("works", "toPost isn't null");
        }
        RequestBody body = RequestBody.create(toPost, MEDIA_TYPE_MARKDOWN);


        //MediaType.parse(toPost)
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("x-rapidapi-host", "yodish.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "aaf97f5dd8msh99c18088dd918e7p1f88cdjsnac081b3886d1")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody myResponse = response.body();
            Gson gson = new Gson();
            String jsonData = myResponse.string();
            try {
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray jarray = Jobject.getJSONArray("contents");
                String translated = jarray.getString(0);
                return translated;
                //JsonArray allGames = result.get("games").getAsJsonArray();
            } catch (JSONException j) {
                Log.e("json", "json wack");
                j.printStackTrace();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        String failed = "didn't work?";
        return failed;
    }
    /*public void callIntwCallback(String toPost) {
        OkHttpClient client = new OkHttpClient();

        Call post(String url, String MEDIA_TYPE_MARKDOWN, Callback callback) {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
            return call;
        }
    }*/

        /*public String callInternet(String toPost) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(toPost, MEDIA_TYPE_MARKDOWN);


        Request request = new Request.Builder()
                .url(getURL(toPost))
                .post(body)
                .addHeader("x-rapidapi-host", "yodish.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "16cc401140mshd664bc186fff096p1861ebjsn3d18e2fbdf33")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody myResponse = response.body();
            Gson gson = new Gson();
            String jsonData = myResponse.string();
            try {
                JSONObject jObject = new JSONObject(jsonData);
                JSONObject jarray = jObject.getJSONObject("contents");
                String translated = jarray.getString("translated");

                return translated;

            } catch (JSONException j) {
                Log.e("json", "json wack");
                j.printStackTrace();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return new String();
    }*/
