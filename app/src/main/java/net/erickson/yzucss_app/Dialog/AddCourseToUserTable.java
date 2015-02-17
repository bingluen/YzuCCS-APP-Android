package net.erickson.yzucss_app.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;
import net.erickson.yzucss_app.doSomething.doAddCourseToUserTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/2/16.
 */
public class AddCourseToUserTable extends DialogFragment {

    private List<UserTableListItem> tables;
    private ArrayList mSelectedTable;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);
        //prepare courseTable
        AccessUserTableDatabase tableHelper = new AccessUserTableDatabase(getActivity());
        tables = tableHelper.getTableList();
        //UserTableListAdapter tableListAdapter = new UserTableListAdapter(getActivity().getLayoutInflater(), tables);

        mSelectedTable = new ArrayList();

        CharSequence[] tableList = new CharSequence[tableHelper.getUserTableCount()];
        for (int i = 0; i < tableHelper.getUserTableCount(); i++)
        {
            //tableList.add(tables.get(i).getName());
            tableList[i] = tables.get(i).getName();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle(R.string.addCourse_Title)
                .setPositiveButton(getResources().getText(R.string.dialog_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(mSelectedTable.size() > 0)
                                {
                                    doAddCourseToUserTable addCourseToUserTable = new doAddCourseToUserTable(getActivity());
                                    addCourseToUserTable.setListId(mSelectedTable);
                                    addCourseToUserTable.setCourse(getArguments().getLong("courseId"));
                                    addCourseToUserTable.doing();
                                }

                            }
                        })
                .setMultiChoiceItems(tableList, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            mSelectedTable.add(tables.get(which).getId());
                        } else {
                            mSelectedTable.remove(tables.get(which).getId());
                        }
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, null);
        AlertDialog dialog = builder.create();

        return dialog;
    }

}
