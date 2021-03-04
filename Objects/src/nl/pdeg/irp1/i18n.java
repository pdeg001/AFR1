package nl.pdeg.irp1;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class i18n extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "nl.pdeg.irp1.i18n");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", nl.pdeg.irp1.i18n.class).invoke(this, new Object[] {null});
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
public anywheresoftware.b4a.objects.ActivityWrapper _i18nactivity = null;
public String _locale = "";
public anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
public anywheresoftware.b4a.sql.SQL _sql = null;
public String _qry = "";
public anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt = null;
public String _i18nreturnvalue = "";
public nl.pdeg.irp1.main _main = null;
public nl.pdeg.irp1.starter _starter = null;
public String  _initialize(nl.pdeg.irp1.i18n __ref,anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _act) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="i18n";
if (Debug.shouldDelegate(ba, "initialize", false))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_act}));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Public Sub Initialize (act As Activity)";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="i18nActivity = act";
__ref._i18nactivity /*anywheresoftware.b4a.objects.ActivityWrapper*/  = _act;
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="End Sub";
return "";
}
public String  _getviewssetlocale(nl.pdeg.irp1.i18n __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="i18n";
if (Debug.shouldDelegate(ba, "getviewssetlocale", false))
	 {return ((String) Debug.delegate(ba, "getviewssetlocale", null));}
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Public Sub GetViewsSetLocale";
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="For Each v As View In i18nActivity.GetAllViewsRec";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group1 = __ref._i18nactivity /*anywheresoftware.b4a.objects.ActivityWrapper*/ .GetAllViewsRecursive();
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group1.Get(index1)));
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="If v Is Label Then 'this will catch all of Label";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="lbl = v";
__ref._lbl /*anywheresoftware.b4a.objects.LabelWrapper*/  = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_v.getObject()));
RDebugUtils.currentLine=1376261;
 //BA.debugLineNum = 1376261;BA.debugLine="If lbl.Text.IndexOf(\"i18n\") > -1 Then";
if (__ref._lbl /*anywheresoftware.b4a.objects.LabelWrapper*/ .getText().indexOf("i18n")>-1) { 
RDebugUtils.currentLine=1376262;
 //BA.debugLineNum = 1376262;BA.debugLine="lbl.Text = GetI18nString(lbl.Text)";
__ref._lbl /*anywheresoftware.b4a.objects.LabelWrapper*/ .setText(BA.ObjectToCharSequence(__ref._geti18nstring /*String*/ (null,__ref._lbl /*anywheresoftware.b4a.objects.LabelWrapper*/ .getText())));
 };
 };
RDebugUtils.currentLine=1376265;
 //BA.debugLineNum = 1376265;BA.debugLine="If v Is EditText Then";
if (_v.getObjectOrNull() instanceof android.widget.EditText) { 
RDebugUtils.currentLine=1376266;
 //BA.debugLineNum = 1376266;BA.debugLine="edt = v";
__ref._edt /*anywheresoftware.b4a.objects.EditTextWrapper*/  = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(_v.getObject()));
RDebugUtils.currentLine=1376267;
 //BA.debugLineNum = 1376267;BA.debugLine="If edt.Hint.IndexOf(\"i18n\") > -1 Then";
if (__ref._edt /*anywheresoftware.b4a.objects.EditTextWrapper*/ .getHint().indexOf("i18n")>-1) { 
RDebugUtils.currentLine=1376268;
 //BA.debugLineNum = 1376268;BA.debugLine="edt.Hint = GetI18nString(edt.Hint)";
__ref._edt /*anywheresoftware.b4a.objects.EditTextWrapper*/ .setHint(__ref._geti18nstring /*String*/ (null,__ref._edt /*anywheresoftware.b4a.objects.EditTextWrapper*/ .getHint()));
 };
 };
 }
};
RDebugUtils.currentLine=1376272;
 //BA.debugLineNum = 1376272;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getflag(nl.pdeg.irp1.i18n __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="i18n";
