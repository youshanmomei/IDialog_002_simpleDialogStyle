package cn.qiuc.org.libs.idialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import cn.qiuc.org.idialog_002_simpledialogstyle.R;

/**
 * Created by admin on 2016/6/26.
 */
public class BaseDialogFragment extends DialogFragment{

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
}
