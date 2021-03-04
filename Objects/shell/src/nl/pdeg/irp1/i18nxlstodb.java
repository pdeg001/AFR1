
package nl.pdeg.irp1;

import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RemoteObject;

public class i18nxlstodb {
    public static RemoteObject myClass;
	public i18nxlstodb() {
	}
    public static PCBA staticBA = new PCBA(null, i18nxlstodb.class);

public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _sql = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static nl.pdeg.irp1.main _main = null;
public static nl.pdeg.irp1.starter _starter = null;
public static Object[] GetGlobals(RemoteObject _ref) throws Exception {
		return new Object[] {"sql",_ref.getField(false, "_sql")};
}
}