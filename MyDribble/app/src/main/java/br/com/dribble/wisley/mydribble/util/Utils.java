package br.com.dribble.wisley.mydribble.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wisley on 10/09/17.
 */

public class Utils {

    public static final String JSON_DATE_FORMAT = "dd/MM/yyyy - hh:mm";
    private static final String VALID_DATE = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d{2}$";

    /**
     * @param jsonObject Object
     * @param nameChild  Child
     * @return True if is valid
     */
    public static boolean isJsonObject(JSONObject jsonObject, String nameChild) {
        try {
            jsonObject.getJSONObject(nameChild);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}
