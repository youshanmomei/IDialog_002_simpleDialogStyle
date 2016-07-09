package cn.qiuc.org.idialog_002_simpledialogstyle;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import cn.qiuc.org.libs.idialog.ISimpleDialogListener;
import cn.qiuc.org.libs.idialog.SimpleDialogFragment;

/**
 * Created by admin on 2016/7/9.
 */
public class JayneHatDialogFragment extends SimpleDialogFragment {

    public static String TAG = "jayne";

    public static void show(FragmentActivity activity) {
        new JayneHatDialogFragment().show(activity.getSupportFragmentManager(), TAG);
    }

    @Override
    protected Builder build(Builder builder) {
        builder.setTitle("Jayne's hat");
        builder.setView(LayoutInflater.from(getActivity()).inflate(R.layout.item_jayne_hat, null));
        builder.setPositiveButton("I want one", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISimpleDialogListener listener = getDialogListener();

                if (listener != null) {
                    listener.onPositiveButtonClicked(0);
                }
                dismiss();
            }
        });

        return builder;
    }
}
