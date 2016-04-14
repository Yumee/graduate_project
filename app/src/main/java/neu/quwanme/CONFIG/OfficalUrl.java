package neu.quwanme.CONFIG;

/**
 * Created by Lonie233 on 2016/3/16.
 * url列表
 */
public class OfficalUrl {

    public final static String baseUrl = "http://192.168.12.72:8081/quwanmeserver/";

    public final static String testUrl = "http://192.168.13.44:8081/quwanmeserver/userAction/androidQueryUser";

    public final static String StudentLoginUrl = "userAction/userLogin" ;

    public final static String ShopLoginUrl = "shopAction/shopLogin" ;

    public final static String StudentResgistUrl = "userAction/androidCreateUser";

    public final static String ShopResgistUrl = "shopAction/androidCreateShop";

    public final static String SchoolListUrl ="schoolAction/getAllSchools";

    public final static String CityListUrl ="shopAction/getAllCity";


    /*****************************************activity related url*****************************************************/
    public final static String AtyBaseUrl = "activityAction/" ;
    public final static String TypeListUrl ="getAllType";

    public final static String CreateAtyUrl = "androidCreateActivity";

    public final static String GetLastestActivity = "getLastestActivity";

    public final static String GetAllActivity = "getAllAty";

    public final static String GetOrganizingActivity = "getAtyNotReady";

    public final static String GetgetOrganizedOverActivity = "getAtyReady";

    public final static String GetFinishActivity = "getAtyFinsh";



    /*****************************************activity related url*****************************************************/


}