if (Debug.shouldDelegate(ba, "getflag", false))
	 {return ((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) Debug.delegate(ba, "getflag", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Public Sub GetFlag As Bitmap";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="Return LoadBitmap(File.DirAssets, $\"${Starter.cou";
if (true) return __c.LoadBitmap(__c.File.getDirAssets(),(""+__c.SmartStringFormatter("",(Object)(_starter._country /*String*/ .toLowerCase()))+".png"));
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="End Sub";
return null;
}
public String  _class_globals(nl.pdeg.irp1.i18n __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="i18n";
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Private i18nActivity As Activity";
_i18nactivity = new anywheresoftware.b4a.objects.ActivityWrapper();
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="Private locale As String = Starter.locale";
_locale = _starter._locale /*String*/ ;
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="Private rs As ResultSet";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
RDebugUtils.currentLine=1179652;
 //BA.debugLineNum = 1179652;BA.debugLine="Private sql As SQL = Starter.i18nSql";
_sql = _starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ ;
RDebugUtils.currentLine=1179653;
 //BA.debugLineNum = 1179653;BA.debugLine="Private qry As String";
_qry = "";
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="Private lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="Private edt As EditText";
_edt = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="Private i18nReturnvalue As String";
_i18nreturnvalue = "";
RDebugUtils.currentLine=1179657;
 //BA.debugLineNum = 1179657;BA.debugLine="End Sub";
return "";
}
public String  _geti18nstring(nl.pdeg.irp1.i18n __ref,String _i18nstring) throws Exception{
__ref = this;
RDebugUtils.currentModule="i18n";
if (Debug.shouldDelegate(ba, "geti18nstring", false))
	 {return ((String) Debug.delegate(ba, "geti18nstring", new Object[] {_i18nstring}));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Private Sub GetI18nString(i18NString As String) As";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="Log(i18NString)";
__c.LogImpl("31310721",_i18nstring,0);
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="qry = $\"SELECT message_value FROM i18n_messages W";
__ref._qry /*String*/  = ("SELECT message_value FROM i18n_messages\n"+"WHERE \n"+"LOWER(message_language) = ?\n"+"AND\n"+"message_key = ?");
RDebugUtils.currentLine=1310729;
 //BA.debugLineNum = 1310729;BA.debugLine="rs = sql.ExecQuery2(qry, Array As String(locale.T";
__ref._rs /*anywheresoftware.b4a.sql.SQL.ResultSetWrapper*/  = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(__ref._sql /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2(__ref._qry /*String*/ ,new String[]{__ref._locale /*String*/ .toLowerCase(),_i18nstring})));
RDebugUtils.currentLine=1310730;
 //BA.debugLineNum = 1310730;BA.debugLine="i18nReturnvalue = sql.ExecQuerySingleResult2(qry,";
__ref._i18nreturnvalue /*String*/  = __ref._sql /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult2(__ref._qry /*String*/ ,new String[]{__ref._locale /*String*/ .toLowerCase(),_i18nstring});
RDebugUtils.currentLine=1310731;
 //BA.debugLineNum = 1310731;BA.debugLine="Log($\"i18nReturnValue : ${i18nReturnvalue}\"$)";
__c.LogImpl("31310731",("i18nReturnValue : "+__c.SmartStringFormatter("",(Object)(__ref._i18nreturnvalue /*String*/ ))+""),0);
RDebugUtils.currentLine=1310732;
 //BA.debugLineNum = 1310732;BA.debugLine="If i18nReturnvalue = Null Then";
if (__ref._i18nreturnvalue /*String*/ == null) { 
RDebugUtils.currentLine=1310733;
 //BA.debugLineNum = 1310733;BA.debugLine="Return \"i18N Unknown\"";
if (true) return "i18N Unknown";
 }else {
RDebugUtils.currentLine=1310735;
 //BA.debugLineNum = 1310735;BA.debugLine="Return i18nReturnvalue";
if (true) return __ref._i18nreturnvalue /*String*/ ;
 };
RDebugUtils.currentLine=1310744;
 //BA.debugLineNum = 1310744;BA.debugLine="End Sub";
return "";
}
}