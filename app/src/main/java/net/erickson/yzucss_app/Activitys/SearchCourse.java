package net.erickson.yzucss_app.Activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2014/12/22.
 */
public class SearchCourse extends FragmentActivity {
    EditText searchKeyWord = (EditText) findViewById(R.id.section_keyword_field);
    public void onCreate (Bundle savedInstanceState)
    {
        searchKeyWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
