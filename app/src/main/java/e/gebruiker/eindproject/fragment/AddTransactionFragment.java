package e.gebruiker.eindproject.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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



    private EditText editTag;
    private EditText editPrice;
    private Spinner categorySpinner;
    private EditText editDate;

    private ImageButton btnTagCamera;
    private ImageButton btnAddTrans;

    public static AddTransactionFragment newInstance(int resId) {
        AddTransactionFragment addTransactionFragment = new AddTransactionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        addTransactionFragment.setArguments(bundle);
        return addTransactionFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        editTag = rootView.findViewById(R.id.editTag);
        editPrice = rootView.findViewById(R.id.editPrice);


        categorySpinner = rootView.findViewById(R.id.categorySpinner);

        editDate = rootView.findViewById(R.id.editDate);


        btnTagCamera = rootView.findViewById(R.id.btnTagCamera);
        btnTagCamera.setOnClickListener(tagListener);

        btnAddTrans = rootView.findViewById(R.id.btnAddTrans);
        btnAddTrans.setOnClickListener(addTransListener);

        // receive tag value from AddTransactionCamerFragment
        Bundle getBundle = getArguments();
        String getTag = getBundle.getString("tag");

        // add tag value to editTag
        if (getTag != null) {
            editTag.setText(getTag);
        }

        editDate.setInputType(InputType.TYPE_CLASS_DATETIME);

        return rootView;
    }

    @Override
    public void takeScreenShot() {}

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    // clickListener for tag-camera-button: redirects user to addTransactionCameraFragment
    public View.OnClickListener tagListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            replaceAddTransactionCameraFragment();
        }
    };

    // clickListener voor add-knop: adds entry + redirects user to transactionsFragment
    public View.OnClickListener addTransListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addTransactionEntry();

            TransactionsFragment transactionsFragment = new TransactionsFragment();

                getFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, transactionsFragment, "TRANS")
                    .addToBackStack(null).commit();
        }
    };

    // adds the data input from the user to the sql-database and sets the text of
    public void addTransactionEntry() {
        String tagString = editTag.getText().toString();
        CharSequence priceChars = editPrice.getText().toString();
        String categorySpinnerString = categorySpinner.toString();

        TransactionEntry entry = new TransactionEntry(tagString, priceChars, categorySpinnerString);
        Database db = Database.getDatabase(getActivity().getBaseContext());
        db.insert(entry);

        editTag.setText("");
    }

    // redirects the user to the camera-fragment
    private ScreenShotable replaceAddTransactionCameraFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        animator.start();

        AddTransactionCameraFragment addTransactionCameraFragment = new AddTransactionCameraFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addTransactionCameraFragment).addToBackStack(null)
                .commit();

        return addTransactionCameraFragment;
    }
}