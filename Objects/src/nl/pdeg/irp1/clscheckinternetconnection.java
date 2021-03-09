package nl.pdeg.irp1;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class clscheckinternetconnection extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "nl.pdeg.irp1.clscheckinternetconnection");
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

 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.dateutils _dateutils = null;
public nl.pdeg.irp1.main _main = null;
public nl.pdeg.irp1.starter _starter = null;
public nl.pdeg.irp1.selectcountry _selectcountry = null;
public nl.pdeg.irp1.xuiviewsutils _xuiviewsutils = null;
public nl.pdeg.irp1.httputils2service _httputils2service = null;
public nl.pdeg.irp1.b4xcollections _b4xcollections = null;
public anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _checkconnected() throws Exception{
ResumableSub_CheckConnected rsub = new ResumableSub_CheckConnected(this);
rsub.resume(ba, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_CheckConnected extends BA.ResumableSub {
public ResumableSub_CheckConnected(nl.pdeg.irp1.clscheckinternetconnection parent) {
this.parent = parent;
}
nl.pdeg.irp1.clscheckinternetconnection parent;
anywheresoftware.b4a.phone.Phone _p = null;
boolean _success = false;
int _exitvalue = 0;
String _stdout = "";
String _stderr = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
parent.__c.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 12;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 13;BA.debugLine="Wait For (p.ShellAsync(\"ping\", Array As String(\"-";
parent.__c.WaitFor("complete", ba, this, _p.ShellAsync(ba,"ping",new String[]{"-c","1","8.8.8.8"}));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[0];
_exitvalue = (Integer) result[1];
_stdout = (String) result[2];
_stderr = (String) result[3];
;
 //BA.debugLineNum = 14;BA.debugLine="If StdErr = \"\" And StdOut.Contains(\"Destination H";
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
 //BA.debugLineNum = 15;BA.debugLine="Return True";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.True));return;};
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 17;BA.debugLine="Return False";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.False));return;};
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _complete(boolean _success,int _exitvalue,String _stdout,String _stderr) throws Exception{
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 6;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
