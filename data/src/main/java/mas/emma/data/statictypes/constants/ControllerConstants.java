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
public class ControllerConstants {

    //<editor-fold defaultstate="collapsed" desc="Paths ">
    public static final String USERPATH = "/users";
    public static final String CHATMESSAGEPATH = "/chatmessage";
    public static final String GROUPMEMBERPATH = "/groupmember";
    public static final String TOPICGROUPPATH = "/topicgroup";
    public static final String GROUPMESSAGEPATH = "/groupmessage";
    public static final String CONVERSATIONPATH = "/conversation";
    public static final String USERDETAILSPATH = "/userdetails";
    public static final String APPAUTHPATH = "/appauth";
    public static final String ORGANISATIONPATH = "/organisation";
    public static final String AUTHPATH = "/auth";
    public static final String ROLEPATH = "/role";
    public static final String USERMASTERORGANISATIONPATNH = "/usermasterorganisation";

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Keywords ">
    public static final String ID = "id";
    public static final String ITRBL = "itrbl";
    public static final String ORGANISATION_ID = "organisation_id";
    public static final String EMAIL = "email";
    public static final String ALL = "/all";
    public static final String DELETE_BY_ID = "/deletebyid/{id}";
    public static final String DELETE_BY_ID2 = "/deletebyid/";
    public static final String DELETE_BY_IDS = "/deletebyids";
    public static final String ALL_WITH_SORT = "/all{sort}";
    public static final String FIND_ALL_BY_IDS = "/findallbyid/{itrbl}";
    public static final String FIND_BY_EMAIL = "/findbyemail/{email}";
    public static final String FIND_BY_EMAIL2 = "/findbyemail/";
    public static final String IS_AUTHENTICATED = "/isauthenticated/{email}";
    public static final String IS_AUTHENTICATED2 = "/isauthenticated/";
    public static final String GET_BY_ORGANISATION_ID = "/getbyorganisationid/{organisation_id}";
    public static final String GET_BY_ORGANISATION_ID2 = "/getbyorganisationid/";
    public static final String GET_BY_ID_AND_ORGANISATION_ID = "/getbyidandorganisationid/{id}/{organisation_id}";

    public static final String GET_BY_ID = "/getbyid/{id}";
    public static final String GET_BY_ID2 = "/getbyid/";
    public static final String UPDATE = "/update";
    public static final String CREATE = "/create";
    public static final String HEADER_APP_ID = "h_a_i";
    public static final String HEADER_USERID = "h_u";
    //</editor-fold>
}
