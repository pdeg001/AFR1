package nl.pdeg.irp1;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends  android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "nl.pdeg.irp1", "nl.pdeg.irp1.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "nl.pdeg.irp1.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public static String _dbi18n = "";
public static String _i18nxls = "";
public static String _dbrdo = "";
public static nl.pdeg.irp1.i18nxlstodb _clsi18nxls = null;
public static anywheresoftware.b4a.objects.collections.List _lstcountry = null;
public static String _locale = "";
public static String _country = "";
public static anywheresoftware.b4a.sql.SQL _i18nsql = null;
public static anywheresoftware.b4a.sql.SQL _rdosql = null;
public static String _filesfolder = "";
public static String _doy = "";
public static String _moy = "";
public static String _ftp = "";
public static boolean _hasinternet = false;
public b4a.example.dateutils _dateutils = null;
public nl.pdeg.irp1.main _main = null;
public nl.pdeg.irp1.selectcountry _selectcountry = null;
public nl.pdeg.irp1.xuiviewsutils _xuiviewsutils = null;
public nl.pdeg.irp1.httputils2service _httputils2service = null;
public nl.pdeg.irp1.b4xcollections _b4xcollections = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 33;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return false;
}
public static String  _closeapplication() throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 105;BA.debugLine="Public Sub closeApplication";
 //BA.debugLineNum = 106;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 107;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext(processBA);
 //BA.debugLineNum = 108;BA.debugLine="jo.RunMethod(\"finishAffinity\", Null)";
