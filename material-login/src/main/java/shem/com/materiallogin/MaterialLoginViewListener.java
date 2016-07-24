package shem.com.materiallogin;

import android.support.design.widget.TextInputLayout;
import android.widget.Spinner;

/**
 * Created by shem on 1/15/16.
 */
public interface MaterialLoginViewListener {
    void onRegister(TextInputLayout registerUser, TextInputLayout registerUserName, TextInputLayout registerCellNumber, TextInputLayout registerServiceName, Spinner registerCategoryName);
    //registerUser, registerUserName, registerCellNumber, registerServiceProviderName, registerServiceCategory
    void onLogin(TextInputLayout loginUser, TextInputLayout loginPass);
}
