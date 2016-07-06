package cn.qiuc.org.libs.idialog;

/**
 * Created by admin on 2016/7/6.
 */
public interface ISimpleDialogListener {
    void onPositiveButtonClicked(int requestCode);

    void onNegativeButtonClicked(int requestCode);
}
