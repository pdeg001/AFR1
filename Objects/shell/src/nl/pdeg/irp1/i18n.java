
package nl.pdeg.irp1;

import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RemoteObject;

public class i18n {
    public static RemoteObject myClass;
	public i18n() {
	}
    public static PCBA staticBA = new PCBA(null, i18n.class);

public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _i18nactivity = RemoteObject.declareNull("anywheresoftware.b4a.objects.ActivityWrapper");
public static RemoteObject _locale = RemoteObject.createImmutable("");
public static RemoteObject _rs = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
public static RemoteObject _sql = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static RemoteObject _qry = RemoteObject.createImmutable("");
public static RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _edt = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _i18nreturnvalue = RemoteObject.createImmutable("");
public static nl.pdeg.irp1.main _main = null;
public static nl.pdeg.irp1.starter _starter = null;
public static Object[] GetGlobals(RemoteObject _ref) throws Exception {
		return new Object[] {"edt",_ref.getField(false, "_edt"),"i18nActivity",_ref.getField(false, "_i18nactivity"),"i18nReturnvalue",_ref.getField(false, "_i18nreturnvalue"),"lbl",_ref.getField(false, "_lbl"),"locale",_ref.getField(false, "_locale"),"qry",_ref.getField(false, "_qry"),"rs",_ref.getField(false, "_rs"),"sql",_ref.getField(false, "_sql")};
}
}