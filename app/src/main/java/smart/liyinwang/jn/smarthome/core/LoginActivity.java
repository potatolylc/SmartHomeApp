package smart.liyinwang.jn.smarthome.core;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.http.HttpClient;
import smart.liyinwang.jn.smarthome.http.ResponseHandler;
import smart.liyinwang.jn.smarthome.http.URIRepository;
import smart.liyinwang.jn.smarthome.utils.DummyUtils;
import smart.liyinwang.jn.smarthome.utils.Utils;

public class LoginActivity extends ActionBarActivity {
    // view component
    private AutoCompleteTextView mUserNameAutoText;
    private AutoCompleteTextView mWifiSsidAutoText;
    private EditText mPasswordEditText;
    private Button mSignInButton;

    // input variables
    String mUserName;
    String mUserWifiSsid;
    String mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserNameAutoText = (AutoCompleteTextView) findViewById(R.id.login_user_name_edit_text);
        mWifiSsidAutoText = (AutoCompleteTextView) findViewById(R.id.login_wifi_ssid_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.login_password_edit_text);

        mSignInButton = (Button)findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput();
                boolean flag = checkInputSpace();
                if(flag)
                    attemptLogin();
            }
        });
    }

    private void getInput() {
        mUserName = mUserNameAutoText.getText().toString();
        mUserWifiSsid = mWifiSsidAutoText.getText().toString();
        mUserPassword = mPasswordEditText.getText().toString();
    }

    private boolean checkInputSpace() {
        boolean flag = false;
        if(mUserName.equals("")) {
            Toast.makeText(this, "Please input user name.", Toast.LENGTH_SHORT).show();
            return flag;
        }
        if(mUserWifiSsid.equals("")) {
            Toast.makeText(this, "Please input WiFi name.", Toast.LENGTH_SHORT).show();
            return flag;
        }
        if(mUserPassword.equals("")) {
            Toast.makeText(this, "Please input password.", Toast.LENGTH_SHORT).show();
            return flag;
        }
        flag = true;
        return flag;
    }

    private void clearInput() {
        mUserNameAutoText.setText("");
        mWifiSsidAutoText.setText("");
        mPasswordEditText.setText("");
    }

    private void attemptLogin() {
        String uri = String.format(URIRepository.USER_LOGIN_AUTHENTICATION);
        System.out.println(uri);

        RequestParams params = new RequestParams();
        params.put(Utils.STRING_USER_NAME, mUserName);
        params.put(Utils.STRING_USER_WIFI_SSID, mUserWifiSsid);
        params.put(Utils.STRING_USER_PASSWORD, mUserPassword);

        HttpClient.getClient().get(uri, params, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    boolean loginFlag = (boolean)response.get("result");
                    if(loginFlag) {
                        System.out.println("Login succeeded...");

                        SharedPreferences.Editor editor = getSharedPreferences(Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
                        editor.putInt(Utils.STRING_USER_SERIAL_NUM, DummyUtils.DUMMY_USER_SERIAL_NUM);
                        editor.putString(Utils.STRING_USER_NAME, DummyUtils.DUMMY_USER_NAME);
                        editor.putString(Utils.STRING_USER_WIFI_SSID, DummyUtils.DUMMY_USER_WIFI_SSID);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed. Please try again.", Toast.LENGTH_SHORT).show();
                        clearInput();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
