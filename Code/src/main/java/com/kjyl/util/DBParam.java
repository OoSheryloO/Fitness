package com.kjyl.util;

/** 
 * <p>Title: DBParam</p>
 * <p>Description: 参数Util For DB</p>
 * <p>Company: huban hangzhou</p>
 * @author Sheryl
 * @created 2017年9月18日 下午5:54:43
 *  命名前缀：
	byte bt    char c    short sh    int i    long l    char c    string s    
	float f    double d    hashtable h    [] arr    List lst    Vector v    
	StringBuffer sb    Boolean b    Byte bt    Map map    Object ob
 */
public class DBParam {
	// 数据库 参数
/** value：Id */ public static final String sIdKey = "Id";


	/**记录状态*/
	public enum RecordStatus{
		Default{/**默认*/
			public int getCode() {
				return 0;
			}
		},
		Recover{/**恢复*/
			public int getCode() {
				return 1;
			}
		},
		Delete{/**删除*/
			public int getCode() {
				return 88;
			}
		};
		public abstract int getCode();
	}


	public static final String sUIDKey = "UID";//用户ID
	public static final String sDBIDKey = "DBID";//其他唯一ID
	public static final String sUseIDKey = "UseID";//其他用处ID 注意是Use 不是 User
	public static final String sUserNameKey = "name";
	public static final String sUserNumericalOrderKey = "NumericalOrder";//流水号
	public static final String sUserPasswordKey = "password";
	public static final String sRestsPasswordKey = "RestsPassword";
	public static final String sRandomKey = "Rand";
	public static final String sPhoneKey = "phone";
	public static final String sIdentityKey = "identity";
	public static final String sPositionKey = "position";
	public static final String sDateKey = "Time";
	public static final String sDeptIDKey = "DeptId";
	public static final String sTimeMonthKey = "TimeMonth";
	public static final String sPublisherKey = "Publisher";
	public static final String sMoneyKey = "Money";
	public static final String sAddressKey = "Address";
	
	public static final String sUserIDKey = "userId";
	public static final String sBelongIDKey = "belongId";
	public static final String sOtherUserIdKey = "otherUserId";
	
	//筛选条件
	public static final String sConditionKey = "Condition";//条件
	public static final String sKeyWordKey = "KeyWord";//关键词
	public static final String sExcludeKey = "Exclude";//排除
	public static final String sScreenStringKey = "Screen";//筛选
	
	public static final String sMonthTimeKey = "MonthTime";
	public static final String sWeekTimeKey = "WeekTime";
	public static final String sDayTimeKey = "DayTime";
	
	// area 常量
	public static final String sArea_LevelKey = "level";
	public static final String sArea_ParentId = "pid";
	public static final String sArea_CodeKey = "code";
	public static final String sArea_PlaceKey = "place";
	
	/** 编号 常量 */
	public static final String sArea_NumberLikeKey = "NumberLike";
	
	public static final String sSchoolKey = "School";
	public static final String sTeacherKey = "Teacher";
	public static final String sGradeKey = "Grade";
	public static final String sClassKey = "Class";
	
	public static final String sStatusKey = "Status";
	/** value : Type */
	public static final String STypeKey = "Type";
	
	public static final String sUseTypeKey = "useType";
	/** value : type */
	public static final String sTypeKey = "type";
	public static final String sCodeKey = "code";
	/** value : number */
	public static final String sNumberKey = "number";
	/** value : Number */
	public static final String SNumberKey = "Number";
	/** value : value */
	public static final String sValueKey = "value";
	
	public static final String sLimitKey = "Limit";
	
	public static final String sStartKey = "start";
	
	
	
}