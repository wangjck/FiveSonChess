package chess.son.five.fivesonchess;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    private Toolbar toolbar;
    private ConstraintLayout layout;
    private Game game;
    private Button rematch;
    private TextView tx;

    /** */
    private static final String API_KEY = "0d5fe03e9e9d465dd6da5db4194dbe57";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        layout = (ConstraintLayout) findViewById(R.id.coordinatorLayout);
        game = (Game) findViewById(R.id.qwe123);
        rematch = (Button) findViewById(R.id.button);
        tx = (TextView) findViewById(R.id.textView3);
        setSupportActionBar(toolbar);

        rematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.init();
                startAPICall();

            }
        });
        startAPICall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void startAPICall() {
        String s;
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid="
                            + API_KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d("nihao", response.toString(2));
                                if (response.toString(2).contains("clear")) {
                                    layout.setBackgroundResource(R.drawable.back_sun);
                                } else if (response.toString(2).contains("snow")) {
                                     layout.setBackgroundResource(R.drawable.back_snow);
                                } else if (response.toString(2).contains("rain")) {
                                    layout.setBackgroundResource(R.drawable.back_rain);
                                } else {
                                    layout.setBackgroundResource(R.drawable.back_wind);
                                }

                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e("nihao", error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
