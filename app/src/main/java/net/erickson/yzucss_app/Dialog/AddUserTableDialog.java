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

import net.erickson.yzucss_app.DataObjects.UserTableObject;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

/**
 * Created by Erickson on 2015/2/17.
 */
public class AddUserTableDialog extends DialogFragment {
    private EditText tableNameColum;
    private EditText tableCommentColum;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
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
                                if (tableNameColum.getText().length() > 0) {
                                    UserTableObject userTableObject = new UserTableObject();
                                    userTableObject.setName(tableNameColum.getText());
                                    userTableObject.setComment(tableCommentColum.getText());
                                    userTableObject.setYear("1032");

                                    AccessUserTableDatabase UTDHelper = new AccessUserTableDatabase(getActivity());

                                    UTDHelper.CreateUserTable(userTableObject);
                                } else {

                                }
                            }
                        })
                .setNegativeButton(R.string.dialog_cancel, null);


        return builder.create();
    }
}
