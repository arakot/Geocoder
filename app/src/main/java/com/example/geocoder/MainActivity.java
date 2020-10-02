package com.example.geocoder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateSource;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import org.json.JSONException;
import java.io.IOException;




public class MainActivity extends AppCompatActivity implements CameraListener {

    MapView mapView;
    Button getButton;
    EditText searchEdit;
    private final String MAPKIT_API_KEY = "ed6e88ed-ef0b-4024-9733-aba7bfff396b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mapView = (MapView)findViewById(R.id.mapview);
        mapView.getMap().addCameraListener(this);

        searchEdit = (EditText)findViewById(R.id.search_edit);
        getButton = (Button) findViewById(R.id.button);
    }

    public void onGetClick(View view) throws IOException, JSONException {
        YandexGeoCoder ya = new YandexGeoCoder(mapView);
        ya.GET(searchEdit.getText().toString());

    }

    @Override
    public void onCameraPositionChanged(@NonNull Map map, @NonNull CameraPosition cameraPosition, @NonNull CameraUpdateSource cameraUpdateSource, boolean b) {

    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

}
