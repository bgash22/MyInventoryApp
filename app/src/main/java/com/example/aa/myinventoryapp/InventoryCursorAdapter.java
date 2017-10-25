package com.example.aa.myinventoryapp;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.aa.myinventoryapp.data.InventoryContract.InventoryEntry;



import java.io.ByteArrayInputStream;

/**
 * {@link PetCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class InventoryCursorAdapter extends CursorAdapter  {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */

    //private Button buyButton;
    final ContentValues values = new ContentValues();
    private  int quantity;
    private Uri mCurrentPetUri;
    private ContentResolver mContentResolver;
    private Context mContexts;


    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        mContexts = context;
    }


    public static class PetViewHolder {
        public final TextView nameTextView;
        public final TextView quantityTextView;
        public final TextView priceTextView;
        public final Button buyButton;

        public PetViewHolder(View view) {
            nameTextView = (TextView) view.findViewById(R.id.name);
            quantityTextView = (TextView) view.findViewById(R.id.quantity);
            priceTextView = (TextView) view.findViewById(R.id.price);
            buyButton = (Button) view.findViewById(R.id.buyButton);
        }
    }


    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        View view= LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        PetViewHolder petViewHolder = new PetViewHolder(view);
        view.setTag(petViewHolder);
        return view;

    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(final View view, Context context, Cursor cursor) {

        PetViewHolder petViewHolder = (PetViewHolder) view.getTag();

        // Find individual views that we want to modify in the list item layout
        //TextView nameTextView = (TextView) view.findViewById(R.id.name);
        //TextView summaryTextView = (TextView) view.findViewById(R.id.summary);
        //ImageView imageView= (ImageView) view.findViewById(R.id.picture);

        //Button buyButton = (Button) view.findViewById(R.id.buyButton);


        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRICE);

        //int weightColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);

        /*byte[] bb = cursor.getBlob(cursor.getColumnIndex(PetEntry.COLUMN_PET_IMAGE));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bb);
        //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));*/

        // Read the pet attributes from the Cursor for the current pet
        final String inventoryName = cursor.getString(nameColumnIndex);
        final int inventoryQuantity = cursor.getInt(quantityColumnIndex);
        final double inventoryPrice = cursor.getDouble(priceColumnIndex);

        //final int weight = cursor.getInt(cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT));

        final Cursor cursorVal = cursor;
        final Uri mCurrentPetUri =InventoryEntry.CONTENT_URI;



        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(inventoryName)) {
            String petBreed1=inventoryName;
            petBreed1 = context.getString(R.string.unknown_breed);
        }

        // Update the TextViews with the attributes for the current pet
        petViewHolder.nameTextView.setText(inventoryName);
        petViewHolder.quantityTextView.setText(inventoryQuantity);
        petViewHolder.priceTextView.setText(Double.toString(inventoryPrice));

        petViewHolder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.d(LOG_TAG, "Add Button");
                values.clear();
                ContentResolver mContentResolver = view.getContext().getContentResolver();
                //weight = cursor.getInt(cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT));
                if (quantity > 0) {
                    int quantity = cursorVal.getInt(cursorVal.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_QUANTITY));
                    values.put(InventoryEntry.COLUMN_INVENTORY_QUANTITY, --quantity);
                    mContentResolver.update(
                            mCurrentPetUri,
                            values,
                            null,
                            null);
                    mContexts.getContentResolver().notifyChange(mCurrentPetUri, null);
                }
            }
        });
        petViewHolder.buyButton.setFocusable(false);

    }
}
