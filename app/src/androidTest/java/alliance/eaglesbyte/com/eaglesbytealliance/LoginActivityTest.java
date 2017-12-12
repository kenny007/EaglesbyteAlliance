package alliance.eaglesbyte.com.eaglesbytealliance;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kennymore on 12/12/2017.
 */
public class LoginActivityTest {
    //This rule enables launching of the activity
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity lgActivity = null;
    @Before
    public void setUp() throws Exception {
        lgActivity = loginActivityTestRule.getActivity(); //this provides the context
    }

    @Test
    public void testLoginLaunch(){
        View view = lgActivity.findViewById(R.id.email);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        lgActivity = null;
    }

}