
package nl.pdeg.irp1;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class starter implements IRemote{
	public static starter mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public starter() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
public boolean isSingleton() {
		return true;
	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("starter"), "nl.pdeg.irp1.starter");
	}
     public static RemoteObject getObject() {
		return myClass;
	 }
	public RemoteObject _service;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
        _service = (RemoteObject) args[2];
        remoteMe = RemoteObject.declareNull("nl.pdeg.irp1.starter");
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[3];
		pcBA = new PCBA(this, starter.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}public static RemoteObject runMethod(boolean notUsed, String method, Object... args) throws Exception{
		return (RemoteObject) mostCurrent.pcBA.raiseEvent(method.substring(1), args);
	}
    public static void runVoidMethod(String method, Object... args) throws Exception{
		runMethod(false, method, args);
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _rp = RemoteObject.declareNull("anywheresoftware.b4a.objects.RuntimePermissions");
public static RemoteObject _dbi18n = RemoteObject.createImmutable("");
public static RemoteObject _i18nxls = RemoteObject.createImmutable("");
public static RemoteObject _clsi18nxls = RemoteObject.declareNull("nl.pdeg.irp1.i18nxlstodb");
public static RemoteObject _locale = RemoteObject.createImmutable("");
public static RemoteObject _country = RemoteObject.createImmutable("");
public static RemoteObject _i18nsql = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static RemoteObject _filesfolder = RemoteObject.createImmutable("");
public static nl.pdeg.irp1.main _main = null;
  public Object[] GetGlobals() {
		return new Object[] {"clsI18nXls",starter._clsi18nxls,"country",starter._country,"dbI18n",starter._dbi18n,"filesFolder",starter._filesfolder,"i18nSql",starter._i18nsql,"i18nXls",starter._i18nxls,"locale",starter._locale,"Main",Debug.moduleToString(nl.pdeg.irp1.main.class),"rp",starter._rp,"Service",starter.mostCurrent._service};
}
}