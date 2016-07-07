package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by admin on 2016/6/26.
 */
public class SimppleDialogFragment extends BaseDialogFragment{

    public static String ARG_MESSAGR = "message";
    public static String ARG_TITLE = "title";
    protected static String ARG_POSITIVE_BUTTON = "positive_button";
    protected static String ARG_NEGATIVE_BUTTON = "negative_button";
    protected int mRequestCode;

//    public static SimpleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
//        return new SimpleDialogBuilder(context, fragmentManager, SimpleDateFormat.class);//TODO...
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            mRequestCode = getTargetRequestCode();
        }else{
            Bundle args = getArguments();
            if (args != null) {
                mRequestCode = args.getInt(BaseDialogBuilder.ARG_REQUEST_CODE, 0);
            }
        }
    }

    /**
     * children can extend this to add more things to base builder
     * @param builder
     * @return
     */
    @Override
    protected Builder build(Builder builder) {
        final String title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        final CharSequence message = getMessage();
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        final String postiveButtonText = getPostiveButtonText();
        if (!TextUtils.isEmpty(postiveButtonText)) {
            builder.setPositiveButton(postiveButtonText, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ISimpleDialogListener listener = getDialogListener();
                    if (listener != null) {
                        listener.onPositiveButtonClicked(mRequestCode);
                    }
                    dismiss();
                }
            });
        }

        final String negativeButtonText = getNegativeButtonText();
        if (!TextUtils.isEmpty(negativeButtonText)){
            builder.setNegativeButton(negativeButtonText, new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    ISimpleDialogListener listener = getDialogListener();
                    if (listener != null) {
                        listener.onNegativeButtonClicked(mRequestCode);
                    }
                    dismiss();
                }
            });
        }

        return builder;
    }

    protected CharSequence getMessage() {
        return getArguments().getCharSequence(ARG_MESSAGR);
    }

    protected String getTitle() {
        return getArguments().getString(ARG_TITLE);
    }

    protected String getPostiveButtonText() {
        return getArguments().getString(ARG_POSITIVE_BUTTON);
    }


    protected String getNegativeButtonText() {
        return getArguments().getString(ARG_NEGATIVE_BUTTON);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        ISimpleDialogCancelListener listener = getCancelListener();
        if (listener != null) {
            listener.onCancelled(mRequestCode);
        }
    }

    protected ISimpleDialogCancelListener getCancelListener() {
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            if (targetFragment instanceof ISimpleDialogCancelListener) {
                return (ISimpleDialogCancelListener) targetFragment;
            }
        } else {
            if (getActivity() instanceof ISimpleDialogCancelListener) {
                return (ISimpleDialogCancelListener) getActivity();
            }
        }
        return null;
    }

    private ISimpleDialogListener getDialogListener() {
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            if (targetFragment instanceof ISimpleDialogListener) {
                return (ISimpleDialogListener) targetFragment;
            }
        } else {
            if (getActivity() instanceof ISimpleDialogListener) {
                return (ISimpleDialogListener) getActivity();
            }
        }
        return null;
    }


    private static class SimpleDialogBuilder extends BaseDialogBuilder<SimpleDialogBuilder>{

        public SimpleDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> claszz) {
            super(context, fragmentManager, claszz);
        }

        @Override
        protected SimpleDialogBuilder self() {
            return null;
        }

        @Override
        protected Bundle prepareArguments() {
            return null;
        }
    }
}
