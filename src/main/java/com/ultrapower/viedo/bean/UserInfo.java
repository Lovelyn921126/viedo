package com.ultrapower.viedo.bean;



/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String unicomId;
     private String userName;
     private String userPassword;
     private String userNick;
     private Short userType;
     private Short userGender;
     private Short userAge;
     private String userHeaderImage;
     private String userMidHeaderImage;
     private String userSmallHeaderImage;
     private String userEmail;
     private String userNationId;
     private Integer userProvinceId;
     private Integer userCompanyId;
     private String userCompanyName;
     private Integer userPositionId;
     private String userPositionName;
     private Integer userExperience;
     private Integer userLevel;
     private Integer userIntegral;
     private Integer userMedalCount;
     private Integer isNewUser;
     private Short status;
     private String userInfoDepartmentName;
//     private String userEmail1(del);
     private String userOrgCode;
     private Short userSource;
     private String userPhoneNum;
     private String userPassWordUltrapower;
     private Short userSpecialType;


    // Constructors

    /** default constructor */
    public UserInfo() {
    }

	/** minimal constructor */
    public UserInfo(String unicomId, String userName, String userNick, Short userType, Short userGender, Short userAge, String userHeaderImage, String userNationId, Integer userProvinceId, Integer userCompanyId, Integer userPositionId, Integer userExperience, Integer userLevel, Integer userIntegral, Integer userMedalCount, Short status, Short userSpecialType) {
        this.unicomId = unicomId;
        this.userName = userName;
        this.userNick = userNick;
        this.userType = userType;
        this.userGender = userGender;
        this.userAge = userAge;
        this.userHeaderImage = userHeaderImage;
        this.userNationId = userNationId;
        this.userProvinceId = userProvinceId;
        this.userCompanyId = userCompanyId;
        this.userPositionId = userPositionId;
        this.userExperience = userExperience;
        this.userLevel = userLevel;
        this.userIntegral = userIntegral;
        this.userMedalCount = userMedalCount;
        this.status = status;
        this.userSpecialType = userSpecialType;
    }
    
    /** full constructor */
