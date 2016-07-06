package cn.qiuc.org.libs.idialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.qiuc.org.idialog_002_simpledialogstyle.R;

/**
 * Created by admin on 2016/7/7.
 */
public class ProgressDialogFragment extends BaseDialogFragment {

    protected static String ARG_MESSAGE = "message";
    protected static String ARG_TITLE = "title";

    protected int mRequestCode;

    public static ProgressDialogBuilder createBuilder(Context context, FragmentManager fragmentManager) {
        return new ProgressDialogBuilder(context, fragmentManager);
    }

    @Override
    protected Builder build(Builder initialBuilder) {
        int defaultMessageTextColor = getResources().getColor(R.color.sdl__message_text_dark);
        TypedArray a = getActivity().getTheme().obtainStyledAttributes(null, R.styleable.DialogStyle, R.attr.sdlDialogStyle, 0);
        int messagetTextColor = a.getColor(R.styleable.DialogStyle_messageTextColor, defaultMessageTextColor);
        a.recycle();

        LayoutInflater inflater = initialBuilder.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_part_progress, null, false);
        TextView tvMessage = (TextView) view.findViewById(R.id.sdl__message);
        tvMessage.setText(getArguments().getString(ARG_MESSAGE));
        tvMessage.setTextColor(messagetTextColor);

        initialBuilder.setView(view);
//        initialBuilder.setTitle(getArguments().getString(ARG_TITLE));

        return initialBuilder;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() == null) {
            throw new IllegalArgumentException("use ProgressDialogBuilder to construct this dialog");
        }

        final Fragment targetFragment = getTargetFragment();
        mRequestCode = targetFragment != null ? getTargetRequestCode() : getArguments().getInt(BaseDialogBuilder.ARG_REQUEST_CODE, 0);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        ISimpleDialogCancelListener listener = getCancelLister();
        if (listener != null) {
            listener.onCancelled(mRequestCode);
        }
    }

    protected ISimpleDialogCancelListener getCancelLister() {
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

    public static class ProgressDialogBuilder extends BaseDialogBuilder<ProgressDialogBuilder> {

        private String mTitle;
        private String mMessage;

        protected ProgressDialogBuilder(Context context, FragmentManager fragmentManager) {
            super(context, fragmentManager, ProgressDialogFragment.class);
        }

        @Override
        protected ProgressDialogBuilder self() {
            return null;
        }

        @Override
        protected Bundle prepareArguments() {
            Bundle args = new Bundle();
            args.putString(SimppleDialogFragment.ARG_MESSAGR, mMessage);
            args.putString(SimppleDialogFragment.ARG_TITLE, mTitle);
            return args;
        }

        public ProgressDialogBuilder setTitle(int titleResourceId) {
            mTitle = mContext.getString(titleResourceId);
            return this;
        }

        public ProgressDialogBuilder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public ProgressDialogBuilder setMessage(int messageResourceId) {
            mMessage = mContext.getString(messageResourceId);
            return this;
        }

        public ProgressDialogBuilder setMessage(String message) {
            mMessage = message;
            return this;
        }

    }
}
