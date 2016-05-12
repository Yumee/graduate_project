package neu.quwanme.CONFIG;

/**
 * Created by Lonie233 on 2016/3/16.
 * url列表
 */
public class OfficalUrl {

    public final static String baseUrl = "http://192.168.23.1:8080/quwanmeserver/";

    public final static String testUrl = "http://192.168.13.44:8081/quwanmeserver/userAction/androidQueryUser";


    public final static String SchoolListUrl ="schoolAction/getAllSchools";

    /*****************************************shop related url*****************************************************/

    public final static String ShopLoginUrl = "shopAction/shopLogin" ;

    public final static String ShopResgistUrl = "shopAction/androidCreateShop";

    public final static String ShopUpdatetUrl = "shopAction/androidUpdateShop";

    public final static String getShopByRank = "shopAction/getShopByRank";

    public final static String CityListUrl ="shopAction/getAllCity";

    /*****************************************shop related url*****************************************************/

    /*****************************************activity related url*****************************************************/
    public final static String AtyBaseUrl = "activityAction/" ;
    public final static String TypeListUrl ="getAllType";

    public final static String CreateAtyUrl = "androidCreateActivity";

    public final static String UpdateAtyUrl = "androidUpdateActivity";

    public final static String DeleteAtyUrl = "androidDeleteActivity";

    public final static String GetLastestActivity = "getLastestActivity";

    public final static String GetAllActivity = "getAllAty";

    public final static String GetOrganizingActivity = "getAtyNotReady";

    public final static String GetgetOrganizedOverActivity = "getAtyReady";

    public final static String GetFinishActivity = "getAtyFinsh";
    /***************************************************************************/
    public final static String GetLastestActivityNotMe = "getLastestActivityNotMe";

    public final static String GetAllActivityNotMe = "getAllAtyNotMe";

    public final static String GetOrganizingActivityNotMe = "getAtyNotReadyNotMe";

    public final static String GetgetOrganizedOverActivityNotMe= "getAtyReadyNotMe";

    public final static String GetFinishActivityNotMe = "getAtyFinshNotMe";
    /*******************************************************************************/



    public final static String GetUserLaestAty = "getUserLastestAty";

    public final static String GetUserAty = "getUserAty";

    public final static String GetUserHisAty = "getUserHisAty";


    /*****************************************activity related url*****************************************************/


    /*****************************************user related url*****************************************************/
    public final static String UserBaseUrl = "userAction/";

    public final static String StudentLoginUrl = "userAction/userLogin" ;

    public final static String StudentResgistUrl = "userAction/androidCreateUser";

    public final static String UserJoinAtyUrl = "userJoin";

    public final static String UserQuitAtyUrl = "userQuit";

    public final static String UserUpdataInfo ="updateUser" ;



    /*****************************************user related url*****************************************************/




}
