package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.support.v4.app.FragmentManager;

/**
 * Created by admin on 2016/6/26.
 */
public class BaseDialogBuilder<T extends BaseDialogBuilder<T>> {
    public static String ARG_REQUEST_CODE = "request_code";
    public static String ARG_CANCELABLE_ON_TOUCH_OUTSIDE = "cancelable_oto";
    public static String DEFAULT_TAG = "simple_dialog";
    public static int DEFAUL_REQUEST_CODE = -42;

    private boolean mCancelable = true;
    private boolean ismCancelableOnTouchOutSide = true;
    private String mTag = DEFAULT_TAG;
    private int mRequestCode = DEFAUL_REQUEST_CODE;

    private final FragmentManager mFragmentManager;
    private final Context mContext;
    private final Class<? extends BaseDialogFragment> mClass;


    public BaseDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> claszz) {
        mFragmentManager = fragmentManager;
        mContext = context.getApplicationContext();
        mClass = claszz;
    }

}
