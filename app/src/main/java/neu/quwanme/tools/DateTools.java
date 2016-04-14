package neu.quwanme.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import neu.quwanme.CONFIG.Symbols;

/**
 * Created by Lonie233 on 2016/4/14.
 */
public class DateTools {

    public static String parseDate(Date d){
        SimpleDateFormat s = new SimpleDateFormat(Symbols.dateFormat);
        return s.format(d);
    }

}
