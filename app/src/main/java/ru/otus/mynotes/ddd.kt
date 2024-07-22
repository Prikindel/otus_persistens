//
//import android.content.ContentProvider
//import android.content.ContentResolver
//import android.content.ContentUris
//import android.content.ContentValues
//import android.content.Context
//import android.database.Cursor
//import android.database.sqlite.SQLiteDatabase
//import android.net.Uri
//import ru.otus.mynotes.NotesApplication
//
//class UserContentProvider : ContentProvider() {
//    private var dbHelper: UserDatabaseHelper? = null
//
//    override fun onCreate(): Boolean {
//        dbHelper = UserDatabaseHelper(context)
//        return true
//    }
//
//    override fun query(
//        uri: Uri,
//        projection: Array<String>?,
//        selection: String?,
//        selectionArgs: Array<String>?,
//        sortOrder: String?
//    ): Cursor? {
//        val db: SQLiteDatabase = dbHelper.getReadableDatabase()
//        val cursor = db.query(
//            UserDatabaseHelper.TABLE_NAME,
//            projection,
//            selection,
//            selectionArgs,
//            null,
//            null,
//            sortOrder
//        )
//        cursor.setNotificationUri(context!!.contentResolver, uri)
//        return cursor
//    }
//
//    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        val db: SQLiteDatabase = dbHelper.getWritableDatabase()
//        val id = db.insert(UserDatabaseHelper.TABLE_NAME, null, values)
//        context!!.contentResolver.notifyChange(uri, null)
//        return ContentUris.withAppendedId(uri, id)
//    }
//
//    override fun update(
//        uri: Uri,
//        values: ContentValues?,
//        selection: String?,
//        selectionArgs: Array<String>?
//    ): Int {
//        val db: SQLiteDatabase = dbHelper.getWritableDatabase()
//        val rowsUpdated = db.update(UserDatabaseHelper.TABLE_NAME, values, selection, selectionArgs)
//        context!!.contentResolver.notifyChange(uri, null)
//        return rowsUpdated
//    }
//
//    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
//        val db: SQLiteDatabase = dbHelper.getWritableDatabase()
//        val rowsDeleted = db.delete(UserDatabaseHelper.TABLE_NAME, selection, selectionArgs)
//        context!!.contentResolver.notifyChange(uri, null)
//        return rowsDeleted
//    }
//
//    override fun getType(uri: Uri): String? {
//        return "vnd.android.cursor.dir/vnd.com.example.userprovider.users"
//    }
//
//    companion object {
//        private const val AUTHORITY = "com.example.userprovider"
//        private val BASE_CONTENT_URI: Uri = Uri.parse("content://" + AUTHORITY)
//        private const val PATH_USERS = "users"
//
//        val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USERS).build()
//    }
//}
//
//class Use {
//    val context: Context
//    fun use() {
//        val contentResolver: ContentResolver = context.contentResolver
//        val uri = UserContentProvider.CONTENT_URI
//// Запрос данных
//        val cursor = contentResolver.query(uri, null, null, null, null)
//// Вставка данных
//        val values = ContentValues()
//        values.put("name", "John Doe")
//        val newUri = contentResolver.insert(uri, values)
//// Обновление данных
//        val rowsUpdated = contentResolver.update(uri, values, "name=?", arrayOf("John Doe"))
//// Удаление данных
//        val rowsDeleted = contentResolver.delete(uri, "name=?", arrayOf("John Doe"))
//    }
//}