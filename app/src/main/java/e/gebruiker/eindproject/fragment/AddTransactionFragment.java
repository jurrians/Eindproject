package e.gebruiker.eindproject.fragment;

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
import android.widget.ImageButton;
import android.widget.ImageView;
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        final ImageButton tagButton = rootView.findViewById(R.id.btnTagCamera);
        tagButton.setOnClickListener(tagListener);
        final ImageButton priceButton = rootView.findViewById(R.id.btnAddTrans);
        priceButton.setOnClickListener(priceListener);

//        final ImageButton addTransactionButton = (ImageButton) rootView.findViewById(R.id.buttonAddTransaction);
//        addTransactionButton.setOnClickListener(addTransactionListener);

        return rootView;
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
            cameraFor = "TAG";
            replaceAddTransactionCameraFragment();
        }
    };

    public View.OnClickListener priceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraFor = "PRICE";
            replaceAddTransactionCameraFragment();
        }
    };



    // OPEN TAG-CAMERA FRAGMENT
    private void replaceAddTransactionCameraFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        AddTransactionCameraFragment addTransactionCameraFragment = new AddTransactionCameraFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addTransactionCameraFragment).addToBackStack(null).commit();
    }
}