package neu.quwanme.CONFIG;

/**
 * Created by Lonie233 on 2016/4/11.
 * sp存储类，用于存取一些全局变量
 */
public class Symbols {

    public static final String citylist = "citylist";
    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String from = "from";

    /************************user main****************************/
    public static final String fromLastestAty = "fromLastestAty";
    public static final String fromRecomAty = "fromRecomAty";
    public static final String fromMyAty = "fromMyAty";
    public static final String fromMyAtyHis = "fromMyAtyHis";


    /************************shop****************************/
    public static final String shop = "shop";

    public static final String shopId = "shopId";

    public static final  String shopName = "shopName";

    public static final  String shopCity = "shopCity";

    public static final  String shopType = "shopType";

    public static final  String shopPassword = "shopPassword";

    public static final  String fromShopRank = "fromShopRank";

    public static final  String fromShopMain = "fromShopMain";

    /************************shop****************************/
    
    /************************user****************************/
    public static final String user = "user";

    public static final String userId = "userId";

    public static final String userNumber = "userNumber";

    public static final String userRealName = "userRealName";

    public static final String userNickName = "userNickName";

    public static final String userPassword = "userPassword";

    public static final String userAge = "userAge";

    public static final String userSex = "userSex";

    /************************user****************************/

    /************************activity****************************/

    public final static String curTime ="curTime" ;
    public final static String startTime ="startTime" ;
    public final static String endTime ="endTime" ;
    public final static String cityName ="cityName" ;
    public final static String startPos ="startPos" ;
    public final static String endPos ="endPos" ;
    public final static String activityId ="activityId" ;

    public final static String ListType ="listtype" ;

    public final static int AllAty = 1 ;
    public final static int OrgIng = 2 ;//正在组团
    public final static int Orged = 3 ;//组团完毕
    public final static int Finish = 4 ;//活动结束
    public final static int Ing = 5 ;//正在进行的活动

    public final static int Not_Start = 0 ;//未开的活动
    public final static int Strated = 1 ;//正在进行的活动
    public final static int Finished = 2 ;//已结束的活动

    public static final String typelist = "typelist";

    public static final String IsFromOtherAty = "IsFromOtherAty" ;//区分是我的活动，还是他人的活动，shop/user 公用

    /************************activity****************************/


}
