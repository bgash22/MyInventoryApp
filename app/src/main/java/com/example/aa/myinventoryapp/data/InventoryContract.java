package com.example.aa.myinventoryapp.data;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

/**
 * API Contract for the Inventorys app.
 */
public final class InventoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private InventoryContract() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.aa.myinventoryapp";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.inventories/inventories/ is a valid path for
     * looking at inventory data. content://com.example.android.inventories/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_INVENTORIES = "inventory";

    /**
     * Inner class that defines constant values for the inventories database table.
     * Each entry in the table represents a single inventory.
     */
    public static final class InventoryEntry implements BaseColumns {

        /** The content URI to access the inventory data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORIES);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of inventories.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORIES;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single inventory.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORIES;

        /** Name of database table for inventories */
        public final static String TABLE_NAME = "inventory";

        /**
         * Unique ID number for the inventory (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Image URI of the inventory
         *
         * Type: TEXT
         */
        public final static String COLUMN_INVENTORY_IMAGE ="image";


        /**
         * Name of the inventory.
         *
         * Type: TEXT
         */
        public final static String COLUMN_INVENTORY_NAME ="name";

        /**
         * Breed of the inventory.
         *
         * Type: TEXT
         */
        //public final static String COLUMN_INVENTORY_DESCRIPTION = "description";

        /**
         * Breed of the inventory.
         *
         * Type: TEXT
         */
        public final static String COLUMN_INVENTORY_QUANTITY = "quantity";

        /**
         * Name of the supplier.
         *
         * Type: TEXT
         */
        public final static String COLUMN_INVENTORY_SUPPLIER ="supplier";

        /**
         * Price of the inventory.
         *
         * Type: DOUBLE
         */
        public final static String COLUMN_INVENTORY_PRICE = "price";


        /**
         * Type of the inventory.
         *
         * The only possible values are {@link #TYPE_ONEAR}, {@link #TYPE_INEAR},
         * or {@link #TYPE_OVEREAR}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_INVENTORY_TYPE = "type";

        /**
         * Possible values for the type of the inventory.
         */
        public static final int TYPE_ONEAR = 0;
        public static final int TYPE_INEAR = 1;
        public static final int TYPE_OVEREAR = 2;

        /**
         * Returns whether or not the given type is {@link #TYPE_ONEAR}, {@link #TYPE_INEAR},
         * or {@link #TYPE_OVEREAR}.
         */
        public static boolean isValidType(int type) {
            if (type == TYPE_ONEAR || type == TYPE_INEAR || type == TYPE_OVEREAR) {
                return true;
            }
            return false;
        }
    }

}

