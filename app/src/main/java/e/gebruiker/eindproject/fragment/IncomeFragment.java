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
import android.widget.ImageView;



import e.gebruiker.eindproject.R;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;

public class IncomeFragment extends Fragment implements ScreenShotable{

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_income, container, false);

        final FloatingActionButton addIncomeButton = (FloatingActionButton) rootView.findViewById(R.id.floatingIncomeButton);

        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click", "click");
                replaceAddIncomeFragment();
            }
        });



        return rootView;
    }



    private ScreenShotable replaceAddIncomeFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        animator.start();
        AddIncomeFragment addIncomeFragment = new AddIncomeFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, addIncomeFragment).addToBackStack(null).commit();
        return addIncomeFragment;
    }


    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}

