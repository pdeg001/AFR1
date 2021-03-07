package nl.pdeg.irp1;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class i18nxlstodb_subs_0 {


public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Dim sql As SQL = Starter.i18nSql";
i18nxlstodb._sql = i18nxlstodb._starter._i18nsql /*RemoteObject*/ ;__ref.setField("_sql",i18nxlstodb._sql);
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _getxml(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("GetXml (i18nxlstodb) ","i18nxlstodb",3,__ref.getField(false, "ba"),__ref,18);
if (RapidSub.canDelegate("getxml")) { return __ref.runUserSub(false, "i18nxlstodb","getxml", __ref);}
RemoteObject _workbook = RemoteObject.declareNull("anywheresoftware.b4a.objects.WorkbookWrapper");
RemoteObject _sheet = RemoteObject.declareNull("anywheresoftware.b4a.objects.WorkbookWrapper.SheetWrapper");
RemoteObject _qry = RemoteObject.createImmutable("");
int _row = 0;
 BA.debugLineNum = 18;BA.debugLine="Public Sub GetXml";
Debug.ShouldStop(131072);
 BA.debugLineNum = 19;BA.debugLine="Dim workbook As ReadableWorkbook";
Debug.ShouldStop(262144);
_workbook = RemoteObject.createNew ("anywheresoftware.b4a.objects.WorkbookWrapper");Debug.locals.put("workbook", _workbook);
 BA.debugLineNum = 20;BA.debugLine="Dim sheet As ReadableSheet";
Debug.ShouldStop(524288);
_sheet = RemoteObject.createNew ("anywheresoftware.b4a.objects.WorkbookWrapper.SheetWrapper");Debug.locals.put("sheet", _sheet);
 BA.debugLineNum = 21;BA.debugLine="Dim qry As String";
Debug.ShouldStop(1048576);
_qry = RemoteObject.createImmutable("");Debug.locals.put("qry", _qry);
 BA.debugLineNum = 23;BA.debugLine="qry = $\"INSERT INTO i18n_messages 	(message_key,";
Debug.ShouldStop(4194304);
_qry = (RemoteObject.concat(RemoteObject.createImmutable("INSERT INTO i18n_messages\n"),RemoteObject.createImmutable("	(message_key, message_language, message_value) VALUES\n"),RemoteObject.createImmutable("	(?, ?, ?)")));Debug.locals.put("qry", _qry);
 BA.debugLineNum = 27;BA.debugLine="workbook.Initialize(Starter.filesFolder, Starter.";
Debug.ShouldStop(67108864);
_workbook.runVoidMethod ("Initialize",(Object)(i18nxlstodb._starter._filesfolder /*RemoteObject*/ ),(Object)(i18nxlstodb._starter._i18nxls /*RemoteObject*/ ));
 BA.debugLineNum = 28;BA.debugLine="sheet = workbook.GetSheet(0)";
Debug.ShouldStop(134217728);
_sheet = _workbook.runMethod(false,"GetSheet",(Object)(BA.numberCast(int.class, 0)));Debug.locals.put("sheet", _sheet);
 BA.debugLineNum = 30;BA.debugLine="Starter.i18nSql.BeginTransaction";
Debug.ShouldStop(536870912);
i18nxlstodb._starter._i18nsql /*RemoteObject*/ .runVoidMethod ("BeginTransaction");
 BA.debugLineNum = 32;BA.debugLine="For row = 1 To sheet.RowsCount -1";
Debug.ShouldStop(-2147483648);
{
final int step8 = 1;
final int limit8 = RemoteObject.solve(new RemoteObject[] {_sheet.runMethod(true,"getRowsCount"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_row = 1 ;
for (;(step8 > 0 && _row <= limit8) || (step8 < 0 && _row >= limit8) ;_row = ((int)(0 + _row + step8))  ) {
Debug.locals.put("row", _row);
 BA.debugLineNum = 33;BA.debugLine="Starter.i18nSql.ExecNonQuery2(qry, Array As Stri";
Debug.ShouldStop(1);
i18nxlstodb._starter._i18nsql /*RemoteObject*/ .runVoidMethod ("ExecNonQuery2",(Object)(_qry),(Object)(i18nxlstodb.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {3},new Object[] {_sheet.runMethod(true,"GetCellValue",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, _row))),_sheet.runMethod(true,"GetCellValue",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, _row))),_sheet.runMethod(true,"GetCellValue",(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, _row)))})))));
 }
}Debug.locals.put("row", _row);
;
 BA.debugLineNum = 38;BA.debugLine="Starter.i18nSql.TransactionSuccessful";
