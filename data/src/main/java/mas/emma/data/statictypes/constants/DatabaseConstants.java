/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.statictypes.constants;

/**
 *
 * @author nlmsc
 */
public class DatabaseConstants {

    public final static String CUSTOMPREFIX = "custom";
    //<editor-fold defaultstate="collapsed" desc="Tables">
    public final static String USERSTBL = "UserMaster";
    public final static String CONVERSATIONTBL = "Conversation";
    public final static String GROUPMEMBERTBL = "GroupMember";
    public final static String TOPICGROUPTBL = "TopicGroup";
    public final static String USERDETAILSTBL = "UserDetail";
    public final static String AUTHTBL = "Auth";
    public final static String APPAUTHTBL = "AppAuth";
    public final static String ORGANISATIONTBL = "Organisation";
    public final static String ROLETBL = "EmmaRole";
    public final static String USERMASTERORGANISATIONTBL = "UserMasterOrganisation";
    public final static String MESSAGETBL = "Message";
    public final static String GROUPMESSAGETBL = "GROUP_MESSAGE";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User_Master columns">
    public final static String USER_ID = "id";
    public final static String USER_FIRST_NAME = "first_name";
    public final static String USER_LAST_NAME = "last_name";
    public final static String USER_EMAIL = "email";
    public final static String USER_ADMIN = "administrator";
    public final static String USER_PROFILE_TYPE = "profile_type";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Usersdetails columns">
    public final static String USERDETAILS_ID = "id";
    public final static String USERDETAILS_BIRTHDAY = "birthday";
    public final static String USERDETAILS_STREET_NAME = "street_name";
    public final static String USERDETAILS_HOUSE_NUMBER = "house_number";
    public final static String USERDETAILS_ZIP = "zip";
    public final static String USERDETAILS_CITY = "city";
    public final static String USERDETAILS_PHONENUMBER = "phonenumber";
    public final static String USERDETAILS_PROFILE_STATUS = "profile_status";
    public final static String USERDETAILS_LAST_SEEN = "last_seen";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Auth columns">
    public final static String AUTH_ID = "id";
    public final static String AUTH_AUTH = "auth";
    public final static String AUTH_KEY = "auth_key";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AppAuth columns">
    public final static String APP_AUTH_ID = "id";
    public final static String APP_AUTH_NAME = "application_name";
    public final static String APP_AUTH_GUID = "guid";
    public final static String APP_AUTH_EMAIL = "email";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Organisation columns">
    public final static String ORGANISATION_ID = "org_id";
    public final static String ORGANISATION_NAME = "organisation_name";
    public final static String ORGANISATION_GUID = "guid";
    public final static String ORGANISATION_EMAIL = "email";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Role columns">
    public final static String ROLE_ID = "row_id";
    public final static String ROLE_ROLE_ID = "role_id";
    public final static String ROLE_ORGANISATION_ID = "organisation_id";
    public final static String ROLE_USER_ID = "user_id";
    public final static String ROLE_NAME = "role_name";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User Master Organisation columns">
    public final static String USERMASTERORG_ROW_ID = "row_id";
    public final static String USERMASTERORG_UID = "uid";
    public final static String USERMASTERORG_ORG_ID = "org_id";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Groupmember columns">
    public final static String GROUPMEMBER_ID = "id";
    public final static String GROUPMEMBER_GROUP_ID = "group_id";
    public final static String GROUPMEMBER_USER_ID = "user_id";
    public final static String GROUPMEMBER_IS_ADMINISTRATOR = "is_administrator";
    public final static String GROUPMEMBER_JOINED_ON = "joined_on";
    public final static String GROUPMEMBER_LAST_SEEN = "last_seen";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Group columns">
    public final static String GROUP_ID = "id";
    public final static String GROUP_GROUP_ID = "group_id";
    public final static String GROUP_CREATED_BY = "created_by";
    public final static String GROUP_CREATED_ON = "created_on";
    public final static String GROUP_GROUP_NAME = "group_name";
    public final static String GROUP_ORGANISATION_ID = "organisation_id";
    public final static String GROUP_EXTERNAL_ACCESSIBLE = "is_external_accessible";
    public final static String GROUP_PUBLIC = "is_public";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="SingleMessage columns">
    public final static String MESSAGE_ID = "id";
    public final static String MESSAGE_SENDER = "sender";
    public final static String MESSAGE_SEND_TO = "send_to";
    public final static String MESSAGE_SEND_DATE = "send_date";
    public final static String MESSAGE_READ_ON = "read_on";
    public final static String MESSAGE_BODY = "body";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="GroupMessage columns">
    public final static String GROUP_MESSAGE_ID = "id";
    public final static String GROUP_MESSAGE_SENDER = "sender";
    public final static String GROUP_MESSAGE_SEND_TO = "send_to";
    public final static String GROUP_MESSAGE_SEND_DATE = "send_date";
    public final static String GROUP_MESSAGE_READ_ON = "read_on";
    public final static String GROUP_MESSAGE_BODY = "body";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Conversation columns">
    public final static String CONVERSATION_ID = "id";
    public final static String CONVERSATION_USER_ID = "user_id";
    public final static String CONVERSATION_SEND_TO = "send_to";

    //</editor-fold>
}
