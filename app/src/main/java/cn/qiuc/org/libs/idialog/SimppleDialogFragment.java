package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by admin on 2016/6/26.
 */
public class SimppleDialogFragment {

    public static String ARG_MESSAGR = "message";
    public static String ARG_TITLE = "title";

    public static SimppleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {

        return null;
    }


    private static class SimppleDialogBuilder extends BaseDialogBuilder<SimppleDialogBuilder>{

        public SimppleDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> claszz) {
            super(context, fragmentManager, claszz);
        }

        @Override
        protected SimppleDialogBuilder self() {
            return null;
        }

        @Override
        protected Bundle prepareArguments() {
            return null;
        }
    }
}
