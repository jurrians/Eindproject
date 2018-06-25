package e.gebruiker.eindproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import e.gebruiker.eindproject.fragment.BalanceFragment;
import e.gebruiker.eindproject.fragment.IncomeFragment;
import e.gebruiker.eindproject.fragment.PriorityFragment;
import e.gebruiker.eindproject.fragment.TransactionsFragment;
import e.gebruiker.eindproject.fragment.UserFragment;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
//import e.gebruiker.eindproject.fragment.UserFragment;
import yalantis.com.sidemenu.util.ViewAnimator;


public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
//    private ContentFragment contentFragment;
    private UserFragment userFragment;
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;


//    int MY_PERMISSIONS_REQUEST_CAMERA = 0;
//
//
//    public CameraSource mCameraSource;
//    public SurfaceView mCameraView;
//    public TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);










//        TextRecognizer textRecognizer = new TextRecognizer();



//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//        {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
//            {
//
//            }
//            else
//            {
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionId );
//            }
//        }
//
//
//        mCameraView = (SurfaceView) findViewById(R.id.surfaceViewAddTrans);
//        mTextView = (TextView) findViewById(R.id.textViewAdTransaction);




        userFragment = UserFragment.newInstance(R.layout.fragment_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, userFragment)
                .commit();



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, userFragment, drawerLayout, this);





    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(UserFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem1 = new SlideMenuItem(UserFragment.USER, R.drawable.icn_user);
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem(UserFragment.BALANCE, R.drawable.icn_balance);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(UserFragment.INCOME, R.drawable.icn_income);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(UserFragment.PRIORITY, R.drawable.icn_priority);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(UserFragment.TRANSACTIONS, R.drawable.icn_transactions);
        list.add(menuItem5);


    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public ScreenShotable replaceCurrentFragment(ScreenShotable screenShotable) {
        return screenShotable;
    }



    // replaceUser
    private ScreenShotable replaceUserFragment(int topPosition) {

        Log.d("replaceUserFragment","check!");

        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, userFragment).addToBackStack(null).commit();
        return userFragment;
    }


    // replaceBalance
    private ScreenShotable replaceBalanceFragment(int topPosition) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        BalanceFragment balanceFragment = new BalanceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, balanceFragment).addToBackStack(null).commit();
        return balanceFragment;
    }

    // replaceTransactions
    private ScreenShotable replaceTransactionsFragment(int topPosition) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        TransactionsFragment transactionsFragment = new TransactionsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, transactionsFragment).addToBackStack(null).commit();
        return transactionsFragment;
    }

    // replaceIncome
    private ScreenShotable replaceIncomeFragment(int topPosition) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        IncomeFragment incomeFragment = new IncomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, incomeFragment).addToBackStack(null).commit();
        return incomeFragment;
    }

     // replacePriority
    private ScreenShotable replacePriorityFragment(int topPosition) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        PriorityFragment priorityFragment = new PriorityFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, priorityFragment).addToBackStack(null).commit();
        return priorityFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case UserFragment.USER:
                return replaceUserFragment(position);
            case UserFragment.BALANCE:
                return replaceBalanceFragment(position);
            case UserFragment.TRANSACTIONS:
                return replaceTransactionsFragment(position);
            case UserFragment.INCOME:
                return replaceIncomeFragment(position);
            case UserFragment.PRIORITY:
                return replacePriorityFragment(position);
            default:
                return replaceCurrentFragment(screenShotable);
        }

    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

//    private void startCameraSource() {
//
//        //Create the TextRecognizer
//        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
//
//        if (!textRecognizer.isOperational()) {
//            Log.w("tag", "Detector dependencies not loaded yet");
//        } else {
//
//            //Initialize camerasource to use high resolution and set Autofocus on.
//            mCameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
//                    .setFacing(CameraSource.CAMERA_FACING_BACK)
//                    .setRequestedPreviewSize(1280, 1024)
//                    .setAutoFocusEnabled(true)
//                    .setRequestedFps(2.0f)
//                    .build();
//
//            /**
//             * Add call back to SurfaceView and check if camera permission is granted.
//             * If permission is granted we can start our cameraSource and pass it to surfaceView
//             */
//            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {
//                    try {
//
//                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
//                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//                            ActivityCompat.requestPermissions(MainActivity.this,
//                                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
//                            return;
//                        }
//                        mCameraSource.start(mCameraView.getHolder());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                }
//
//                /**
//                 * Release resources for cameraSource
//                 */
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {
//                    mCameraSource.stop();
//                }
//            });
//
//            //Set the TextRecognizer's Processor.
//            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
//                @Override
//                public void release() {
//                }
//
//                /**
//                 * Detect all the text from camera using TextBlock and the values into a stringBuilder
//                 * which will then be set to the textView.
//                 * */
//                @Override
//                public void receiveDetections(Detector.Detections<TextBlock> detections) {
//                    final SparseArray<TextBlock> items = detections.getDetectedItems();
//                    if (items.size() != 0 ){
//
//                        mTextView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                StringBuilder stringBuilder = new StringBuilder();
//                                for(int i=0;i<items.size();i++){
//                                    TextBlock item = items.valueAt(i);
//                                    stringBuilder.append(item.getValue());
//                                    stringBuilder.append("\n");
//                                }
//                                mTextView.setText(stringBuilder.toString());
//                            }
//                        });
//                    }
//                }
//            });
//        }
//    }
//

}