_jo.RunMethod("finishAffinity",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static nl.pdeg.irp1.main._i18n  _createi18n(String _countrytl) throws Exception{
nl.pdeg.irp1.main._i18n _t1 = null;
 //BA.debugLineNum = 98;BA.debugLine="Public Sub Createi18n (countryTl As String) As i18";
 //BA.debugLineNum = 99;BA.debugLine="Dim t1 As i18n";
_t1 = new nl.pdeg.irp1.main._i18n();
 //BA.debugLineNum = 100;BA.debugLine="t1.Initialize";
_t1.Initialize();
 //BA.debugLineNum = 101;BA.debugLine="t1.countryTl = countryTl";
_t1.countryTl /*String*/  = _countrytl;
 //BA.debugLineNum = 102;BA.debugLine="Return t1";
if (true) return _t1;
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return null;
}
public static String  _findlocale2() throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 87;BA.debugLine="Private Sub FindLocale2";
 //BA.debugLineNum = 88;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 89;BA.debugLine="jo = jo.InitializeStatic(\"java.util.Locale\").RunM";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.InitializeStatic("java.util.Locale").RunMethod("getDefault",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 90;BA.debugLine="locale = jo.RunMethod(\"getLanguage\", Null)";
_locale = BA.ObjectToString(_jo.RunMethod("getLanguage",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 91;BA.debugLine="country = jo.RunMethod(\"getCountry\", Null)";
_country = BA.ObjectToString(_jo.RunMethod("getCountry",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _getseti18nfiles() throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Private Sub GetSetI18nFiles";
 //BA.debugLineNum = 60;BA.debugLine="File.Copy(File.DirAssets, dbI18n, filesFolder, db";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_dbi18n,_filesfolder,_dbi18n);
 //BA.debugLineNum = 63;BA.debugLine="File.Copy(File.DirAssets, i18nXls, filesFolder, i";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_i18nxls,_filesfolder,_i18nxls);
 //BA.debugLineNum = 65;BA.debugLine="If File.Exists(filesFolder, dbRdo) = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_filesfolder,_dbrdo)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 66;BA.debugLine="File.Copy(File.DirAssets, dbRdo, filesFolder, db";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_dbrdo,_filesfolder,_dbrdo);
 };
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _getseti18nxls() throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Private Sub GetSeti18nXls";
 //BA.debugLineNum = 95;BA.debugLine="clsI18nXls.GetXml";
_clsi18nxls._getxml /*String*/ ();
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _initi18nsql() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Public Sub InitI18nSql";
 //BA.debugLineNum = 75;BA.debugLine="If i18nSql.IsInitialized = False Then";
if (_i18nsql.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 76;BA.debugLine="i18nSql.Initialize(filesFolder, dbI18n, False)";
_i18nsql.Initialize(_filesfolder,_dbi18n,anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _initrdodb() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Public Sub InitRdoDb";
 //BA.debugLineNum = 82;BA.debugLine="If rdoSql.IsInitialized = False Then";
if (_rdosql.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 83;BA.debugLine="rdoSql.Initialize(filesFolder, dbRdo, False)";
_rdosql.Initialize(_filesfolder,_dbrdo,anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static String  _initstarter() throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Private Sub InitStarter";
 //BA.debugLineNum = 41;BA.debugLine="lstCountry.Initialize";
_lstcountry.Initialize();
 //BA.debugLineNum = 42;BA.debugLine="lstCountry.Add(Createi18n(\"EN\"))";
_lstcountry.Add((Object)(_createi18n("EN")));
 //BA.debugLineNum = 43;BA.debugLine="lstCountry.Add(Createi18n(\"NL\"))";
_lstcountry.Add((Object)(_createi18n("NL")));
 //BA.debugLineNum = 44;BA.debugLine="dbI18n = \"i18n.db\"";
_dbi18n = "i18n.db";
 //BA.debugLineNum = 45;BA.debugLine="i18nXls = \"afr_i18n.xls\"";
_i18nxls = "afr_i18n.xls";
 //BA.debugLineNum = 46;BA.debugLine="dbRdo = \"rdodb.db\"";
_dbrdo = "rdodb.db";
 //BA.debugLineNum = 48;BA.debugLine="FindLocale2";
_findlocale2();
 //BA.debugLineNum = 49;BA.debugLine="SetFilesFolder";
_setfilesfolder();
 //BA.debugLineNum = 50;BA.debugLine="GetSetI18nFiles";
_getseti18nfiles();
 //BA.debugLineNum = 51;BA.debugLine="InitI18nSql";
_initi18nsql();
 //BA.debugLineNum = 52;BA.debugLine="InitRdoDb";
_initrdodb();
 //BA.debugLineNum = 54;BA.debugLine="clsI18nXls.Initialize";
_clsi18nxls._initialize /*String*/ (processBA);
 //BA.debugLineNum = 55;BA.debugLine="clsI18nXls.GetXml";
_clsi18nxls._getxml /*String*/ ();
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private rp As RuntimePermissions";
_rp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 9;BA.debugLine="Public dbI18n, i18nXls, dbRdo As String";
_dbi18n = "";
_i18nxls = "";
_dbrdo = "";
 //BA.debugLineNum = 10;BA.debugLine="Private clsI18nXls As i18nXlsToDb";
_clsi18nxls = new nl.pdeg.irp1.i18nxlstodb();
 //BA.debugLineNum = 11;BA.debugLine="Private lstCountry As List";
_lstcountry = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 13;BA.debugLine="Public locale, country As String";
_locale = "";
_country = "";
 //BA.debugLineNum = 14;BA.debugLine="Public i18nSql, rdoSql As SQL";
_i18nsql = new anywheresoftware.b4a.sql.SQL();
_rdosql = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 15;BA.debugLine="Public filesFolder As String";
_filesfolder = "";
 //BA.debugLineNum = 16;BA.debugLine="Public doy As String =\"pdegrootafr\", moy As Strin";
_doy = "pdegrootafr";
_moy = "hkWpXtB1!";
_ftp = "ftp.pdeg.nl";
 //BA.debugLineNum = 17;BA.debugLine="Public hasInternet As Boolean";
_hasinternet = false;
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 21;BA.debugLine="InitStarter";
_initstarter();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 25;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _setfilesfolder() throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Private Sub SetFilesFolder";
 //BA.debugLineNum = 71;BA.debugLine="filesFolder = rp.GetSafeDirDefaultExternal(\"\")";
_filesfolder = _rp.GetSafeDirDefaultExternal("");
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
}
