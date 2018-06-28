package e.gebruiker.eindproject.fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;

import e.gebruiker.eindproject.R;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.app.Activity.RESULT_OK;

public class AddTransactionCameraFragment extends Fragment implements ScreenShotable {


    protected ImageView imageView;
    private Button snapBtn;
    private Button addTransBtn;
    private TextView txtView;
    private Bitmap imageBitmap;

    protected int res;

    public String detectedText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_transaction_camera, container, false);

        snapBtn = rootView.findViewById(R.id.snapBtn);
        addTransBtn = rootView.findViewById(R.id.addTranstBtn);
        imageView = rootView.findViewById(R.id.imageView);
        txtView = rootView.findViewById(R.id.detectTextView);

        snapBtn.setOnClickListener(snapListener);
        addTransBtn.setOnClickListener(addListener);

        dispatchTakePictureIntent();

        return rootView;
    }

    public View.OnClickListener snapListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("snapBtn", "click");
            dispatchTakePictureIntent();
        }
    };

    public View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddTransactionFragment addTransactionFragment = new AddTransactionFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, addTransactionFragment, "ADDTRANS").addToBackStack(null).commit();

            Bundle tagBundle = new Bundle();
            tagBundle.putString("tag", detectedText);
            addTransactionFragment.setArguments(tagBundle);
            }
    };

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    // opens the camera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // puts image in preview-view
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            detectTxt();
        }
    }

    // detects text in the image
    public void detectTxt() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance().getVisionTextDetector();
        detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                processTxt(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    // saves the detected text in variable and sets text to textView
    private void processTxt(FirebaseVisionText text) {
        List<FirebaseVisionText.Block> blocks = text.getBlocks();
        if (blocks.size() == 0) {
            Toast.makeText(getActivity().getApplicationContext(), "No Text :(", Toast.LENGTH_LONG).show();
            return;
        }
        for (FirebaseVisionText.Block block : text.getBlocks()) {
            String txt = block.getText();
            txtView.setTextSize(24);
            txtView.setText(txt);

            detectedText = txt;
        }
    }

    @Override
    public void takeScreenShot() {
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
