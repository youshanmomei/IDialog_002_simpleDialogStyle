package cn.qiuc.org.libs.idialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import cn.qiuc.org.idialog_002_simpledialogstyle.R;

/**
 * Created by admin on 2016/6/26.
 */
public class BaseDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.SDL_Dialog);

        //dialog background
        TypedArray typedArray = getActivity().getTheme().obtainStyledAttributes(null, R.styleable.DialogStyle, R.attr.sdlDialogStyle, 0);
        Drawable dialogBackground = typedArray.getDrawable(R.styleable.DialogStyle_dialogBackground);
        typedArray.recycle();

        dialog.getWindow().setBackgroundDrawable(dialogBackground);
        Bundle args = getArguments();
        if (args != null) {
            dialog.setCanceledOnTouchOutside(args.getBoolean(BaseDialogBuilder.ARG_CANCELABLE_ON_TOUCH_OUTSIDE));
        }

        return dialog;
    }
/*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Builder builder = new Builder(this, getActivity(), inflater, container);
        return builder;
    }

    protected abstract Builder build(Builder initialBuilder);

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().ssetDismissMessage(null);
        }
        super.onDestroyView();
    }
*/

    /**
     * @return the positive button if specified and the view is created, null otherwise
     */
    protected Button getPositiveButton() {
        if (getView() != null) {
            return (Button) getView().findViewById(R.id.sdl__positive_button);
        } else {
            return null;
        }
    }

    /**
     * @return the negative button if specified and the view is created, null otherwise
     */
    protected Button getNegativeButton() {
        if (getView() != null) {
            return (Button) getView().findViewById(R.id.sdl__negative_button);
        } else {
            return null;
        }
    }

    /**
     * @return the neutral button if specified and the view is created, null otherwise
     */
    protected Button getNeutralButton() {
        if (getView() != null) {
            return (Button) getView().findViewById(R.id.sdl__neural_button);
        } else {
            return null;
        }
    }

    /**
     * Custom dialog builder
     */
    protected static class Builder {

        private final DialogFragment mDialpgment;
        private final Context mContext;
        private final ViewGroup mContainer;
        private final LayoutInflater mInflater;

        private CharSequence mTitle = null;
        private CharSequence mPositiveButtonText;
        private View.OnClickListener mPositiveButtonListener;
        private CharSequence mNegativeButtonText;
        private View.OnClickListener mNegativeButtonListener;
        private CharSequence mNeutralButtonText;
        private View.OnClickListener mNeutralButtonListener;
        private CharSequence mMessage;
        private ListAdapter mListAdapter;
        private AdapterView.OnItemClickListener mOnItemClickListener;
        private int mListcheckedItemIdx;
        private View mView;
        private boolean mViewSpacingSpecified;
        private int mViewSpacingLeft;
        private int mViewSpacingTop;
        private int mViewSpacingRight;
        private int mViewSpacingBottom;
        private Drawable mIcon;
        private int mTitleTextColor;
        private int mTitleSeparatorColor;
        private int mMessageTextColor;

        public Builder(DialogFragment dialogFragment, Context context, LayoutInflater inflater, ViewGroup container) {
            this.mDialpgment = dialogFragment;
            this.mContext = context;
            this.mContainer = container;
            this.mInflater = inflater;
        }

        public LayoutInflater getLayoutInflater() {
            return mInflater;
        }

        public Builder setTitle(int titleld) {
            this.mTitle = mContext.getText(titleld);
            return this;
        }

        public Builder setPostiveButton(int textId, final View.OnClickListener listener) {
            mPositiveButtonText = mContext.getText(textId);
            mPositiveButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            mPositiveButtonText = text;
            mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(int textId, final View.OnClickListener listener) {
            mNegativeButtonText = mContext.getText(textId);
            mPositiveButtonListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            mNegativeButtonText = text;
            mNegativeButtonListener = listener;
            return this;
        }

        public Builder setNeturalButton(int textId, final View.OnClickListener listener) {
            mNeutralButtonText = mContext.getText(textId);
            mNeutralButtonListener = listener;
            return this;
        }

        public Builder setNeturalButton(CharSequence text, final View.OnClickListener listener) {
            mNeutralButtonText = text;
            mNeutralButtonListener = listener;
            return this;
        }

        public Builder setMessage(int messageId) {
            mMessage = mContext.getText(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            mMessage = message;
            return this;
        }

        /**
         * set list
         * @param listAdapter
         * @param checkedItemIdx
         * @param listener
         * @return
         */
        public Builder setItems(ListAdapter listAdapter, int checkedItemIdx, final AdapterView.OnItemClickListener listener) {
            mListAdapter = listAdapter;
            mOnItemClickListener = listener;
            mListcheckedItemIdx = checkedItemIdx;
            return this;
        }

        public Builder setView(View view){
            mView = view;
            mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view, int viewSpacingLeft, int  viewSpacingTop, int viewSpacingRight, int viewSpacingBottom){
            mView = view;
            mViewSpacingSpecified = true;
            mViewSpacingLeft = viewSpacingLeft;
            mViewSpacingTop = viewSpacingTop;
            mViewSpacingRight = viewSpacingRight;
            mViewSpacingBottom = viewSpacingBottom;
            return this;
        }

        public Builder setIcon(int resourceId) {
            mIcon = mContext.getResources().getDrawable(resourceId);
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            mIcon = drawable;
            return this;
        }

        public View create(){
            final Resources res = mContext.getResources();
            int defaultTitleTextColor = res.getColor(R.color.sdl_title_text_dark);
            int defaultTitleSeparatorColor = res.getColor(R.color.sdl_title_separator_dark);
            int defaultMessageTextColor = res.getColor(R.color.sdl_message_text_dark);
            ColorStateList defaultButtonTextColor = res.getColorStateList(R.color.sdl_button_text_dark);
            int defaultButtonSeparatorColor = res.getColor(R.color.sdl_button_separator_dark);
            int defaultButtonBackgroundColorNormal = res.getColor(R.color.sdl_button_noraml_dark);
            int defaultButtonBackgrroundColorPressed = res.getColor(R.color.sdl_Button_pressed_dark);
            int defaultButtonBackgroundColorFocused = res.getColor(R.color.sdl_button_focused_dark);

            TypedArray a = mContext.getTheme().obtainStyledAttributes(null, R.styleable.DialogStyle, R.attr.sdlDialogStyle, 0);
            mTitleTextColor = a.getColor(R.styleable.DialogStyle_titleTextColor, defaultTitleTextColor);
            mTitleSeparatorColor = a.getColor(R.styleable.DialogStyle_titleSeparatorColor, defaultTitleSeparatorColor);
            mMessageTextColor = a.getColor(R.styleable.DialogStyle_messageTextColor, defaultMessageTextColor);
            ColorStateList mButtonTextColor = a.getColorStateList(R.styleable.DialogStyle_buttonTextColor);

            if (mButtonTextColor == null) {
                mButtonTextColor = defaultButtonTextColor;
            }

            int mButtonSeparatorColor = a.getColor(R.styleable.DialogStyle_buttonSeparatorColor, defaultButtonSeparatorColor);
            int mButtonBackgroundColorNoraml = a.getColor(R.styleable.DialogStyle_buttonBackgroundColorNormal, defaultButtonBackgroundColorNormal);
            int mButtonBackgroundColorPressed = a.getColor(R.styleable.DialogStyle_buttonBackgroundColorPressed, defaultButtonBackgrroundColorPressed);
            int mButtonBackgroundColorFocused = a.getColor(R.styleable.DialogStyle_buttonBackgroundColorFocused, defaultButtonBackgroundColorFocused);
            a.recycle();

            View v = getDialogLayoutAndInitTitle();
            //TODO...

            return null;

        }

        private View getDialogLayoutAndInitTitle() {
            View v = mInflater.inflate(R.layout.dialog_part_title, mContainer, false);
            TextView tvTitle = (TextView) v.findViewById(R.id.sdl__title);
            View viewTitleDivider = v.findViewById(R.id.sdl__titleDivider);
            if (mTitle != null) {
                tvTitle.setText(mTitle);
                tvTitle.setTextColor(mTitleTextColor);
                if (mIcon != null) {
                    tvTitle.setCompoundDrawablesWithIntrinsicBounds(mIcon, null, null, null);
                    tvTitle.setCompoundDrawablePadding(mContext.getResources().getDimensionPixelSize(R.dimen.grid_2));
                }
                viewTitleDivider.setBackgroundDrawable(new ColorDrawable(mTitleSeparatorColor));
            } else {
                tvTitle.setVisibility(View.GONE);
                viewTitleDivider.setVisibility(View.GONE);
            }

            return v;
        }


    }
}
