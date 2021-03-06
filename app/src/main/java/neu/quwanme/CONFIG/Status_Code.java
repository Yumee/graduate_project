package neu.quwanme.CONFIG;

/**
 * Created by Lonie233 on 2016/3/19.
 * 代表一些状态值
 */
public class Status_Code {

    /**
     * 返回的gson串中的key值
     */
    public final static String Status_Code = "status_code";

    //	系统设定的一些状态量
    public final static int SYSTEM_SUCCESS_STATUS = 0 ;
    public final static int SYSTEM_NOT_EXIST_STATUS = 1 ;
    public final static int SYSTEM_EXCEPTION_STATUS = 2 ;
    public final static int SYSTEM_UPDATE_FAILED = 1 ;


    //	service 层返回的状态量
    public final static int SUCCESS_STATUS = 0 ;//操作成功 增删改查
    public final static int NOT_EXIST_STATUS = 1 ;//对象不存在 删除，更新，查找
    public final static int EXCEPTION_STATUS = 2 ;//操作异常
    public final static int UPDATE_FAILED = 1 ;//更新失败
    public final static int FAILED = 1;//插入失败,更新失败，删除失败，通用的操作失败状态码

    public final static int LOGIN_FAILED = 1;//登录失败，用户名密码错误

    //	object（数据表中不存在） 不存在,返回此值  或将此值设为id
    public static int OBJECT_NOT_EXIST = -1 ;


}
