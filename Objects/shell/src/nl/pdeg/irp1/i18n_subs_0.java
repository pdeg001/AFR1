package nl.pdeg.irp1;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class i18n_subs_0 {


public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private i18nActivity As Activity";
i18n._i18nactivity = RemoteObject.createNew ("anywheresoftware.b4a.objects.ActivityWrapper");__ref.setField("_i18nactivity",i18n._i18nactivity);
 //BA.debugLineNum = 4;BA.debugLine="Private locale As String = Starter.locale";
i18n._locale = i18n._starter._locale /*RemoteObject*/ ;__ref.setField("_locale",i18n._locale);
 //BA.debugLineNum = 5;BA.debugLine="Private rs As ResultSet";
i18n._rs = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");__ref.setField("_rs",i18n._rs);
 //BA.debugLineNum = 6;BA.debugLine="Private sql As SQL = Starter.i18nSql";
i18n._sql = i18n._starter._i18nsql /*RemoteObject*/ ;__ref.setField("_sql",i18n._sql);
 //BA.debugLineNum = 7;BA.debugLine="Private qry As String";
i18n._qry = RemoteObject.createImmutable("");__ref.setField("_qry",i18n._qry);
 //BA.debugLineNum = 8;BA.debugLine="Private lbl As Label";
i18n._lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");__ref.setField("_lbl",i18n._lbl);
 //BA.debugLineNum = 9;BA.debugLine="Private edt As EditText";
i18n._edt = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");__ref.setField("_edt",i18n._edt);
 //BA.debugLineNum = 10;BA.debugLine="Private i18nReturnvalue As String";
i18n._i18nreturnvalue = RemoteObject.createImmutable("");__ref.setField("_i18nreturnvalue",i18n._i18nreturnvalue);
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _getflag(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("GetFlag (i18n) ","i18n",2,__ref.getField(false, "ba"),__ref,61);
if (RapidSub.canDelegate("getflag")) { return __ref.runUserSub(false, "i18n","getflag", __ref);}
 BA.debugLineNum = 61;BA.debugLine="Public Sub GetFlag As Bitmap";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 62;BA.debugLine="Return LoadBitmap(File.DirAssets, $\"${Starter.cou";
Debug.ShouldStop(536870912);
if (true) return i18n.__c.runMethod(false,"LoadBitmap",(Object)(i18n.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)((RemoteObject.concat(RemoteObject.createImmutable(""),i18n.__c.runMethod(true,"SmartStringFormatter",(Object)(BA.ObjectToString("")),(Object)((i18n._starter._country /*RemoteObject*/ .runMethod(true,"toLowerCase")))),RemoteObject.createImmutable(".png")))));
 BA.debugLineNum = 63;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _geti18nstring(RemoteObject __ref,RemoteObject _i18nstring) throws Exception{
try {
		Debug.PushSubsStack("GetI18nString (i18n) ","i18n",2,__ref.getField(false, "ba"),__ref,17);
if (RapidSub.canDelegate("geti18nstring")) { return __ref.runUserSub(false, "i18n","geti18nstring", __ref, _i18nstring);}
Debug.locals.put("i18NString", _i18nstring);
 BA.debugLineNum = 17;BA.debugLine="Private Sub GetI18nString(i18NString As String) As";
Debug.ShouldStop(65536);
 BA.debugLineNum = 18;BA.debugLine="Log(i18NString)";
Debug.ShouldStop(131072);
i18n.__c.runVoidMethod ("LogImpl","31310721",_i18nstring,0);
 BA.debugLineNum = 20;BA.debugLine="qry = $\"SELECT message_value FROM i18n_messages W";
Debug.ShouldStop(524288);
__ref.setField ("_qry" /*RemoteObject*/ ,(RemoteObject.concat(RemoteObject.createImmutable("SELECT message_value FROM i18n_messages\n"),RemoteObject.createImmutable("WHERE \n"),RemoteObject.createImmutable("LOWER(message_language) = ?\n"),RemoteObject.createImmutable("AND\n"),RemoteObject.createImmutable("message_key = ?"))));
 BA.debugLineNum = 26;BA.debugLine="rs = sql.ExecQuery2(qry, Array As String(locale.T";
Debug.ShouldStop(33554432);
__ref.getField(false,"_rs" /*RemoteObject*/ ).setObject (__ref.getField(false,"_sql" /*RemoteObject*/ ).runMethod(false,"ExecQuery2",(Object)(__ref.getField(true,"_qry" /*RemoteObject*/ )),(Object)(RemoteObject.createNewArray("String",new int[] {2},new Object[] {__ref.getField(true,"_locale" /*RemoteObject*/ ).runMethod(true,"toLowerCase"),_i18nstring}))));
 BA.debugLineNum = 27;BA.debugLine="i18nReturnvalue = sql.ExecQuerySingleResult2(qry,";
Debug.ShouldStop(67108864);
__ref.setField ("_i18nreturnvalue" /*RemoteObject*/ ,__ref.getField(false,"_sql" /*RemoteObject*/ ).runMethod(true,"ExecQuerySingleResult2",(Object)(__ref.getField(true,"_qry" /*RemoteObject*/ )),(Object)(RemoteObject.createNewArray("String",new int[] {2},new Object[] {__ref.getField(true,"_locale" /*RemoteObject*/ ).runMethod(true,"toLowerCase"),_i18nstring}))));
 BA.debugLineNum = 28;BA.debugLine="Log($\"i18nReturnValue : ${i18nReturnvalue}\"$)";
Debug.ShouldStop(134217728);
i18n.__c.runVoidMethod ("LogImpl","31310731",(RemoteObject.concat(RemoteObject.createImmutable("i18nReturnValue : "),i18n.__c.runMethod(true,"SmartStringFormatter",(Object)(BA.ObjectToString("")),(Object)((__ref.getField(true,"_i18nreturnvalue" /*RemoteObject*/ )))),RemoteObject.createImmutable(""))),0);
 BA.debugLineNum = 29;BA.debugLine="If i18nReturnvalue = Null Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("n",__ref.getField(true,"_i18nreturnvalue" /*RemoteObject*/ ))) { 
 BA.debugLineNum = 30;BA.debugLine="Return \"i18N Unknown\"";
Debug.ShouldStop(536870912);
if (true) return BA.ObjectToString("i18N Unknown");
 }else {
 BA.debugLineNum = 32;BA.debugLine="Return i18nReturnvalue";
Debug.ShouldStop(-2147483648);
if (true) return __ref.getField(true,"_i18nreturnvalue" /*RemoteObject*/ );
 };
 BA.debugLineNum = 41;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getviewssetlocale(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("GetViewsSetLocale (i18n) ","i18n",2,__ref.getField(false, "ba"),__ref,43);
if (RapidSub.canDelegate("getviewssetlocale")) { return __ref.runUserSub(false, "i18n","getviewssetlocale", __ref);}
RemoteObject _v = RemoteObject.declareNull("anywheresoftware.b4a.objects.ConcreteViewWrapper");
 BA.debugLineNum = 43;BA.debugLine="Public Sub GetViewsSetLocale";
Debug.ShouldStop(1024);
 BA.debugLineNum = 45;BA.debugLine="For Each v As View In i18nActivity.GetAllViewsRec";
Debug.ShouldStop(4096);
_v = RemoteObject.createNew ("anywheresoftware.b4a.objects.ConcreteViewWrapper");
{
final RemoteObject group1 = __ref.getField(false,"_i18nactivity" /*RemoteObject*/ ).runMethod(false,"GetAllViewsRecursive");
final int groupLen1 = group1.runMethod(true,"getSize").<Integer>get()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), group1.runMethod(false,"Get",index1));
Debug.locals.put("v", _v);
 BA.debugLineNum = 46;BA.debugLine="If v Is Label Then 'this will catch all of Label";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("i",_v.getObjectOrNull(), RemoteObject.createImmutable("android.widget.TextView"))) { 
 BA.debugLineNum = 47;BA.debugLine="lbl = v";
Debug.ShouldStop(16384);
__ref.getField(false,"_lbl" /*RemoteObject*/ ).setObject (_v.getObject());
 BA.debugLineNum = 48;BA.debugLine="If lbl.Text.IndexOf(\"i18n\") > -1 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(">",__ref.getField(false,"_lbl" /*RemoteObject*/ ).runMethod(true,"getText").runMethod(true,"indexOf",(Object)(RemoteObject.createImmutable("i18n"))),BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 49;BA.debugLine="lbl.Text = GetI18nString(lbl.Text)";
Debug.ShouldStop(65536);
__ref.getField(false,"_lbl" /*RemoteObject*/ ).runMethod(true,"setText",BA.ObjectToCharSequence(__ref.runClassMethod (nl.pdeg.irp1.i18n.class, "_geti18nstring" /*RemoteObject*/ ,(Object)(__ref.getField(false,"_lbl" /*RemoteObject*/ ).runMethod(true,"getText")))));
 };
 };
 BA.debugLineNum = 52;BA.debugLine="If v Is EditText Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("i",_v.getObjectOrNull(), RemoteObject.createImmutable("android.widget.EditText"))) { 
 BA.debugLineNum = 53;BA.debugLine="edt = v";
Debug.ShouldStop(1048576);
__ref.getField(false,"_edt" /*RemoteObject*/ ).setObject (_v.getObject());
 BA.debugLineNum = 54;BA.debugLine="If edt.Hint.IndexOf(\"i18n\") > -1 Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean(">",__ref.getField(false,"_edt" /*RemoteObject*/ ).runMethod(true,"getHint").runMethod(true,"indexOf",(Object)(RemoteObject.createImmutable("i18n"))),BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 55;BA.debugLine="edt.Hint = GetI18nString(edt.Hint)";
Debug.ShouldStop(4194304);
__ref.getField(false,"_edt" /*RemoteObject*/ ).runMethod(true,"setHint",__ref.runClassMethod (nl.pdeg.irp1.i18n.class, "_geti18nstring" /*RemoteObject*/ ,(Object)(__ref.getField(false,"_edt" /*RemoteObject*/ ).runMethod(true,"getHint"))));
 };
 };
 }
}Debug.locals.put("v", _v);
;
 BA.debugLineNum = 59;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba,RemoteObject _act) throws Exception{
try {
		Debug.PushSubsStack("Initialize (i18n) ","i18n",2,__ref.getField(false, "ba"),__ref,13);
if (RapidSub.canDelegate("initialize")) { return __ref.runUserSub(false, "i18n","initialize", __ref, _ba, _act);}
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
Debug.locals.put("act", _act);
 BA.debugLineNum = 13;BA.debugLine="Public Sub Initialize (act As Activity)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 14;BA.debugLine="i18nActivity = act";
Debug.ShouldStop(8192);
__ref.setField ("_i18nactivity" /*RemoteObject*/ ,_act);
 BA.debugLineNum = 15;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}