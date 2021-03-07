package nl.pdeg.irp1;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { return nl.pdeg.irp1.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 27;BA.debugLine="clsInetConnected.Initialize";
Debug.ShouldStop(67108864);
main.mostCurrent._clsinetconnected.runClassMethod (nl.pdeg.irp1.clscheckinternetconnection.class, "_initialize" /*RemoteObject*/ ,main.processBA);
 BA.debugLineNum = 28;BA.debugLine="clsI18n.Initialize(Activity)";
Debug.ShouldStop(134217728);
main.mostCurrent._clsi18n.runClassMethod (nl.pdeg.irp1.i18n.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.mostCurrent._activity));
 BA.debugLineNum = 29;BA.debugLine="CheckInetConnected";
Debug.ShouldStop(268435456);
_checkinetconnected();
 BA.debugLineNum = 30;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,36);
if (RapidSub.canDelegate("activity_pause")) { return nl.pdeg.irp1.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 36;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8);
 BA.debugLineNum = 38;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,32);
if (RapidSub.canDelegate("activity_resume")) { return nl.pdeg.irp1.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 34;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _checkinetconnected() throws Exception{
try {
		Debug.PushSubsStack("CheckInetConnected (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
if (RapidSub.canDelegate("checkinetconnected")) { nl.pdeg.irp1.main.remoteMe.runUserSub(false, "main","checkinetconnected"); return;}
ResumableSub_CheckInetConnected rsub = new ResumableSub_CheckInetConnected(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_CheckInetConnected extends BA.ResumableSub {
public ResumableSub_CheckInetConnected(nl.pdeg.irp1.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
nl.pdeg.irp1.main parent;
RemoteObject _result = RemoteObject.createImmutable(false);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("CheckInetConnected (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 43;BA.debugLine="Wait For (clsInetConnected.CheckConnected) Comple";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","complete", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "checkinetconnected"), parent.mostCurrent._clsinetconnected.runClassMethod (nl.pdeg.irp1.clscheckinternetconnection.class, "_checkconnected" /*RemoteObject*/ ));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("result", _result);
;
 BA.debugLineNum = 44;BA.debugLine="If result = True Then 'Treu for testing";
Debug.ShouldStop(2048);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_result,parent.mostCurrent.__c.getField(true,"True"))) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 45;BA.debugLine="Activity.LoadLayout(\"main_no_internet\")";
Debug.ShouldStop(4096);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main_no_internet")),main.mostCurrent.activityBA);
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 47;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(16384);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 50;BA.debugLine="clsI18n.GetViewsSetLocale";
Debug.ShouldStop(131072);
parent.mostCurrent._clsi18n.runClassMethod (nl.pdeg.irp1.i18n.class, "_getviewssetlocale" /*RemoteObject*/ );
 BA.debugLineNum = 51;BA.debugLine="imgFlag.Bitmap = clsI18n.GetFlag";
Debug.ShouldStop(262144);
parent.mostCurrent._imgflag.runMethod(false,"setBitmap",(parent.mostCurrent._clsi18n.runClassMethod (nl.pdeg.irp1.i18n.class, "_getflag" /*RemoteObject*/ ).getObject()));
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _complete(RemoteObject _result) throws Exception{
}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private clsI18n As i18n";
main.mostCurrent._clsi18n = RemoteObject.createNew ("nl.pdeg.irp1.i18n");
 //BA.debugLineNum = 20;BA.debugLine="Private clsInetConnected As ClsCheckInternetConne";
main.mostCurrent._clsinetconnected = RemoteObject.createNew ("nl.pdeg.irp1.clscheckinternetconnection");
 //BA.debugLineNum = 21;BA.debugLine="Private imgFlag As ImageView";
main.mostCurrent._imgflag = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private btnCloseApp As Button";
main.mostCurrent._btncloseapp = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private lblHeader As Label";
main.mostCurrent._lblheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("nl.pdeg.irp1.main");
starter.myClass = BA.getDeviceClass ("nl.pdeg.irp1.starter");
i18n.myClass = BA.getDeviceClass ("nl.pdeg.irp1.i18n");
i18nxlstodb.myClass = BA.getDeviceClass ("nl.pdeg.irp1.i18nxlstodb");
clscheckinternetconnection.myClass = BA.getDeviceClass ("nl.pdeg.irp1.clscheckinternetconnection");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}