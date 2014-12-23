package net.erickson.yzucss_app.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.doSomething.doSearchCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2014/12/22.
 */
public class SearchCourse extends Activity {
    private EditText searchKeyWord;

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        searchKeyWord = (EditText) findViewById(R.id.section_keyword_field);
        searchKeyWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<CourseObject> result = new ArrayList<>();
                doSearchCourse searchHelper = new doSearchCourse(getApplicationContext());
                result = searchHelper.search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
