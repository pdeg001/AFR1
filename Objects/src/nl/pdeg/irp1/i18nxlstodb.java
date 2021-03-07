package nl.pdeg.irp1;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class i18nxlstodb extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "nl.pdeg.irp1.i18nxlstodb");
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

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.sql.SQL _sql = null;
public b4a.example.dateutils _dateutils = null;
public nl.pdeg.irp1.main _main = null;
public nl.pdeg.irp1.starter _starter = null;
public nl.pdeg.irp1.xuiviewsutils _xuiviewsutils = null;
public nl.pdeg.irp1.httputils2service _httputils2service = null;
public nl.pdeg.irp1.b4xcollections _b4xcollections = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Dim sql As SQL = Starter.i18nSql";
_sql = _starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ ;
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return "";
}
public String  _getxml() throws Exception{
anywheresoftware.b4a.objects.WorkbookWrapper _workbook = null;
anywheresoftware.b4a.objects.WorkbookWrapper.SheetWrapper _sheet = null;
String _qry = "";
int _row = 0;
 //BA.debugLineNum = 18;BA.debugLine="Public Sub GetXml";
 //BA.debugLineNum = 19;BA.debugLine="Dim workbook As ReadableWorkbook";
_workbook = new anywheresoftware.b4a.objects.WorkbookWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim sheet As ReadableSheet";
_sheet = new anywheresoftware.b4a.objects.WorkbookWrapper.SheetWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim qry As String";
_qry = "";
 //BA.debugLineNum = 23;BA.debugLine="qry = $\"INSERT INTO i18n_messages 	(message_key,";
_qry = ("INSERT INTO i18n_messages\n"+"	(message_key, message_language, message_value) VALUES\n"+"	(?, ?, ?)");
 //BA.debugLineNum = 27;BA.debugLine="workbook.Initialize(Starter.filesFolder, Starter.";
_workbook.Initialize(_starter._filesfolder /*String*/ ,_starter._i18nxls /*String*/ );
 //BA.debugLineNum = 28;BA.debugLine="sheet = workbook.GetSheet(0)";
_sheet = _workbook.GetSheet((int) (0));
 //BA.debugLineNum = 30;BA.debugLine="Starter.i18nSql.BeginTransaction";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 32;BA.debugLine="For row = 1 To sheet.RowsCount -1";
{
final int step8 = 1;
final int limit8 = (int) (_sheet.getRowsCount()-1);
_row = (int) (1) ;
for (;_row <= limit8 ;_row = _row + step8 ) {
 //BA.debugLineNum = 33;BA.debugLine="Starter.i18nSql.ExecNonQuery2(qry, Array As Stri";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(_qry,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_sheet.GetCellValue((int) (0),_row),_sheet.GetCellValue((int) (1),_row),_sheet.GetCellValue((int) (2),_row)}));
 }
};
 //BA.debugLineNum = 38;BA.debugLine="Starter.i18nSql.TransactionSuccessful";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 39;BA.debugLine="Starter.i18nSql.EndTransaction";
_starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 5;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 6;BA.debugLine="If sql.IsInitialized = False Then";
if (_sql.IsInitialized()==__c.False) { 
 //BA.debugLineNum = 7;BA.debugLine="Starter.InitI18nSql";
_starter._initi18nsql /*String*/ ();
 };
 //BA.debugLineNum = 10;BA.debugLine="TruncateI18nTable";
_truncatei18ntable();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public void  _truncatei18ntable() throws Exception{
ResumableSub_TruncateI18nTable rsub = new ResumableSub_TruncateI18nTable(this);
rsub.resume(ba, null);
}
public static class ResumableSub_TruncateI18nTable extends BA.ResumableSub {
public ResumableSub_TruncateI18nTable(nl.pdeg.irp1.i18nxlstodb parent) {
this.parent = parent;
}
nl.pdeg.irp1.i18nxlstodb parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 14;BA.debugLine="Starter.i18nSql.ExecNonQuery($\"DELETE FROM i18n_m";
parent._starter._i18nsql /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery(("DELETE FROM i18n_messages"));
 //BA.debugLineNum = 15;BA.debugLine="Sleep(1000)";
parent.__c.Sleep(parent.getActivityBA(),this,(int) (1000));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
