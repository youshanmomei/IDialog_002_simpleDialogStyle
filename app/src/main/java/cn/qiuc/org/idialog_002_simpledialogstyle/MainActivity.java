package cn.qiuc.org.idialog_002_simpledialogstyle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import cn.qiuc.org.libs.idialog.SimpleDialogFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

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

                break;
            case R.id.btn_def_light_theme:

                break;
            case R.id.btn_cus_dark_theme:

                break;
            case R.id.btn_cus_light_theme:

                break;

            //------------------------------//
            case R.id.btn_sim_dia_msg:
                SimpleDialogFragment.createBuilder(myMainActivity, getSupportFragmentManager()).setMessage(R.string.msg_1).show();
                break;
            case R.id.btn_sim_dia_msg_title:

                break;
            case R.id.btn_sim_dia_callbacks:

                break;
            case R.id.btn_dia_list:

                break;
            case R.id.btn_dia_custom_view:

                break;
            case R.id.btn_progress_dialog:

                break;


        }
    }
}
