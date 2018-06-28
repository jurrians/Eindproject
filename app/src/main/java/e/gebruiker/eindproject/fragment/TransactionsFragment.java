package e.gebruiker.eindproject.fragment;

import android.database.Cursor;
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
import android.widget.ListView;


import e.gebruiker.eindproject.Database;
import e.gebruiker.eindproject.EntryAdapter;
import e.gebruiker.eindproject.R;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.util.ViewAnimator;

public class TransactionsFragment extends Fragment implements ScreenShotable{

    private View containerView;
    protected int res;

    Database db;
    EntryAdapter adapter;
    ListView listView;

    public AddTransactionFragment addTransactionFragment;

    public static TransactionsFragment newInstance(int resId) {
        TransactionsFragment transactionsFragment = new TransactionsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        transactionsFragment.setArguments(bundle);
        return transactionsFragment;
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
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        final ImageButton addTransactionButton = rootView.findViewById(R.id.buttonAddTransaction);
        addTransactionButton.setOnClickListener(addTransactionListener);

        listView = rootView.findViewById(R.id.transListView);

        db = Database.getDatabase(getActivity().getApplicationContext());
        Cursor cursor = db.selectAll();

        adapter = new EntryAdapter(getContext(), cursor);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
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

    public ScreenShotable replaceAddTransactionFragment() {
        View view = getActivity().findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        animator.start();

        addTransactionFragment = AddTransactionFragment.newInstance(R.layout.fragment_add_transaction);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content_frame, addTransactionFragment, "ADDTRANS")
                .replace(R.id.content_frame, addTransactionFragment).addToBackStack(null).commit();
        return addTransactionFragment;
    }




}



