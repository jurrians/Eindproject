package e.gebruiker.eindproject.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;

import e.gebruiker.eindproject.Database;
import e.gebruiker.eindproject.MainActivity;
import e.gebruiker.eindproject.R;

import e.gebruiker.eindproject.TransactionEntry;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class AddTransactionFragment extends Fragment implements ScreenShotable {


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;




    public String cameraFor;

    public Bundle cameraForBundle;


    private Bundle savedState = null;


    public EditText editTag;
    private EditText editPrice;
    private Spinner categorySpinner;
    private EditText editDate;

    private ImageButton btnTagCamera;
    private ImageButton btnPriceCamera;

    private ImageButton btnAddTrans;



    public View rootView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        editTag = getActivity().findViewById(R.id.editTag);

        editPrice = getActivity().findViewById(R.id.editPrice);


        categorySpinner = getActivity().findViewById(R.id.categorySpinner);

        editDate = getActivity().findViewById(R.id.editDate);


        btnTagCamera = rootView.findViewById(R.id.btnTagCamera);
        btnTagCamera.setOnClickListener(tagListener);


        btnAddTrans = rootView.findViewById(R.id.btnAddTrans);
        btnAddTrans.setOnClickListener(addTransListener);


        //        editPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        //        editPrice.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        //        editDate.setInputType(InputType.TYPE_CLASS_DATETIME);

//        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : "null";
//        Log.d(TAG, " onCreateView: " + greeting);


        return rootView;
    }



    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }



//    // TAG, PRICE, CATEGORY, DATE (+ AUTOGEN ID) -INJECTOR MAKEN

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, " onSaveInstanceState");
        savedInstanceState.putString("greeting", "hello");
        System.out.println("onSaveInstanceState: hello");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : "null";
        Log.d(TAG,  "onViewStateRestored: " + greeting);

        System.out.println("onViewStateRestored: " + greeting);
    }







    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    public View.OnClickListener tagListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.d("CLICK", "btnTagCamera");

//            getActivity().savedInstanceState.putString("cameraFor", cameraFor);

            replaceAddTransactionCameraFragment();
        }
    };


    // ADD INPUT DATA TO SQLDB
        // DATABASE MAKEN
        // TAG, PRICE, CATEGORY, DATE (+ AUTOGEN ID) -INJECTOR MAKEN
        // INJECTOR AANROEPEN IN ADDTRANSLISTENER + RETURN TO TRANSACTIONFRAGMENT (DATA OP TRANSACTTIONFRAGMENT VERVERSEN
        //
    public View.OnClickListener addTransListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            addTransactionEntry();

//            getActivity().setContentView(R.layout.fragment_transactions);
//            replaceAddTransactionCameraFragment();
//            TransactionsFragment transactionsFragment = (TransactionsFragment) getActivity().getSupportFragmentManager().findFragmentByTag("transactions");

            getActivity().onBackPressed();



            // ADD TAG TO DB
            // ADD PRICE TO DB
            // ADD CATEGORY TO DB
            // ADD DAT TO DB

            //(UPDATE TRANSACTION FRAGMENT --> LIST)
            // OPEN TRANSACTION FRAGMENT

//            addTransactionEntry();
        }
    };




    public void addTransactionEntry() {

        String tagString = editTag.getText().toString();
        CharSequence priceChars = editPrice.getText();
        String categorySpinnerString = categorySpinner.toString();
//        CharSequence dateChars = editDate.getText();


        TransactionEntry entry = new TransactionEntry(tagString, priceChars, categorySpinnerString);
        Database db = Database.getDatabase(getActivity().getBaseContext());
        db.insert(entry);

        editTag.setText("");
        editPrice.setText("");

    }

    // OPEN TAG-CAMERA FRAGMENT
    private ScreenShotable replaceAddTransactionCameraFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        AddTransactionCameraFragment addTransactionCameraFragment = new AddTransactionCameraFragment();

        addTransactionCameraFragment.setArguments(cameraForBundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addTransactionCameraFragment).addToBackStack(null)
                .commit();


        return addTransactionCameraFragment;
    }


//    public ScreenShotable replaceAddTransactionFragment() {
//        View view = getActivity().findViewById(R.id.content_frame);
//        int finalRadius = Math.max(view.getWidth(), view.getHeight());
//        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
//        animator.setInterpolator(new AccelerateInterpolator());
//        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//        animator.start();
//        AddTransactionFragment addTransactionFragment = new AddTransactionFragment();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content_frame, addTransactionFragment, "ADDTRANS").addToBackStack(null).commit();
//        return addTransactionFragment;
//    }

}