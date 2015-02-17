package net.erickson.yzucss_app.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.erickson.yzucss_app.Adapter.UserTableListAdapter;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.DataObjects.UserTableObject;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

/**
 * Created by Erickson on 2015/2/17.
 */
public class AddUserTableDialog extends DialogFragment {
    private EditText tableNameColum;
    private EditText tableCommentColum;
    private UserTableListAdapter userTableListAdapter;

    public void setUserTableListAdapter(UserTableListAdapter userTableListAdapter)
    {
        this.userTableListAdapter = userTableListAdapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_add_course_table, null);

        tableNameColum = (EditText) view.findViewById(R.id.addCourseTableRow_tableName);
        tableCommentColum = (EditText) view.findViewById(R.id.addCourseTableRow_tableComment);

        builder.setView(view)
                .setTitle(R.string.addCourseTable_dialog_title)
                .setPositiveButton(R.string.dialog_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addTable();
                            }
                        })
                .setNegativeButton(R.string.dialog_cancel, null);


        return builder.create();
    }

    private void addTable() {
        if (tableNameColum.getText().length() > 0) {
            UserTableObject userTableObject = new UserTableObject();
            userTableObject.setName(tableNameColum.getText());
            userTableObject.setComment(tableCommentColum.getText());
            userTableObject.setYear("1032");

            userTableListAdapter.addTable(userTableObject);

            AccessUserTableDatabase UTDHelper = new AccessUserTableDatabase(getActivity());

            UTDHelper.CreateUserTable(userTableObject);


        } else {

        }
    }
}
