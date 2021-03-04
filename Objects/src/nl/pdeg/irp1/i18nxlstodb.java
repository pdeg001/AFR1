package nl.pdeg.irp1;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class i18nxlstodb extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "nl.pdeg.irp1.i18nxlstodb");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", nl.pdeg.irp1.i18nxlstodb.class).invoke(this, new Object[] {null});
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
public anywheresoftware.b4a.sql.SQL _sql = null;
public nl.pdeg.irp1.main _main = null;
public nl.pdeg.irp1.starter _starter = null;
public String  _getxml(nl.pdeg.irp1.i18nxlstodb __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="i18nxlstodb";
if (Debug.shouldDelegate(ba, "getxml", false))
	 {return ((String) Debug.delegate(ba, "getxml", null));}
anywheresoftware.b4a.objects.WorkbookWrapper _workbook = null;
anywheresoftware.b4a.objects.WorkbookWrapper.SheetWrapper _sheet = null;
String _qry = "";
int _row = 0;
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Public Sub GetXml";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="Dim workbook As ReadableWorkbook";
_workbook = new anywheresoftware.b4a.objects.WorkbookWrapper();
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="Dim sheet As ReadableSheet";
_sheet = new anywheresoftware.b4a.objects.WorkbookWrapper.SheetWrapper();
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="Dim qry As String";
_qry = "";
RDebugUtils.currentLine=1703941;
 //BA.debugLineNum = 1703941;BA.debugLine="qry = $\"INSERT INTO i18n_messages 	(message_key,";
_qry = ("INSERT INTO i18n_messages\n"+"	(message_key, message_language, message_value) VALUES\n"+"	(?, ?, ?)");
RDebugUtils.currentLine=1703945;
 //BA.debugLineNum = 1703945;BA.debugLine="workbook.Initialize(Starter.filesFolder, Starter.";
_workbook.Initialize(_starter._filesfolder /*String*/ ,_starter._i18nxls /*String*/ );
RDebugUtils.currentLine=1703946;
 //BA.debugLineNum = 1703946;BA.debugLine="sheet = workbook.GetSheet(0)";
_sheet = _workbook.GetSheet((int) (0));
RDebugUtils.currentLine=1703948;
 //BA.debugLineNum = 1703948;BA.debugLine="Starter.i18nSql.BeginTransaction";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
RDebugUtils.currentLine=1703950;
 //BA.debugLineNum = 1703950;BA.debugLine="For row = 1 To sheet.RowsCount -1";
{
final int step8 = 1;
final int limit8 = (int) (_sheet.getRowsCount()-1);
_row = (int) (1) ;
for (;_row <= limit8 ;_row = _row + step8 ) {
RDebugUtils.currentLine=1703951;
 //BA.debugLineNum = 1703951;BA.debugLine="Starter.i18nSql.ExecNonQuery2(qry, Array As Stri";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(_qry,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_sheet.GetCellValue((int) (0),_row),_sheet.GetCellValue((int) (1),_row),_sheet.GetCellValue((int) (2),_row)}));
 }
};
RDebugUtils.currentLine=1703956;
 //BA.debugLineNum = 1703956;BA.debugLine="Starter.i18nSql.TransactionSuccessful";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
RDebugUtils.currentLine=1703957;
 //BA.debugLineNum = 1703957;BA.debugLine="Starter.i18nSql.EndTransaction";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
RDebugUtils.currentLine=1703958;
 //BA.debugLineNum = 1703958;BA.debugLine="End Sub";
return "";
}
public String  _initialize(nl.pdeg.irp1.i18nxlstodb __ref,anywheresoftware.b4a.BA _ba) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="i18nxlstodb";
if (Debug.shouldDelegate(ba, "initialize", false))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba}));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Public Sub Initialize";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="If sql.IsInitialized = False Then";
if (__ref._sql /*anywheresoftware.b4a.sql.SQL*/ .IsInitialized()==__c.False) { 
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="Starter.InitI18nSql";
_starter._initi18nsql /*String*/ ();
 };
RDebugUtils.currentLine=1572869;
 //BA.debugLineNum = 1572869;BA.debugLine="TruncateI18nTable";
__ref._truncatei18ntable /*void*/ (null);
RDebugUtils.currentLine=1572870;
 //BA.debugLineNum = 1572870;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(nl.pdeg.irp1.i18nxlstodb __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="i18nxlstodb";
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="Dim sql As SQL = Starter.i18nSql";
_sql = _starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ ;
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="End Sub";
return "";
}
public void  _truncatei18ntable(nl.pdeg.irp1.i18nxlstodb __ref) throws Exception{
RDebugUtils.currentModule="i18nxlstodb";
if (Debug.shouldDelegate(ba, "truncatei18ntable", false))
	 {Debug.delegate(ba, "truncatei18ntable", null); return;}
ResumableSub_TruncateI18nTable rsub = new ResumableSub_TruncateI18nTable(this,__ref);
rsub.resume(ba, null);
}
public static class ResumableSub_TruncateI18nTable extends BA.ResumableSub {
public ResumableSub_TruncateI18nTable(nl.pdeg.irp1.i18nxlstodb parent,nl.pdeg.irp1.i18nxlstodb __ref) {
this.parent = parent;
this.__ref = __ref;
this.__ref = parent;
}
nl.pdeg.irp1.i18nxlstodb __ref;
nl.pdeg.irp1.i18nxlstodb parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="i18nxlstodb";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="Starter.i18nSql.ExecNonQuery($\"DELETE FROM i18n_m";
parent._starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(("DELETE FROM i18n_messages"));
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="Sleep(1000)";
parent.__c.Sleep(parent.getActivityBA(),new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "i18nxlstodb", "truncatei18ntable"),(int) (1000));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
}