package com.murugamani.example.yuvati;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.murugamani.example.yuvati.Fragments.ViolenceFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    private int menuItem;

    public static String LOGIN_ID = "login_status";
    private final int RC_SIGN_IN = 1;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private String logOut="true";

   // private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.bringToFront();

        navigationView.getMenu().findItem(R.id.schemes).setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.getMenu().findItem(menuItem).setChecked(false);
                navigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                Fragment fragment = null;
                Class fragmentClass;
                switch (item.getItemId()){
                    case R.id.schemes:
                        //fragmentClass = SchemeFragment.class;
                        fragmentClass = ViolenceFragment.class;
                        Toast.makeText(MainActivity.this,"Schemes",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.fund:
                        //fragmentClass = FundFragment.class;
                        fragmentClass = ViolenceFragment.class;
                        Toast.makeText(MainActivity.this,"Funds",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tips:
                        //fragmentClass = TipFragment.class;
                        fragmentClass = ViolenceFragment.class;
                        Toast.makeText(MainActivity.this,"Tips",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.violence:
                        fragmentClass = ViolenceFragment.class;
                        Toast.makeText(MainActivity.this,"Violence",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.account_setting:
                        //fragmentClass = SettingFragment.class;
                        fragmentClass = ViolenceFragment.class;
                        break;
                    default:
                        fragmentClass = ViolenceFragment.class;
                        Toast.makeText(MainActivity.this,"Violence",Toast.LENGTH_SHORT).show();
                }

                menuItem = item.getItemId();

                try{
                    fragment = (Fragment)fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.fragment,fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                mDrawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });


        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Intent intent = getIntent();

        if (intent.getExtras()!=null){
            logOut = intent.getExtras().getString("show");
        }

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

  //      if (firebaseUser != null){
        //    logOut = "true";
    //    }else {
          //  logOut = "false";
      //  }

        check();
    }

    public void check(){
        if (sharedPreferences.getBoolean(LOGIN_ID,true)){
      //      Intent intent = new Intent(MainActivity.this,LoginActivity.class);
       //     startActivityForResult(intent,RC_SIGN_IN);
       //     finish();
        }else {
            schemeFragment();
            Toast.makeText(MainActivity.this,"Mani",Toast.LENGTH_LONG).show();
        }
    }

    private void schemeFragment(){
        menuItem = R.id.schemes;
        Fragment fragment = new ViolenceFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_CANCELED){
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START);
        }else if (menuItem == R.id.schemes){
            finish();
        }else{
            navigationView.getMenu().findItem(R.id.schemes).setChecked(true);
            navigationView.getMenu().findItem(menuItem).setChecked(false);
            schemeFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (logOut.equals("false")){
            menu.getItem(0).setVisible(false);
        }else if (logOut.equals("true")){
            menu.getItem(0).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }else if (item.getItemId() == R.id.log_out){
        //    if (firebaseUser != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are You want to LogOut??");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
          //              AuthUI.getInstance().signOut(MainActivity.this);
                        editor.putBoolean(LOGIN_ID, true);
                        editor.apply();
                        check();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dialogInterface != null){
                            dialogInterface.dismiss();
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            //}
        }
        return super.onOptionsItemSelected(item);
    }
}
