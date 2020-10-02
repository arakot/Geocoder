package com.example.geocoder;

import android.app.Activity;
import android.util.Log;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import org.json.JSONException;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class YandexGeoCoder extends Activity {

    final String apikey = "938d0a04-3bf2-417a-a18e-95ccf76f59b1";
    private MapView mapView;
    private double lat;
    private double lot;

    public YandexGeoCoder(MapView mapView)  {
        this.mapView = mapView;
    }


    private void setcoords (double latit, double longtit){

        this.lat = latit;
        this.lot = longtit;
    }




    public void GET(String city) throws IOException, JSONException {
        /* Парсинг */
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<RespYandex> yandexJsonAdapter = moshi.adapter(RespYandex.class);

        Request request = new Request.Builder()
                .url("https://geocode-maps.yandex.ru/1.x/?apikey="+apikey+"&format=json&geocode="+city +"&results=1")

                .build();
        /* передача запроса */
        new OkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("GetError", e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (!response.isSuccessful()) {
                            throw new IOException("Error" + response);
                        } else {
                            RespYandex respYandex = yandexJsonAdapter.fromJson(response.body().source());

                            String[] coords = respYandex.response.GeoObjectCollection.featureMember.get(0).GeoObject.Point.pos.split(" ");


                            double latit = Double.parseDouble(coords[1]);
                            double longtit = Double.parseDouble(coords[0]);

                            //в глобал переменные класса

                            setcoords(latit, longtit);
                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(runanim);
                                }
                            });
                            t.start();
                        }
                    }
                });
            }
    Runnable runanim = new Runnable() {
        public void run() {
            mapView.getMap().move(new CameraPosition(new Point(lat, lot), 12.0f, 0.0f, 0.0f));
            new Animation(Animation.Type.SMOOTH, 5);

        }
    };
}
