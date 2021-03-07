package nl.pdeg.irp1;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class clscheckinternetconnection extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "nl.pdeg.irp1.clscheckinternetconnection");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", nl.pdeg.irp1.clscheckinternetconnection.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public nl.pdeg.irp1.main _main = null;
public nl.pdeg.irp1.starter _starter = null;
public String  _initialize(nl.pdeg.irp1.clscheckinternetconnection __ref,anywheresoftware.b4a.BA _ba) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="clscheckinternetconnection";
if (Debug.shouldDelegate(ba, "initialize", false))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba}));}
RDebugUtils.currentLine=1835008;
 //BA.debugLineNum = 1835008;BA.debugLine="Public Sub Initialize";
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _checkconnected(nl.pdeg.irp1.clscheckinternetconnection __ref) throws Exception{
RDebugUtils.currentModule="clscheckinternetconnection";
if (Debug.shouldDelegate(ba, "checkconnected", false))
	 {return ((anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) Debug.delegate(ba, "checkconnected", null));}
ResumableSub_CheckConnected rsub = new ResumableSub_CheckConnected(this,__ref);
rsub.resume(ba, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_CheckConnected extends BA.ResumableSub {
public ResumableSub_CheckConnected(nl.pdeg.irp1.clscheckinternetconnection parent,nl.pdeg.irp1.clscheckinternetconnection __ref) {
this.parent = parent;
this.__ref = __ref;
this.__ref = parent;
}
nl.pdeg.irp1.clscheckinternetconnection __ref;
nl.pdeg.irp1.clscheckinternetconnection parent;
anywheresoftware.b4a.phone.Phone _p = null;
boolean _success = false;
int _exitvalue = 0;
String _stdout = "";
String _stderr = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="clscheckinternetconnection";

    while (true) {
        switch (state) {
            case -1:
{
parent.__c.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="Wait For (p.ShellAsync(\"ping\", Array As String(\"-";
parent.__c.WaitFor("complete", ba, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "clscheckinternetconnection", "checkconnected"), _p.ShellAsync(ba,"ping",new String[]{"-c","1","8.8.8.8"}));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[1];
_exitvalue = (Integer) result[2];
_stdout = (String) result[3];
_stderr = (String) result[4];
;
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="If StdErr = \"\" And StdOut.Contains(\"Destination H";
if (true) break;

case 1:
//if
this.state = 6;
if ((_stderr).equals("") && _stdout.contains("Destination Host Unreachable")==parent.__c.False) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
RDebugUtils.currentLine=1900548;
 //BA.debugLineNum = 1900548;BA.debugLine="Return True";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.True));return;};
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=1900550;
 //BA.debugLineNum = 1900550;BA.debugLine="Return False";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.False));return;};
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=1900552;
 //BA.debugLineNum = 1900552;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _class_globals(nl.pdeg.irp1.clscheckinternetconnection __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="clscheckinternetconnection";
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="End Sub";
return "";
}
}