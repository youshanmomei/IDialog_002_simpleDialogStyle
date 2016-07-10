package cn.qiuc.org.idialog_002_simpledialogstyle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import cn.qiuc.org.libs.idialog.BaseDialogFragment;

/**
 * Created by admin on 2016/7/10.
 */
public class FavoriteCharacterDialogFragment extends BaseDialogFragment {

    public static String TAG = "list";
    private static String ARG_TITLE = "title";
    private static String ARG_ITEMS = "items";
    private IFavoriteCharacterDialogListener mListener;


    public static void show(FragmentActivity activity, String title, String[] items){
        FavoriteCharacterDialogFragment dialog = new FavoriteCharacterDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putStringArray(ARG_ITEMS, items);
        dialog.setArguments(args);
        dialog.show(activity.getSupportFragmentManager(), TAG);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && targetFragment instanceof IFavoriteCharacterDialogListener) {
            mListener = (IFavoriteCharacterDialogListener) targetFragment;
        } else if (getActivity() instanceof IFavoriteCharacterDialogListener) {
            mListener = (IFavoriteCharacterDialogListener) getActivity();
        }
    }

    @Override
    protected Builder build(Builder initialBuilder) {
        initialBuilder.setTitle(getTitle());
        initialBuilder.setIcon(R.mipmap.ic_launcher);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_list, R.id.list_item_text, getItems());
        initialBuilder.setItems(adapter, 0, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onListItemSelected(getItems()[position], position);
                    dismiss();
                }
            }
        });

        initialBuilder.setPositiveButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return initialBuilder;
    }


    private String getTitle() {
        return getArguments().getString(ARG_TITLE);
    }

    private String[] getItems() {
        return getArguments().getStringArray(ARG_ITEMS);
    }

}
