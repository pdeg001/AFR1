package nl.pdeg.irp1;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class starter_subs_0 {


public static RemoteObject  _application_error(RemoteObject _error,RemoteObject _stacktrace) throws Exception{
try {
		Debug.PushSubsStack("Application_Error (starter) ","starter",1,starter.processBA,starter.mostCurrent,29);
if (RapidSub.canDelegate("application_error")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","application_error", _error, _stacktrace);}
Debug.locals.put("Error", _error);
Debug.locals.put("StackTrace", _stacktrace);
 BA.debugLineNum = 29;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 30;BA.debugLine="Return True";
Debug.ShouldStop(536870912);
if (true) return starter.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 31;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _findlocale2() throws Exception{
try {
		Debug.PushSubsStack("FindLocale2 (starter) ","starter",1,starter.processBA,starter.mostCurrent,69);
if (RapidSub.canDelegate("findlocale2")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","findlocale2");}
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
 BA.debugLineNum = 69;BA.debugLine="Private Sub FindLocale2' As String";
Debug.ShouldStop(16);
 BA.debugLineNum = 70;BA.debugLine="Dim jo As JavaObject";
Debug.ShouldStop(32);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("jo", _jo);
 BA.debugLineNum = 71;BA.debugLine="jo = jo.InitializeStatic(\"java.util.Locale\").RunM";
Debug.ShouldStop(64);
_jo = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), _jo.runMethod(false,"InitializeStatic",(Object)(RemoteObject.createImmutable("java.util.Locale"))).runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getDefault")),(Object)((starter.mostCurrent.__c.getField(false,"Null")))));
 BA.debugLineNum = 72;BA.debugLine="locale = jo.RunMethod(\"getLanguage\", Null)";
Debug.ShouldStop(128);
starter._locale = BA.ObjectToString(_jo.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getLanguage")),(Object)((starter.mostCurrent.__c.getField(false,"Null")))));
 BA.debugLineNum = 73;BA.debugLine="country = jo.RunMethod(\"getCountry\", Null)";
Debug.ShouldStop(256);
starter._country = BA.ObjectToString(_jo.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getCountry")),(Object)((starter.mostCurrent.__c.getField(false,"Null")))));
 BA.debugLineNum = 74;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getseti18nfiles() throws Exception{
try {
		Debug.PushSubsStack("GetSetI18nFiles (starter) ","starter",1,starter.processBA,starter.mostCurrent,50);
if (RapidSub.canDelegate("getseti18nfiles")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","getseti18nfiles");}
 BA.debugLineNum = 50;BA.debugLine="Private Sub GetSetI18nFiles";
Debug.ShouldStop(131072);
 BA.debugLineNum = 52;BA.debugLine="File.Copy(File.DirAssets, dbI18n, filesFolder, d";
Debug.ShouldStop(524288);
starter.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(starter._dbi18n),(Object)(starter._filesfolder),(Object)(starter._dbi18n));
 BA.debugLineNum = 55;BA.debugLine="File.Copy(File.DirAssets, i18nXls, filesFolder,";
Debug.ShouldStop(4194304);
starter.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(starter._i18nxls),(Object)(starter._filesfolder),(Object)(starter._i18nxls));
 BA.debugLineNum = 57;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getseti18nxls() throws Exception{
try {
		Debug.PushSubsStack("GetSeti18nXls (starter) ","starter",1,starter.processBA,starter.mostCurrent,76);
if (RapidSub.canDelegate("getseti18nxls")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","getseti18nxls");}
 BA.debugLineNum = 76;BA.debugLine="Private Sub GetSeti18nXls";
Debug.ShouldStop(2048);
 BA.debugLineNum = 77;BA.debugLine="clsI18nXls.GetXml";
Debug.ShouldStop(4096);
starter._clsi18nxls.runClassMethod (nl.pdeg.irp1.i18nxlstodb.class, "_getxml" /*RemoteObject*/ );
 BA.debugLineNum = 78;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initi18nsql() throws Exception{
try {
		Debug.PushSubsStack("InitI18nSql (starter) ","starter",1,starter.processBA,starter.mostCurrent,63);
if (RapidSub.canDelegate("initi18nsql")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","initi18nsql");}
 BA.debugLineNum = 63;BA.debugLine="Public Sub InitI18nSql";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 64;BA.debugLine="If i18nSql.IsInitialized = False Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",starter._i18nsql.runMethod(true,"IsInitialized"),starter.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 65;BA.debugLine="i18nSql.Initialize(filesFolder, dbI18n, False)";
Debug.ShouldStop(1);
starter._i18nsql.runVoidMethod ("Initialize",(Object)(starter._filesfolder),(Object)(starter._dbi18n),(Object)(starter.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 67;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initstarter() throws Exception{
try {
		Debug.PushSubsStack("InitStarter (starter) ","starter",1,starter.processBA,starter.mostCurrent,37);
if (RapidSub.canDelegate("initstarter")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","initstarter");}
 BA.debugLineNum = 37;BA.debugLine="Private Sub InitStarter";
Debug.ShouldStop(16);
 BA.debugLineNum = 38;BA.debugLine="dbI18n = \"i18n.db\"";
Debug.ShouldStop(32);
starter._dbi18n = BA.ObjectToString("i18n.db");
 BA.debugLineNum = 39;BA.debugLine="i18nXls = \"afr_i18n.xls\"";
Debug.ShouldStop(64);
starter._i18nxls = BA.ObjectToString("afr_i18n.xls");
 BA.debugLineNum = 41;BA.debugLine="FindLocale2";
Debug.ShouldStop(256);
_findlocale2();
 BA.debugLineNum = 42;BA.debugLine="SetFilesFolder";
Debug.ShouldStop(512);
_setfilesfolder();
 BA.debugLineNum = 43;BA.debugLine="GetSetI18nFiles";
Debug.ShouldStop(1024);
_getseti18nfiles();
 BA.debugLineNum = 44;BA.debugLine="InitI18nSql";
Debug.ShouldStop(2048);
_initi18nsql();
 BA.debugLineNum = 46;BA.debugLine="clsI18nXls.Initialize";
Debug.ShouldStop(8192);
starter._clsi18nxls.runClassMethod (nl.pdeg.irp1.i18nxlstodb.class, "_initialize" /*RemoteObject*/ ,starter.processBA);
 BA.debugLineNum = 47;BA.debugLine="clsI18nXls.GetXml";
Debug.ShouldStop(16384);
starter._clsi18nxls.runClassMethod (nl.pdeg.irp1.i18nxlstodb.class, "_getxml" /*RemoteObject*/ );
 BA.debugLineNum = 48;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private rp As RuntimePermissions";
starter._rp = RemoteObject.createNew ("anywheresoftware.b4a.objects.RuntimePermissions");
 //BA.debugLineNum = 9;BA.debugLine="Public dbI18n, i18nXls As String";
starter._dbi18n = RemoteObject.createImmutable("");
starter._i18nxls = RemoteObject.createImmutable("");
 //BA.debugLineNum = 10;BA.debugLine="Private clsI18nXls As i18nXlsToDb";
starter._clsi18nxls = RemoteObject.createNew ("nl.pdeg.irp1.i18nxlstodb");
 //BA.debugLineNum = 12;BA.debugLine="Public locale, country As String";
starter._locale = RemoteObject.createImmutable("");
starter._country = RemoteObject.createImmutable("");
 //BA.debugLineNum = 13;BA.debugLine="Public i18nSql As SQL";
starter._i18nsql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 14;BA.debugLine="Public filesFolder As String";
starter._filesfolder = RemoteObject.createImmutable("");
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (starter) ","starter",1,starter.processBA,starter.mostCurrent,17);
if (RapidSub.canDelegate("service_create")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","service_create");}
 BA.debugLineNum = 17;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(65536);
 BA.debugLineNum = 18;BA.debugLine="InitStarter";
Debug.ShouldStop(131072);
_initstarter();
 BA.debugLineNum = 19;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_destroy() throws Exception{
try {
		Debug.PushSubsStack("Service_Destroy (starter) ","starter",1,starter.processBA,starter.mostCurrent,33);
if (RapidSub.canDelegate("service_destroy")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","service_destroy");}
 BA.debugLineNum = 33;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(1);
 BA.debugLineNum = 35;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_start(RemoteObject _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (starter) ","starter",1,starter.processBA,starter.mostCurrent,21);
if (RapidSub.canDelegate("service_start")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","service_start", _startingintent);}
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 21;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 22;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
Debug.ShouldStop(2097152);
starter.mostCurrent._service.runVoidMethod ("StopAutomaticForeground");
 BA.debugLineNum = 23;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_taskremoved() throws Exception{
try {
		Debug.PushSubsStack("Service_TaskRemoved (starter) ","starter",1,starter.processBA,starter.mostCurrent,25);
if (RapidSub.canDelegate("service_taskremoved")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","service_taskremoved");}
 BA.debugLineNum = 25;BA.debugLine="Sub Service_TaskRemoved";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 26;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setfilesfolder() throws Exception{
try {
		Debug.PushSubsStack("SetFilesFolder (starter) ","starter",1,starter.processBA,starter.mostCurrent,59);
if (RapidSub.canDelegate("setfilesfolder")) { return nl.pdeg.irp1.starter.remoteMe.runUserSub(false, "starter","setfilesfolder");}
 BA.debugLineNum = 59;BA.debugLine="Private Sub SetFilesFolder";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 60;BA.debugLine="filesFolder = rp.GetSafeDirDefaultExternal(\"\")";
Debug.ShouldStop(134217728);
starter._filesfolder = starter._rp.runMethod(true,"GetSafeDirDefaultExternal",(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 61;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}