//    public UserInfo(String unicomId, String userName, String userPassword, String userNick, Short userType, Short userGender, Short userAge, String userHeaderImage, String userMidHeaderImage, String userSmallHeaderImage, String userEmail, String userNationId, Integer userProvinceId, Integer userCompanyId, String userCompanyName, Integer userPositionId, String userPositionName, Integer userExperience, Integer userLevel, Integer userIntegral, Integer userMedalCount, Integer isNewUser, Short status, String userInfoDepartmentName, String userEmail1(del), String userOrgCode, Short userSource, String userPhoneNum, String userPassWordUltrapower, Short userSpecialType) {
//        this.unicomId = unicomId;
//        this.userName = userName;
//        this.userPassword = userPassword;
//        this.userNick = userNick;
//        this.userType = userType;
//        this.userGender = userGender;
//        this.userAge = userAge;
//        this.userHeaderImage = userHeaderImage;
//        this.userMidHeaderImage = userMidHeaderImage;
//        this.userSmallHeaderImage = userSmallHeaderImage;
//        this.userEmail = userEmail;
//        this.userNationId = userNationId;
//        this.userProvinceId = userProvinceId;
//        this.userCompanyId = userCompanyId;
//        this.userCompanyName = userCompanyName;
//        this.userPositionId = userPositionId;
//        this.userPositionName = userPositionName;
//        this.userExperience = userExperience;
//        this.userLevel = userLevel;
//        this.userIntegral = userIntegral;
//        this.userMedalCount = userMedalCount;
//        this.isNewUser = isNewUser;
//        this.status = status;
//        this.userInfoDepartmentName = userInfoDepartmentName;
//        this.userEmail1(del) = userEmail1(del);
//        this.userOrgCode = userOrgCode;
//        this.userSource = userSource;
//        this.userPhoneNum = userPhoneNum;
//        this.userPassWordUltrapower = userPassWordUltrapower;
//        this.userSpecialType = userSpecialType;
//    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnicomId() {
        return this.unicomId;
    }
    
    public void setUnicomId(String unicomId) {
        this.unicomId = unicomId;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNick() {
        return this.userNick;
    }
    
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Short getUserType() {
        return this.userType;
    }
    
    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Short getUserGender() {
        return this.userGender;
    }
    
    public void setUserGender(Short userGender) {
        this.userGender = userGender;
    }

    public Short getUserAge() {
        return this.userAge;
    }
    
    public void setUserAge(Short userAge) {
        this.userAge = userAge;
    }

    public String getUserHeaderImage() {
        return this.userHeaderImage;
    }
    
    public void setUserHeaderImage(String userHeaderImage) {
        this.userHeaderImage = userHeaderImage;
    }

    public String getUserMidHeaderImage() {
        return this.userMidHeaderImage;
    }
    
    public void setUserMidHeaderImage(String userMidHeaderImage) {
        this.userMidHeaderImage = userMidHeaderImage;
    }

    public String getUserSmallHeaderImage() {
        return this.userSmallHeaderImage;
    }
    
    public void setUserSmallHeaderImage(String userSmallHeaderImage) {
        this.userSmallHeaderImage = userSmallHeaderImage;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNationId() {
        return this.userNationId;
    }
    
    public void setUserNationId(String userNationId) {
        this.userNationId = userNationId;
    }

    public Integer getUserProvinceId() {
        return this.userProvinceId;
    }
    
    public void setUserProvinceId(Integer userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    public Integer getUserCompanyId() {
        return this.userCompanyId;
    }
    
    public void setUserCompanyId(Integer userCompanyId) {
        this.userCompanyId = userCompanyId;
    }

    public String getUserCompanyName() {
        return this.userCompanyName;
    }
    
    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }

    public Integer getUserPositionId() {
        return this.userPositionId;
    }
    
    public void setUserPositionId(Integer userPositionId) {
        this.userPositionId = userPositionId;
    }

    public String getUserPositionName() {
        return this.userPositionName;
    }
    
    public void setUserPositionName(String userPositionName) {
        this.userPositionName = userPositionName;
    }

    public Integer getUserExperience() {
        return this.userExperience;
    }
    
    public void setUserExperience(Integer userExperience) {
        this.userExperience = userExperience;
    }

    public Integer getUserLevel() {
        return this.userLevel;
    }
    
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getUserIntegral() {
        return this.userIntegral;
    }
    
    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public Integer getUserMedalCount() {
        return this.userMedalCount;
    }
    
    public void setUserMedalCount(Integer userMedalCount) {
        this.userMedalCount = userMedalCount;
    }

    public Integer getIsNewUser() {
        return this.isNewUser;
    }
    
    public void setIsNewUser(Integer isNewUser) {
        this.isNewUser = isNewUser;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public String getUserInfoDepartmentName() {
        return this.userInfoDepartmentName;
    }
    
    public void setUserInfoDepartmentName(String userInfoDepartmentName) {
        this.userInfoDepartmentName = userInfoDepartmentName;
    }

//    public String getUserEmail1(del)() {
//        return this.userEmail1(del);
//    }
//    
//    public void setUserEmail1(del)(String userEmail1(del)) {
//        this.userEmail1(del) = userEmail1(del);
//    }

    public String getUserOrgCode() {
        return this.userOrgCode;
    }
    
    public void setUserOrgCode(String userOrgCode) {
        this.userOrgCode = userOrgCode;
    }

    public Short getUserSource() {
        return this.userSource;
    }
    
    public void setUserSource(Short userSource) {
        this.userSource = userSource;
    }

    public String getUserPhoneNum() {
        return this.userPhoneNum;
    }
    
    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserPassWordUltrapower() {
        return this.userPassWordUltrapower;
    }
    
    public void setUserPassWordUltrapower(String userPassWordUltrapower) {
        this.userPassWordUltrapower = userPassWordUltrapower;
    }

    public Short getUserSpecialType() {
        return this.userSpecialType;
    }
    
    public void setUserSpecialType(Short userSpecialType) {
        this.userSpecialType = userSpecialType;
    }
   








}