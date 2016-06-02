package jako.jocantaro.android.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jako.jocantaro.android.tipcalc.R;
import jako.jocantaro.android.tipcalc.TipCalcApp;
import jako.jocantaro.android.tipcalc.fragments.TipHistoryListFragment;
import jako.jocantaro.android.tipcalc.fragments.TipHistoryListFragmentListener;
import jako.jocantaro.android.tipcalc.models.TipRecord;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);

        fragmentListener = (TipHistoryListFragmentListener) fragment;

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {

            about();


        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit() {


        String strInputTotal = inputBill.getText().toString().trim();

        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();

            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());


            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());

            fragmentListener.addToList(tipRecord);

            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);

        }
        hideKeyboard();
    }

    @OnClick(R.id.btnIncrease)
    public void handleBtnIncrease() {

        int newTipPercentage = getTipPercentage() + 1;
        inputPercentage.setText(String.valueOf(newTipPercentage));

    }


    @OnClick(R.id.btnDecrease)
    public void handleBtnDecrease() {
        int newTipPercentage = getTipPercentage() - 1;
        inputPercentage.setText(String.valueOf(newTipPercentage));
    }


    public void handleTipChange(int change) {

        int currentTipPercentage = getTipPercentage();
        int newTipPercentage = currentTipPercentage + change;

        if (newTipPercentage > 0) {
            inputPercentage.setText(newTipPercentage);
        }
    }


    public int getTipPercentage() {

        int currentTipPercentage = DEFAULT_TIP_PERCENTAGE;
        String newTipPercentage = inputPercentage.getText().toString().trim();

        if (!newTipPercentage.isEmpty()) {
            currentTipPercentage = Integer.parseInt(newTipPercentage);
        } else {
            inputPercentage.setText(String.valueOf(currentTipPercentage));
        }

        return currentTipPercentage;
    }


    private void about() {

        TipCalcApp app = (TipCalcApp) getApplication();
        String url = app.getAboutUrl();


        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));

        startActivity(i);

    }

    private void hideKeyboard() {


        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }

    }

}
