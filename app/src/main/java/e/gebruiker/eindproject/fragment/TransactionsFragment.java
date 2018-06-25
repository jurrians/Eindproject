package e.gebruiker.eindproject.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;



import e.gebruiker.eindproject.R;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;

public class TransactionsFragment extends Fragment implements ScreenShotable{

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        final ImageButton addTransactionButton = rootView.findViewById(R.id.buttonAddTransaction);
        addTransactionButton.setOnClickListener(addTransactionListener);

        return rootView;
    }


    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    View.OnClickListener addTransactionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            replaceAddTransactionFragment();
        }
    };


    private ScreenShotable replaceAddTransactionFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        AddTransactionFragment addTransactionFragment = new AddTransactionFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addTransactionFragment).addToBackStack(null).commit();
        return addTransactionFragment;
    }


}



