package e.gebruiker.eindproject;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor c) {
        super(context, R.layout.transaction_entry_row, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {

        // apply data to views in transaction_entry_row

        // tag
        TextView textViewTag = view.findViewById(R.id.tagTransList);
        String tag = c.getString(c.getColumnIndex(Database.columnTag));
        textViewTag.setText(tag);

        // price
        TextView textViewPrice = view.findViewById(R.id.priceTransList);
        String price = c.getString(c.getColumnIndex(Database.columnPrice));
        textViewPrice.setText(price);

        // category
        TextView textViewCategory = view.findViewById(R.id.categoryTransList);
        String category = c.getString(c.getColumnIndex(Database.columnCategory));
        textViewCategory.setText(category);

        // date
        TextView textViewDate = view.findViewById(R.id.dateTransList);
        String date = c.getString(c.getColumnIndex(Database.columnDate));
        textViewDate.setText(date);
    }
}
