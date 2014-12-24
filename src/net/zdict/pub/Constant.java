package net.zdict.pub;

public class Constant {
	
	//！！！！！MOS的推送服务地址，打包时，请将此地址修改为部署的推送服务IP地址 fushuang
//	public static String MOS_PUSH_SERVER_XMPP_HOST="133.96.58.95";
//	public static String MOS_PUSH_SERVER_XMPP_HOST="192.168.1.102";
	public static String MOS_PUSH_SERVER_XMPP_HOST="192.168.100.65";
//	public static String MOS_PUSH_SERVER_XMPP_HOST="61.55.166.152";
//	public static String MOS_PUSH_SERVER_XMPP_HOST="10.21.3.32";
	
	public static String MOS_PUSH_APIKEY="1234567891";//没事勿动
	public static String MOS_PUSH_Server_XMPP_PORT="5222";//推送服务监听端口
//	public static String MOS_PUSH_Server_XMPP_PORT="8094";//推送服务监听端口
		
//	public static String ServerURL="http://61.139.140.32:8022/web_mos/";//吉林生产环境地址 
	
//	public static String ServerURL="http://133.128.6.175:10103/web_mos/";//山西测试环境地址 
	
//	public static String ServerURL="http://133.63.250.1:7001/web_mos/";//四川测试环境地址
	
//	public static String ServerURL="http://133.96.58.95:8901/web_mos/";//河北测试环境地址
//	public static String ServerURL="http://61.55.166.152:8901/web_mos/";//河北生产环境地址
	
	//public static String ServerURL="http://134.0.68.134:7013/web_mos/";//内蒙测试环境地址 
//	public static String ServerURL="http://192.168.0.3:9081/web_mos/";//内蒙演示环境
//	public static String ServerURL="http://192.168.0.2:9081/web_mos/";
//	public static String ServerURL="http://134.0.68.134:7003/web_mos/";
	
//	public static String ServerURL="http://192.168.100.185:8080/web_mos/";//公司JBOSS测试环境地址
public static String ServerURL="http://svr985.cattsoft.com:9961/web_mos/";//开发环境地址 
//	public static String ServerURL="http://10.21.1.32:8080/web_mos/";
	
	public static String SORT_ASGN_DATE_ASC="ASGN_DATE ASC";//派单时间升序
	public static String SORT_ASGN_DATE_DESC="ASGN_DATE DESC";//派单时间升序
	public static String SORT_WO_NBR="WO_NBR";//默认工单号排序
	public static String SO_CAT_SPS = "1";//soCat=1为开通单
	public static String SO_CAT_SGS = "2";//soCat=2为障碍单
	public static String MOS_PROVINCE_SHANXI = "SHANXI";//mos使用省份山西
	
	
	/**推送相关常量**/
	public static final String SHARED_PREFERENCE_NAME = "client_preferences";

    // PREFERENCE KEYS

    public static final String CALLBACK_ACTIVITY_PACKAGE_NAME = "CALLBACK_ACTIVITY_PACKAGE_NAME";

    public static final String CALLBACK_ACTIVITY_CLASS_NAME = "CALLBACK_ACTIVITY_CLASS_NAME";

    public static final String API_KEY = "API_KEY";

    public static final String VERSION = "VERSION";

    public static final String XMPP_HOST = "XMPP_HOST";

    public static final String XMPP_PORT = "XMPP_PORT";

    public static final String XMPP_USERNAME = "XMPP_USERNAME";

    public static final String XMPP_PASSWORD = "XMPP_PASSWORD";

    // public static final String USER_KEY = "USER_KEY";

    public static final String DEVICE_ID = "DEVICE_ID";

    public static final String EMULATOR_DEVICE_ID = "EMULATOR_DEVICE_ID";

    public static final String NOTIFICATION_ICON = "NOTIFICATION_ICON";

    public static final String SETTINGS_NOTIFICATION_ENABLED = "SETTINGS_NOTIFICATION_ENABLED";

    public static final String SETTINGS_SOUND_ENABLED = "SETTINGS_SOUND_ENABLED";

    public static final String SETTINGS_VIBRATE_ENABLED = "SETTINGS_VIBRATE_ENABLED";

    public static final String SETTINGS_TOAST_ENABLED = "SETTINGS_TOAST_ENABLED";
    
    public static final String SETTINGS_LOCATION_ENABLED = "SETTINGS_LOCATION_ENABLED";
    
    // NOTIFICATION FIELDS

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";

    public static final String NOTIFICATION_API_KEY = "NOTIFICATION_API_KEY";

    public static final String NOTIFICATION_TITLE = "NOTIFICATION_TITLE";

    public static final String NOTIFICATION_MESSAGE = "NOTIFICATION_MESSAGE";

    public static final String NOTIFICATION_URI = "NOTIFICATION_URI";
    
    public static final String NOTIFICATION_MSG_TYPE = "MSG_TYPE";

    // INTENT ACTIONS

    public static final String ACTION_SHOW_NOTIFICATION = "com.cattsoft.mos.client.SHOW_NOTIFICATION";

    public static final String ACTION_NOTIFICATION_CLICKED = "com.cattsoft.mos.client.NOTIFICATION_CLICKED";

    public static final String ACTION_NOTIFICATION_CLEARED = "com.cattsoft.mos.client.NOTIFICATION_CLEARED";

    //签到功能
    public static final String Is_Signature = "Is_Signature";
    public static final String Auto_Signature = "Auto_Signature";
    
    public static final String YES = "Y";
    public static final String NO = "N";
    
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    
    //资源字典表查询
    public static final String FACTORY = "factory";
    public static final String DEVICE_MODE = "deviceMode";
    public static final String DEVICE_TREATY = "deviceTreaty";
    
    //流量统计开关
    public static final String FLOW_DATA_SWITCH="OFF";
    
 	//材料回填是否采用有单价、总价的版本，Y为采用，N为不采用
    public static final String MATERIAL_SWITCH="N";
    
    public static final String RESOURCE_LINE_TYPE_IN = "配线架-局端-入端子";
    public static final String RESOURCE_LINE_TYPE_OUT = "配线架-局端-出端子";
    
  //配置预约界面是否需要选择网格，如果需要就配置成本省的名称,不需要就不用改
    public static final String MOS_ORDER_SERV="SHANXI";
    
  //履约签到是否需要用客户服务限制的省份
    public static String MOS_PROVINCE_SCHEDULE = "JILIN";//mos使用省份吉林 
}
