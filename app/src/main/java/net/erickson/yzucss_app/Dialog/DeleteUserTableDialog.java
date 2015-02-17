package net.erickson.yzucss_app.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import net.erickson.yzucss_app.Adapter.UserTableListAdapter;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

/**
 * Created by Erickson on 2015/2/17.
 */
public class DeleteUserTableDialog extends DialogFragment {

    private UserTableListAdapter userTableListAdapter;

    public void setUserTableListAdapter(UserTableListAdapter userTableListAdapter)
    {
        this.userTableListAdapter = userTableListAdapter;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.dialog_deleteCourseTable_title);
        dialog.setMessage(getResources().getText(R.string.dialog_deleteCourseTable_message) + " " + getArguments().getCharSequence("deleteListName").toString() + "?");
        dialog.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTable(getArguments().getLong("deleteListId"));
            }
        });
        dialog.setNegativeButton(R.string.dialog_cancel, null);
        return dialog.create();
    }

    private boolean deleteTable(long id)
    {
        AccessUserTableDatabase UTDHelper = new AccessUserTableDatabase(getActivity());

        userTableListAdapter.deleteTable(id);

        UTDHelper.deleteList(id);
        return true;
    }
}
