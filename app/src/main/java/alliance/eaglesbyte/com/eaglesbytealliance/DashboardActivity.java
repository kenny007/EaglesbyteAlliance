package alliance.eaglesbyte.com.eaglesbytealliance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import alliance.eaglesbyte.com.eaglesbytealliance.models.Course;

public class DashboardActivity extends AppCompatActivity {

    public static final String TAG = "DashboardActivity";

    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;

    // widgets and UI References
    private EditText mTitle, mCode, mCredits;
    private Button mSubmit;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mTitle = (EditText) findViewById(R.id.input_title);
        mCode = (EditText) findViewById(R.id.input_code);
        mCredits = (EditText)findViewById(R.id.input_credits);
        mSubmit = (Button) findViewById(R.id.btn_addcourse);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        instFirebaseAuth();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to add a course");

                if(!isEmpty(mTitle.getText().toString()) && !isEmpty(mCode.getText().toString()) && !isEmpty(mCredits.getText().toString()))
                {
                    addNewCourse(mTitle.getText().toString(), mCode.getText().toString(),mCredits.getText().toString());
                } else{
                    Toast.makeText(DashboardActivity.this, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     * @param string
     * @return
     */
    private boolean isEmpty(String string){
        return string.equals("") || string.equals(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAuthenticationState();
    }
    private void addNewCourse(String title, String code, String credits){
        showDialog();

        //check if user is authenticated
        checkAuthenticationState();
        Course course = new Course();
        course.setTitle(title);
        course.setCode(code);
        course.setEcts_credits(credits);
        course.setPrerequisite("1");
        course.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.dbnode_courses))
                .setValue(course).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DashboardActivity.this, "Course successfully added", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DashboardActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
            }
        });
                //.child(FirebaseAuth.getInstance().getCurrentUser().getUid()) this line allows user to insert into only their own database node




    }

    private void checkAuthenticationState(){
        Log.d(TAG, "checkAuthenticationState: checking authentication state.");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Log.d(TAG, "checkAuthenticationState: user is null, navigating back to login screen.");

            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else{
            Log.d(TAG, "checkAuthenticationState: user is authenticated.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.optionSignOut:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

    /* Firebase Setup*/

    private void instFirebaseAuth(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void showDialog(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private  void hideDialog(){
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
