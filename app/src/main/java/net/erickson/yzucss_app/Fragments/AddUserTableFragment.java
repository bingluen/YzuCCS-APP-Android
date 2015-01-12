package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.erickson.yzucss_app.DataObjects.UserTableObject;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

/**
 * Created by Erickson on 2015/1/6.
 */
public class AddUserTableFragment extends Fragment implements View.OnClickListener {

    private EditText tableNameColum;
    private EditText tableCommentColum;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_add_course_table, container, false);

        tableNameColum = (EditText) rootView.findViewById(R.id.addCourseTableRow_tableName);
        tableCommentColum = (EditText) rootView.findViewById(R.id.addCourseTableRow_tableComment);
        submitButton = (Button) rootView.findViewById(R.id.addCourseTable_submit);

        submitButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addCourseTable_submit:
                UserTableObject userTableObject = new UserTableObject();
                userTableObject.setName(tableNameColum.getText());
                userTableObject.setComment(tableCommentColum.getText());
                userTableObject.setYear("1032");

                AccessUserTableDatabase UTDHelper = new AccessUserTableDatabase(getActivity());

                UTDHelper.CreateUserTable(userTableObject);
                break;
        }
    }
}