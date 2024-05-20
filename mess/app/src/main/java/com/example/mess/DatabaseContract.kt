package com.example.mess
// DatabaseContract.kt

import android.provider.BaseColumns

object DatabaseContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "students"
            const val COLUMN_ID = "id"
            const val COLUMN_NAME = "name"
            const val COLUMN_CMSID = "cmsId"
            const val COLUMN_PASSWORD = "password"

        }
    }

    object MenuEntry : BaseColumns {
        const val TABLE_NAME = "menu"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_DAY_OF_WEEK = "dayOfWeek"
    }
}
