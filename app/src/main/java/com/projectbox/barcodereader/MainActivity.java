package com.projectbox.barcodereader;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.EmptyPermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sudar.zxingorient.ZxingOrient;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_Result)
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        addPermission();
    }

    private void addPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new EmptyPermissionListener())
                .check();
    }

    @OnClick(R.id.btn_Scan)
    protected void onScanCliked() {
//        ----- START: THIS BLOCK OF CODE IS FOR ZXING STANDALONE
//        Intent intent = new Intent(this, CaptureActivity.class);
//        intent.setAction("com.google.zxing.client.android.SCAN");
//        intent.putExtra("SAVE_HISTORY", false);
//        startActivityForResult(intent, 0);
//        ----- END

        new ZxingOrient(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        ----- START : THIS BLOCK OF CODE IS FOR ZXING STANDALONE
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                String contents = data.getStringExtra("SCAN_RESULT");
//                txtResult.setText(contents);
//
//                Log.v(MainActivity.class.getName(), contents);
//            } else if (resultCode == RESULT_CANCELED) {
//                Log.e(MainActivity.class.getName(), "Canceled");
//            }
//        }
//        ------ END

        me.sudar.zxingorient.ZxingOrientResult result = ZxingOrient.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            txtResult.setText(result.getContents());
        }
    }
}
