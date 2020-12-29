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
public class SQLDbConstants {

    //<editor-fold defaultstate="collapsed" desc="Sql query parts">
    public final static String LIST = "list";
    public final static String BEGINCHARACTER = "(";
    public final static String ENDCHARACTER = ") ";
    public final static String SELECT = "SELECT ";
    public final static String UPDATE = "UPDATE ";
    public final static String SET = " SET ";
    public final static String ALL = " * ";
    public final static String FROM = " FROM ";
    public final static String WHERE = " WHERE ";
    public final static String AND = " AND ";
    public final static String BETWEEN = " BETWEEN ";
    public final static String COMMA = ", ";
    public final static String EQUAL = " = ";
    public final static String IN = " IN ";
    public final static String BEGINDAYTIMENOTATION = " 00:00:00 ";
    public final static String ENDDAYTIMENOTATION = " 23:59:59 ";
    public final static String JAVAPARAM = ":";
    public final static String JAVAT = " T";
    public final static String JAVAR = " R";
    public final static String DOT = ".";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Queries">
    public final static String SELECTALLFROMTABLE = SELECT + JAVAT + FROM;
    //</editor-fold>
}
