package cn.qiuc.org.idialog_002_simpledialogstyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.qiuc.org.libs.idialog.ISimpleDialogCancelListener;
import cn.qiuc.org.libs.idialog.ISimpleDialogListener;
import cn.qiuc.org.libs.idialog.ProgressDialogFragment;
import cn.qiuc.org.libs.idialog.SimpleDialogFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ISimpleDialogListener, IFavoriteCharacterDialogListener,
        ISimpleDialogCancelListener {

    public static final int THEME_DEFAULT_DARK = 0;
    public static final int THEME_DEFAULT_LIGHT = 1;
    public static final int THEME_CUSTOM_DARK = 2;
    public static final int THEME_CUSTOM_LIGHT = 3;
    public static final String EXTRA_THEME = "theme";
    public static final int REQUEST_PROGRESS = 1;

    private Button btn_def_dark_theme;
    private Button btn_def_light_theme;
    private Button btn_cus_dark_theme;
    private Button btn_cus_light_theme;
    private Button btn_sim_dia_msg;
    private Button btn_sim_dia_msg_title;
    private Button btn_sim_dia_callbacks;
    private Button btn_dia_list;
    private Button btn_dia_custom_view;
    private Button btn_progress_dialog;

    private MainActivity myMainActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initListener() {
        btn_def_dark_theme.setOnClickListener(this);
        btn_def_light_theme.setOnClickListener(this);
        btn_cus_dark_theme.setOnClickListener(this);
        btn_cus_light_theme.setOnClickListener(this);
        btn_sim_dia_msg.setOnClickListener(this);
        btn_sim_dia_msg_title.setOnClickListener(this);
        btn_sim_dia_callbacks.setOnClickListener(this);
        btn_dia_list.setOnClickListener(this);
        btn_dia_custom_view.setOnClickListener(this);
        btn_progress_dialog.setOnClickListener(this);
    }

    private void initView() {
        btn_def_dark_theme = (Button) findViewById(R.id.btn_def_dark_theme);
        btn_def_light_theme = (Button) findViewById(R.id.btn_def_light_theme);
        btn_cus_dark_theme = (Button) findViewById(R.id.btn_cus_dark_theme);
        btn_cus_light_theme = (Button) findViewById(R.id.btn_cus_light_theme);
        btn_sim_dia_msg = (Button) findViewById(R.id.btn_sim_dia_msg);
        btn_sim_dia_msg_title = (Button) findViewById(R.id.btn_sim_dia_msg_title);
        btn_sim_dia_callbacks = (Button) findViewById(R.id.btn_sim_dia_callbacks);
        btn_dia_list = (Button) findViewById(R.id.btn_dia_list);
        btn_dia_custom_view = (Button) findViewById(R.id.btn_dia_custom_view);
        btn_progress_dialog = (Button) findViewById(R.id.btn_progress_dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_def_dark_theme:
                setCurrentTheme(THEME_DEFAULT_DARK);
                break;
            case R.id.btn_def_light_theme:
                setCurrentTheme(THEME_DEFAULT_LIGHT);
                break;
            case R.id.btn_cus_dark_theme:
                setCurrentTheme(THEME_CUSTOM_DARK);
                break;
            case R.id.btn_cus_light_theme:
                setCurrentTheme(THEME_CUSTOM_LIGHT);
                break;

            //------------------------------//
            case R.id.btn_sim_dia_msg:
                SimpleDialogFragment.createBuilder(myMainActivity, getSupportFragmentManager()).setMessage(R.string.msg_1).show();
                break;
            case R.id.btn_sim_dia_msg_title:
                SimpleDialogFragment.createBuilder(myMainActivity, getSupportFragmentManager()).setTitle(R.string.title).setMessage(R.string.msg_2).show();
                break;
            case R.id.btn_sim_dia_callbacks:
                SimpleDialogFragment.createBuilder(myMainActivity, getSupportFragmentManager())
                        .setTitle(R.string.title)
                        .setMessage(R.string.msg_3)
                        .setPositiveButtonText(R.string.positive_button)
                        .setNegativeButtonText(R.string.negative_button)
                        .setRequestCode(42).setTage("custome-tag")
                        .show();
                break;
            case R.id.btn_dia_list:
                ProgressDialogFragment.createBuilder(myMainActivity, getSupportFragmentManager())
                        .setMessage(R.string.msg_4)
                        .setRequestCode(REQUEST_PROGRESS)
                        .setTitle(R.string.app_name)
                        .show();
                break;
            case R.id.btn_dia_custom_view:
                FavoriteCharacterDialogFragment.show(myMainActivity,
                        "Your favourite character:", new String[]{
                                "Jayne","Malcolm", "Kayless", "Wash", "Zoe", "River"
                        });
                break;
            case R.id.btn_progress_dialog:
                JayneHatDialogFragment.show(myMainActivity);
                break;


        }
    }

    private void setCurrentTheme(int theme) {
        Intent intent = new Intent(myMainActivity, MainActivity.class);
        intent.putExtra(EXTRA_THEME, theme);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    private void setThemeOnCreate(){
        int theme = getIntent().getIntExtra(EXTRA_THEME, THEME_CUSTOM_DARK);
        switch (theme) {
            case THEME_DEFAULT_DARK:
//                setTheme(R.style.DefaultDarkTheme);
                break;
            case THEME_DEFAULT_LIGHT:
//                setTheme(R.style.DefaultLightTheme);
                break;
//                setTheme(R.style.CustomDarkTheme);
            case THEME_CUSTOM_DARK:
//                setTheme(R.style.CustomLightTheme);
                break;
            case  THEME_CUSTOM_LIGHT:

                break;
        }
    }

    @Override
    public void onListItemSelected(String value, int number) {
        Toast.makeText(myMainActivity, "Select:" + value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelled(int requestCode) {
        if (requestCode == 42) {
            Toast.makeText(myMainActivity, "Dialog canceled", Toast.LENGTH_SHORT).show();
        }else if (requestCode == REQUEST_PROGRESS) {
            Toast.makeText(myMainActivity, "progress dialog canceled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPositiveButtonClicked(int requestCode) {
        Toast.makeText(myMainActivity, "positive button clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {
        Toast.makeText(myMainActivity, "negative button clicked", Toast.LENGTH_SHORT).show();
    }
}
