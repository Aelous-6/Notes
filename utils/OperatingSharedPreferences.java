public class OperatingSharedPreferences {

    /**
     * <pre>
     * 基本功能：保存String类型数据到SharedPreferences
     *
     * @param context
     * @param name
     * @param key
     * @param value   </pre>
     */

    public static void setString(Context context,
                                 String name, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                name, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();// 获取编辑器
        editor.putString(key, value);
        editor.commit();// 提交修改
    }


    /**
     * <pre>
     * 基本功能：取得SharedPreferences中存储的String类型数据
     *
     * @param context
     * @param name
     * @param key
     * @return </pre>
     */
    public static String getString(Context context,
                                   String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                name, Context.MODE_PRIVATE);
        String signature = sharedPreferences.getString(key, "");
        return signature;
    }

    /**
     * <pre>
     * 基本功能：存储的Int类型数据到SharedPreferences
     *
     * @param context
     * @param name
     * @param key
     * @return </pre>
     */
    public static void setInt(Context context,
                              String name, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                name, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();// 获取编辑器
        editor.putInt(key, value);
        editor.commit();// 提交修改
    }

    /**
     * <pre>
     * 基本功能：取得SharedPreferences中存储的Int类型数据
     *
     * @param context
     * @param name
     * @param key
     * @return </pre>
     */
    public static int getInt(Context context,
                             String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                name, Context.MODE_PRIVATE);
        int signature = sharedPreferences.getInt(key, 0);
        return signature;
    }
}
