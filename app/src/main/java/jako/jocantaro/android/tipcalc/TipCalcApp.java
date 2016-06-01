package jako.jocantaro.android.tipcalc;

import android.app.Application;

/**
 * Created by jocantaro on 1/06/16.
 */
public class TipCalcApp extends Application {

    private static final String ABOUT_URL ="http://www.google.es";


    public  String getAboutUrl(){
        return ABOUT_URL;
    }




}
