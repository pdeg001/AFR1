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
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, anywheresoftware.b4a.ShellBA.class);
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
		    processBA = new anywheresoftware.b4a.ShellBA(this, null, null, "nl.pdeg.irp1", "nl.pdeg.irp1.starter");
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
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public static String _dbi18n = "";
public static String _i18nxls = "";
public static nl.pdeg.irp1.i18nxlstodb _clsi18nxls = null;
public static String _locale = "";
public static String _country = "";
public static anywheresoftware.b4a.sql.SQL _i18nsql = null;
public static String _filesfolder = "";
public nl.pdeg.irp1.main _main = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "application_error", false))
	 {return ((Boolean) Debug.delegate(processBA, "application_error", new Object[] {_error,_stacktrace}));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="End Sub";
return false;
}
public static String  _findlocale2() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "findlocale2", false))
	 {return ((String) Debug.delegate(processBA, "findlocale2", null));}
anywheresoftware.b4j.object.JavaObject _jo = null;
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Private Sub FindLocale2' As String";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="jo = jo.InitializeStatic(\"java.util.Locale\").RunM";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.InitializeStatic("java.util.Locale").RunMethod("getDefault",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="locale = jo.RunMethod(\"getLanguage\", Null)";
_locale = BA.ObjectToString(_jo.RunMethod("getLanguage",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="country = jo.RunMethod(\"getCountry\", Null)";
_country = BA.ObjectToString(_jo.RunMethod("getCountry",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="End Sub";
return "";
}
public static String  _getseti18nfiles() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "getseti18nfiles", false))
	 {return ((String) Debug.delegate(processBA, "getseti18nfiles", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Private Sub GetSetI18nFiles";
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="File.Copy(File.DirAssets, dbI18n, filesFolder, d";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_dbi18n,_filesfolder,_dbi18n);
RDebugUtils.currentLine=851973;
 //BA.debugLineNum = 851973;BA.debugLine="File.Copy(File.DirAssets, i18nXls, filesFolder,";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_i18nxls,_filesfolder,_i18nxls);
RDebugUtils.currentLine=851975;
 //BA.debugLineNum = 851975;BA.debugLine="End Sub";
return "";
}
public static String  _getseti18nxls() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "getseti18nxls", false))
	 {return ((String) Debug.delegate(processBA, "getseti18nxls", null));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub GetSeti18nXls";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="clsI18nXls.GetXml";
_clsi18nxls._getxml /*String*/ (null);
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="End Sub";
return "";
}
public static String  _initi18nsql() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "initi18nsql", false))
	 {return ((String) Debug.delegate(processBA, "initi18nsql", null));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Public Sub InitI18nSql";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="If i18nSql.IsInitialized = False Then";
if (_i18nsql.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="i18nSql.Initialize(filesFolder, dbI18n, False)";
_i18nsql.Initialize(_filesfolder,_dbi18n,anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="End Sub";
return "";
}
public static String  _initstarter() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "initstarter", false))
	 {return ((String) Debug.delegate(processBA, "initstarter", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Private Sub InitStarter";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="dbI18n = \"i18n.db\"";
_dbi18n = "i18n.db";
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="i18nXls = \"afr_i18n.xls\"";
_i18nxls = "afr_i18n.xls";
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="FindLocale2";
_findlocale2();
RDebugUtils.currentLine=786437;
 //BA.debugLineNum = 786437;BA.debugLine="SetFilesFolder";
_setfilesfolder();
RDebugUtils.currentLine=786438;
 //BA.debugLineNum = 786438;BA.debugLine="GetSetI18nFiles";
_getseti18nfiles();
RDebugUtils.currentLine=786439;
 //BA.debugLineNum = 786439;BA.debugLine="InitI18nSql";
_initi18nsql();
RDebugUtils.currentLine=786441;
 //BA.debugLineNum = 786441;BA.debugLine="clsI18nXls.Initialize";
_clsi18nxls._initialize /*String*/ (null,processBA);
RDebugUtils.currentLine=786442;
 //BA.debugLineNum = 786442;BA.debugLine="clsI18nXls.GetXml";
_clsi18nxls._getxml /*String*/ (null);
RDebugUtils.currentLine=786443;
 //BA.debugLineNum = 786443;BA.debugLine="End Sub";
return "";
}
public static String  _setfilesfolder() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "setfilesfolder", false))
	 {return ((String) Debug.delegate(processBA, "setfilesfolder", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Private Sub SetFilesFolder";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="filesFolder = rp.GetSafeDirDefaultExternal(\"\")";
_filesfolder = _rp.GetSafeDirDefaultExternal("");
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "service_create", false))
	 {return ((String) Debug.delegate(processBA, "service_create", null));}
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub Service_Create";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="InitStarter";
_initstarter();
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "service_destroy", false))
	 {return ((String) Debug.delegate(processBA, "service_destroy", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub Service_Destroy";
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "service_start", false))
	 {return ((String) Debug.delegate(processBA, "service_start", new Object[] {_startingintent}));}
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
mostCurrent._service.StopAutomaticForeground();
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
RDebugUtils.currentModule="starter";
if (Debug.shouldDelegate(processBA, "service_taskremoved", false))
	 {return ((String) Debug.delegate(processBA, "service_taskremoved", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub Service_TaskRemoved";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="End Sub";
return "";
}
}