Debug.ShouldStop(32);
i18nxlstodb._starter._i18nsql /*RemoteObject*/ .runVoidMethod ("TransactionSuccessful");
 BA.debugLineNum = 39;BA.debugLine="Starter.i18nSql.EndTransaction";
Debug.ShouldStop(64);
i18nxlstodb._starter._i18nsql /*RemoteObject*/ .runVoidMethod ("EndTransaction");
 BA.debugLineNum = 40;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba) throws Exception{
try {
		Debug.PushSubsStack("Initialize (i18nxlstodb) ","i18nxlstodb",3,__ref.getField(false, "ba"),__ref,5);
if (RapidSub.canDelegate("initialize")) { return __ref.runUserSub(false, "i18nxlstodb","initialize", __ref, _ba);}
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
 BA.debugLineNum = 5;BA.debugLine="Public Sub Initialize";
Debug.ShouldStop(16);
 BA.debugLineNum = 6;BA.debugLine="If sql.IsInitialized = False Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",__ref.getField(false,"_sql" /*RemoteObject*/ ).runMethod(true,"IsInitialized"),i18nxlstodb.__c.getField(true,"False"))) { 
 BA.debugLineNum = 7;BA.debugLine="Starter.InitI18nSql";
Debug.ShouldStop(64);
i18nxlstodb._starter.runVoidMethod ("_initi18nsql" /*RemoteObject*/ );
 };
 BA.debugLineNum = 10;BA.debugLine="TruncateI18nTable";
Debug.ShouldStop(512);
__ref.runClassMethod (nl.pdeg.irp1.i18nxlstodb.class, "_truncatei18ntable" /*void*/ );
 BA.debugLineNum = 11;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _truncatei18ntable(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("TruncateI18nTable (i18nxlstodb) ","i18nxlstodb",3,__ref.getField(false, "ba"),__ref,13);
if (RapidSub.canDelegate("truncatei18ntable")) { __ref.runUserSub(false, "i18nxlstodb","truncatei18ntable", __ref); return;}
ResumableSub_TruncateI18nTable rsub = new ResumableSub_TruncateI18nTable(null,__ref);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_TruncateI18nTable extends BA.ResumableSub {
public ResumableSub_TruncateI18nTable(nl.pdeg.irp1.i18nxlstodb parent,RemoteObject __ref) {
this.parent = parent;
this.__ref = __ref;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
RemoteObject __ref;
nl.pdeg.irp1.i18nxlstodb parent;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("TruncateI18nTable (i18nxlstodb) ","i18nxlstodb",3,__ref.getField(false, "ba"),__ref,13);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
Debug.locals.put("_ref", __ref);
 BA.debugLineNum = 14;BA.debugLine="Starter.i18nSql.ExecNonQuery($\"DELETE FROM i18n_m";
Debug.ShouldStop(8192);
parent._starter._i18nsql /*RemoteObject*/ .runVoidMethod ("ExecNonQuery",(Object)((RemoteObject.createImmutable("DELETE FROM i18n_messages"))));
 BA.debugLineNum = 15;BA.debugLine="Sleep(1000)";
Debug.ShouldStop(16384);
parent.__c.runVoidMethod ("Sleep",__ref.runMethod(false,"getActivityBA"),anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "i18nxlstodb", "truncatei18ntable"),BA.numberCast(int.class, 1000));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 16;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
}