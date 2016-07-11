package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;

import cn.qiuc.org.idialog_002_simpledialogstyle.R;

/**
 * Created by admin on 2016/6/26.
 */
public class SimpleDialogFragment extends BaseDialogFragment{

    public static String ARG_MESSAGR = "message";
    public static String ARG_TITLE = "title";
    protected static String ARG_POSITIVE_BUTTON = "positive_button";
    protected static String ARG_NEGATIVE_BUTTON = "negative_button";
    protected int mRequestCode;

    public static SimpleDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
        return new SimpleDialogBuilder(context, fragmentManager, SimpleDialogFragment.class);
    }

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

    protected ISimpleDialogListener getDialogListener() {
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


    public static class SimpleDialogBuilder extends BaseDialogBuilder<SimpleDialogBuilder>{

        private boolean mShowDefaultButton = false;
        private String mTitle;
        private CharSequence mMessage1;
        private String mPositiveButtonText;
        private String mNegativeButtonText;

        public SimpleDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends SimpleDialogFragment> clazz) {
            super(context, fragmentManager, clazz);
        }

        @Override
        protected SimpleDialogBuilder self() {
            return this;
        }

        public SimpleDialogBuilder setTitle(int titleResourceId) {
            mTitle = mContext.getString(titleResourceId);
            return this;
        }

        public SimpleDialogBuilder setTitle(String title) {
            mTitle = title;
            return this;
        }

        /**
         * Allow to set resource string with HTML formatting and bind %s,%i
         * @param resourceId
         * @param formatArgs
         * @return
         */
        public SimpleDialogBuilder setMessage(int resourceId, Object... formatArgs) {
            Html.fromHtml(String.format(Html.toHtml(new SpannableString(mContext.getText(resourceId))), formatArgs));
            return this;
        }

        public SimpleDialogBuilder setMessage(CharSequence message){
            mMessage1 = message;
            return this;
        }

        public SimpleDialogBuilder setPositiveButtonText(int textResourceId){
            mPositiveButtonText = mContext.getString(textResourceId);
            return this;
        }

        public SimpleDialogBuilder setPositiveButtonText(String text) {
            mPositiveButtonText = text;
            return this;
        }

        public SimpleDialogBuilder setNegativeButtonText(int textResourceId) {
            mNegativeButtonText = mContext.getString(textResourceId);
            return this;
        }

        public SimpleDialogBuilder setNegativeButtonText(String text) {
            mNegativeButtonText = text;
            return this;
        }

        /**
         * when there is neither positive nor negative button, default close button is created if it was enable.<br/>
         * Default is true.
         * @param hide
         * @return
         */
        public SimpleDialogBuilder hideDefaultButton(boolean hide) {
            mShowDefaultButton = !hide;
            return this;
        }

        @Override
        protected Bundle prepareArguments() {
            if (mShowDefaultButton && mPositiveButtonText == null && mNegativeButtonText == null) {
                mPositiveButtonText = mContext.getString(R.string.dialog_close);
            }

            Bundle args = new Bundle();
            args.putCharSequence(SimpleDialogFragment.ARG_MESSAGR, mMessage1);
            args.putString(SimpleDialogFragment.ARG_TITLE, mTitle);
            args.putString(SimpleDialogFragment.ARG_POSITIVE_BUTTON, mPositiveButtonText);
            args.putString(SimpleDialogFragment.ARG_NEGATIVE_BUTTON, mNegativeButtonText);

            return args;
        }
    }
}
