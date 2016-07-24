package com.example.barnes.ummoqmasterdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMaster;
import com.example.barnes.ummoqmasterdashboard.ummoAPI.QMasterListener;

import shem.com.materiallogin.MaterialLoginView;
import shem.com.materiallogin.MaterialLoginViewListener;

public class MainActivity_Register_Login extends AppCompatActivity implements QMasterListener{


    //Overloads for QmasterListener


    @Override
    public void qCreated(String string) {

    }

    @Override
    public void registrationError(String string) {

    }

    @Override
    public void createQError(String string) {

    }

    @Override
    public void registered(String string) {
        if (string.equals("SUCCESS")){
            Intent ummo_tutorials = new Intent(this, PagerActivity.class);
            startActivity(ummo_tutorials);
            finish();
            overridePendingTransition(R.layout.fadein, R.layout.fadeout);
        }
    }

    @Override
    public void qDestroyed(String string) {

    }

    @Override
    public void userMoved(String string) {

    }

    @Override
    public void userDQd(String string) {

    }

    @Override
    public void feedBackRecieved(String string) {

    }

    @Override
    public void myQRecieved(String string) {

    }

    @Override
    public void updatesRecieved(String string) {

    }


    @Override
    public void onUpdtaesError(String string) {

    }

    @Override
    public void onFeedBackError(String string) {

    }

    @Override
    public void onUserMoveError(String string) {

    }

    @Override
    public void onUserDQError(String string) {

    }

    @Override
    public void onQDestroyError(String sting) {

    }

    //End Overloads

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_register);

        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);
        login.setListener(new MaterialLoginViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerUserName, TextInputLayout registerCellNumber,
                                   TextInputLayout registerServiceName, Spinner registerCategoryName) {
                String user = registerUser.getEditText().getText().toString();
                if (user.isEmpty()) {
                    registerUser.setError("User Email can't be empty");
                    return;
                }
                registerUser.setError("");

                String userName = registerUserName.getEditText().getText().toString();
                if (userName.isEmpty()) {
                    registerUserName.setError("User name can't be empty");
                    return;
                }
                registerUserName.setError("");

                String cellNumber = registerCellNumber.getEditText().getText().toString();
                if (cellNumber.isEmpty()) {
                    registerCellNumber.setError("Cell Number can't be empty");
                    return;
                }
                registerCellNumber.setError("");

                String serviceProviderName = registerServiceName.getEditText().getText().toString();
                if (serviceProviderName.isEmpty()) {
                    registerServiceName.setError("Service Provider Name can't be empty");
                    return;
                }
                registerServiceName.setError("");

                String serviceCategory = registerCategoryName.getSelectedItem().toString();
                if (serviceCategory.isEmpty()) {
                    //registerCategoryName.setError("Service Categoty Name can't be empty");
                    return;
                }
                //registerCategoryName.setError("");

                QMaster qMaster = new QMaster(MainActivity_Register_Login.this);
                qMaster.register(userName,cellNumber,user,serviceProviderName,serviceCategory);

                Snackbar.make(login, serviceCategory, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                String user = loginUser.getEditText().getText().toString();
                if (user.isEmpty()) {
                    loginUser.setError("User name can't be empty");
                    return;
                }
                loginUser.setError("");

                String pass = loginPass.getEditText().getText().toString();
                if (!pass.equals(user)) {
                    loginPass.setError("Wrong password");
                    return;
                }
                loginPass.setError("");

                Snackbar.make(login, "Login success!", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
