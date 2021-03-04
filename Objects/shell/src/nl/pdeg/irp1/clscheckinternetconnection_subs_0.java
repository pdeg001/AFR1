package nl.pdeg.irp1;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class clscheckinternetconnection_subs_0 {


public static RemoteObject  _checkconnected(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("CheckConnected (clscheckinternetconnection) ","clscheckinternetconnection",4,__ref.getField(false, "ba"),__ref,11);
if (RapidSub.canDelegate("checkconnected")) { return __ref.runUserSub(false, "clscheckinternetconnection","checkconnected", __ref);}
ResumableSub_CheckConnected rsub = new ResumableSub_CheckConnected(null,__ref);
rsub.remoteResumableSub = anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSubForFilter();
rsub.resume(null, null);
return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.Common.ResumableSubWrapper"), rsub.remoteResumableSub);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_CheckConnected extends BA.ResumableSub {
public ResumableSub_CheckConnected(nl.pdeg.irp1.clscheckinternetconnection parent,RemoteObject __ref) {
this.parent = parent;
this.__ref = __ref;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
RemoteObject __ref;
nl.pdeg.irp1.clscheckinternetconnection parent;
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.phone.Phone");
RemoteObject _success = RemoteObject.createImmutable(false);
RemoteObject _exitvalue = RemoteObject.createImmutable(0);
RemoteObject _stdout = RemoteObject.createImmutable("");
RemoteObject _stderr = RemoteObject.createImmutable("");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("CheckConnected (clscheckinternetconnection) ","clscheckinternetconnection",4,__ref.getField(false, "ba"),__ref,11);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = 1;
Debug.locals.put("_ref", __ref);
 BA.debugLineNum = 12;BA.debugLine="Dim p As Phone";
Debug.ShouldStop(2048);
_p = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone");Debug.locals.put("p", _p);
 BA.debugLineNum = 13;BA.debugLine="Wait For (p.ShellAsync(\"ping\", Array As String(\"-";
Debug.ShouldStop(4096);
parent.__c.runVoidMethod ("WaitFor","complete", __ref.getField(false, "ba"), anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "clscheckinternetconnection", "checkconnected"), _p.runMethod(false,"ShellAsync",__ref.getField(false, "ba"),(Object)(BA.ObjectToString("ping")),(Object)(RemoteObject.createNewArray("String",new int[] {3},new Object[] {BA.ObjectToString("-c"),BA.ObjectToString("1"),RemoteObject.createImmutable("8.8.8.8")}))));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(1));Debug.locals.put("Success", _success);
_exitvalue = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(2));Debug.locals.put("ExitValue", _exitvalue);
_stdout = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(3));Debug.locals.put("StdOut", _stdout);
_stderr = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(4));Debug.locals.put("StdErr", _stderr);
;
 BA.debugLineNum = 14;BA.debugLine="If StdErr = \"\" And StdOut.Contains(\"Destination H";
Debug.ShouldStop(8192);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_stderr,BA.ObjectToString("")) && RemoteObject.solveBoolean("=",_stdout.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("Destination Host Unreachable"))),parent.__c.getField(true,"False"))) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 15;BA.debugLine="Return True";
Debug.ShouldStop(16384);
if (true) {
parent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,(parent.__c.getField(true,"True")));return;};
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 17;BA.debugLine="Return False";
Debug.ShouldStop(65536);
if (true) {
parent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,(parent.__c.getField(true,"False")));return;};
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 19;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
public static void  _complete(RemoteObject __ref,RemoteObject _success,RemoteObject _exitvalue,RemoteObject _stdout,RemoteObject _stderr) throws Exception{
}
public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba) throws Exception{
try {
		Debug.PushSubsStack("Initialize (clscheckinternetconnection) ","clscheckinternetconnection",4,__ref.getField(false, "ba"),__ref,6);
if (RapidSub.canDelegate("initialize")) { return __ref.runUserSub(false, "clscheckinternetconnection","initialize", __ref, _ba);}
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
 BA.debugLineNum = 6;BA.debugLine="Public Sub Initialize";
Debug.ShouldStop(32);
 BA.debugLineNum = 8;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}