package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.support.v4.app.FragmentManager;

/**
 * Created by admin on 2016/6/26.
 */
public class SimppleDialogFragment {

    public static SimppleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {

        return null;
    }


    private static class SimppleDialogBuilder extends BaseDialogBuilder<SimppleDialogBuilder>{

        public SimppleDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> claszz) {
            super(context, fragmentManager, claszz);
        }
    }
}
