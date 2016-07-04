package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by admin on 2016/6/26.
 */
public abstract class BaseDialogBuilder<T extends BaseDialogBuilder<T>> {
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
    private Fragment mTargetFragment;
    private boolean mCancelableOnTouchOutside;


    public BaseDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> claszz) {
        mFragmentManager = fragmentManager;
        mContext = context.getApplicationContext();
        mClass = claszz;
    }

    protected abstract T self();

    protected abstract Bundle preparearguments();

    public T setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return self();
    }

    public T setCancelableOnTouchOutside(boolean cancelable) {
        mCancelableOnTouchOutside = cancelable;
        if (cancelable) {
            mCancelable = cancelable;
        }
        return self();
    }

    public T setTargetFragment(Fragment fragment, int requestCode) {
        mTargetFragment = fragment;
        mRequestCode = requestCode;
        return self();
    }

    public T setRequestCode(int requestCode){
        mRequestCode = requestCode;
        return self();
    }

    public T setTage(String Tag) {
        mTag = Tag;
        return self();
    }

    public DialogFragment show(){
        Bundle args = preparearguments();
        BaseDialogFragment fragment = (BaseDialogFragment) Fragment.instantiate(mContext, mClass.getName(), args);

        args.putBoolean(ARG_CANCELABLE_ON_TOUCH_OUTSIDE, mCancelableOnTouchOutside);

        if (mTargetFragment != null) {
            fragment.setTargetFragment(mTargetFragment, mRequestCode);
        } else {
            args.putInt(ARG_REQUEST_CODE, mRequestCode);
        }

        fragment.setCancelable(mCancelable);
        fragment.show(mFragmentManager, mTag);

        return fragment;
    }


}
