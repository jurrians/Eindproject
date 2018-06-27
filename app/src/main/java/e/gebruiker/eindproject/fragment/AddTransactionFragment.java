package e.gebruiker.eindproject.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

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

import e.gebruiker.eindproject.MainActivity;
import e.gebruiker.eindproject.R;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.app.Activity.RESULT_OK;


public class AddTransactionFragment extends Fragment implements ScreenShotable {


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;




    public String cameraFor;

    public Bundle cameraForBundle;


    private Bundle savedState = null;


    private EditText editTag;
    private EditText editPrice;
    private Spinner categorySpinner;
    private EditText editDate;

    private ImageButton btnTagCamera;
    private ImageButton btnPriceCamera;

    private ImageButton btnAddTrans;



    ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//
//        if (savedInstanceState == null) {
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);
//        } else {
//
//        }

        // WELLICHT OOK ALLEMAAL IN SAVEDSTATE OPSLAAN
        editTag = getActivity().findViewById(R.id.btnTagCamera);
        editPrice = getActivity().findViewById(R.id.editPrice);
        categorySpinner = getActivity().findViewById(R.id.categorySpinner);
        editDate = getActivity().findViewById(R.id.editDate);

        btnTagCamera = rootView.findViewById(R.id.btnTagCamera);
        btnTagCamera.setOnClickListener(tagListener);

//        btnPriceCamera = rootView.findViewById(R.id.btnPriceCamera);
//        btnPriceCamera.setOnClickListener(priceListener);

        btnAddTrans = rootView.findViewById(R.id.btnAddTrans);
        btnAddTrans.setOnClickListener(addTransListener);






//        if (savedInstanceState != null && savedState == null) {
////            savedState = savedInstanceState.getBundle("");
//        }                                            // App.STAV
//
//        if (savedState != null) {
//            // vstup = textView
////            vstup.setText(savedState.getCharSequence(""));
//        }                                                 // App.VSTUP

//        savedState = null;

        return rootView;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        savedState = saveState();
////        vstup = null;
//    }
//    // TAG, PRICE, CATEGORY, DATE (+ AUTOGEN ID) -INJECTOR MAKEN
//    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
//        Bundle state = new Bundle();
//
//        String tag = editTag.getText().toString();
//        state.putString("tag", tag);
//        String price = editPrice.getText().toString();
//        state.putString("price", price);
////        String category = categorySpinner.getTransitionName();
////        state.putString("category", category); // ???
//        CharSequence date = editDate.getText();
//        state.putCharSequence("date", date);
//
////        state.putCharSequence(App.VTSUP, vstup.getText());
//
//
//        return state;
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        /* If onDestroyView() is called first, we can use the previously savedState but we can't call saveState() anymore */
//        /* If onSaveInstanceState() is called first, we don't have savedState, so we need to call saveState() */
//        /* => (?:) operator inevitable! */
//
//        editTag = getActivity().findViewById(R.id.btnTagCamera);
//        editPrice = getActivity().findViewById(R.id.editPrice);
//        categorySpinner = getActivity().findViewById(R.id.categorySpinner);
//        editDate = getActivity().findViewById(R.id.editDate);
//
//        btnTagCamera = getActivity().findViewById(R.id.btnTagCamera);
//        btnTagCamera.setOnClickListener(tagListener);
//
////        btnPriceCamera = getActivity().findViewById(R.id.btnPriceCamera);
////        btnPriceCamera.setOnClickListener(priceListener);
//
//        btnAddTrans = getActivity().findViewById(R.id.btnAddTrans);
//        btnAddTrans.setOnClickListener(addTransListener);
//
//
//        //        outState.putBundle(App.STAV, (savedState != null) ? savedState : saveState());
//
//
//        if (outState.getString("tag") != null) {
//            String tag = outState.getString("tag");
//            editTag.setText(tag);
//
//        }
//        if (outState.getString("price") != null) {
//            String price = outState.getString("price");
//            editPrice.setText(price);
//
//        }
//
////        String category = outState.getString("category");
////        categorySpinner.
//
//        if (outState.getCharSequence("date") != null) {
//            CharSequence date = outState.getCharSequence("date");
//            editDate.setText(date);
//        }
//    }

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
            cameraFor = "TAG";
//            getActivity().savedInstanceState.putString("cameraFor", cameraFor);

            cameraForBundle = new Bundle();
            cameraForBundle.putString("cameraFor", cameraFor);


            replaceAddTransactionCameraFragment();
        }
    };

    public View.OnClickListener priceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraFor = "PRICE";
            Log.d("click", "priceListener");

            cameraForBundle = new Bundle();
            cameraForBundle.putString("cameraFor", cameraFor);

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

        }
    };



    // OPEN TAG-CAMERA FRAGMENT
    private ScreenShotable replaceAddTransactionCameraFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        AddTransactionCameraFragment addTransactionCameraFragment = new AddTransactionCameraFragment();

//        Bundle cameraForBundle = new Bundle();
//        cameraForBundle.putString("cameraFor", cameraFor);
//
        addTransactionCameraFragment.setArguments(cameraForBundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addTransactionCameraFragment).addToBackStack(null)
                .commit();

//        getActivity().getSupportFragmentManager().beginTransaction();


        return addTransactionCameraFragment;
    }



}