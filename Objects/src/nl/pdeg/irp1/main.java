package nl.pdeg.irp1;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "nl.pdeg.irp1", "nl.pdeg.irp1.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "nl.pdeg.irp1", "nl.pdeg.irp1.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "nl.pdeg.irp1.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public nl.pdeg.irp1.i18ngetsetviews _clsi18n = null;
public nl.pdeg.irp1.afrdb _clsdb = null;
public nl.pdeg.irp1.clscheckinternetconnection _clsinetconnected = null;
public nl.pdeg.irp1.ftpgetdata _clsftp = null;
public nl.pdeg.irp1.loadingindicator _clsloading = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgflag = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btncloseapp = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblheader = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public nl.pdeg.irp1.b4xfloattextfield _b4xfloattextfield1 = null;
public b4a.example.dateutils _dateutils = null;
public nl.pdeg.irp1.starter _starter = null;
public nl.pdeg.irp1.selectcountry _selectcountry = null;
public nl.pdeg.irp1.xuiviewsutils _xuiviewsutils = null;
public nl.pdeg.irp1.httputils2service _httputils2service = null;
public nl.pdeg.irp1.b4xcollections _b4xcollections = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (selectcountry.mostCurrent != null);
return vis;}
public static class _i18n{
public boolean IsInitialized;
public String countryTl;
public void Initialize() {
IsInitialized = true;
countryTl = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _stationlist{
public boolean IsInitialized;
public String station_name;
public String station_descr;
public String station_genre;
public String station_country;
public String station_language;
public String station_url1;
public String station_url2;
public String station_url3;
public void Initialize() {
IsInitialized = true;
station_name = "";
station_descr = "";
station_genre = "";
station_country = "";
station_language = "";
station_url1 = "";
station_url2 = "";
station_url3 = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 39;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 40;BA.debugLine="clsInetConnected.Initialize";
mostCurrent._clsinetconnected._initialize /*String*/ (processBA);
 //BA.debugLineNum = 41;BA.debugLine="clsI18n.Initialize";
mostCurrent._clsi18n._initialize /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 42;BA.debugLine="clsDb.Initialize";
mostCurrent._clsdb._initialize /*String*/ (processBA);
 //BA.debugLineNum = 43;BA.debugLine="CheckInetConnected";
_checkinetconnected();
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static void  _checkinetconnected() throws Exception{
ResumableSub_CheckInetConnected rsub = new ResumableSub_CheckInetConnected(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_CheckInetConnected extends BA.ResumableSub {
public ResumableSub_CheckInetConnected(nl.pdeg.irp1.main parent) {
this.parent = parent;
}
nl.pdeg.irp1.main parent;
boolean _result = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 56;BA.debugLine="Wait For (clsInetConnected.CheckConnected) Comple";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, parent.mostCurrent._clsinetconnected._checkconnected /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ ());
this.state = 11;
return;
case 11:
//C
this.state = 1;
_result = (Boolean) result[0];
;
 //BA.debugLineNum = 58;BA.debugLine="Starter.hasInternet = result";
parent.mostCurrent._starter._hasinternet /*boolean*/  = _result;
 //BA.debugLineNum = 60;BA.debugLine="If result = False Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_result==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 61;BA.debugLine="Activity.LoadLayout(\"main_no_internet\")";
parent.mostCurrent._activity.LoadLayout("main_no_internet",mostCurrent.activityBA);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 63;BA.debugLine="Activity.LoadLayout(\"main\")";
parent.mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 66;BA.debugLine="clsI18n.GetViewsSeti18N(Activity)";
parent.mostCurrent._clsi18n._getviewsseti18n /*String*/ (parent.mostCurrent._activity);
 //BA.debugLineNum = 67;BA.debugLine="imgFlag.Bitmap = clsI18n.GetFlag";
parent.mostCurrent._imgflag.setBitmap((android.graphics.Bitmap)(parent.mostCurrent._clsi18n._getflag /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ ().getObject()));
 //BA.debugLineNum = 69;BA.debugLine="If Starter.hasInternet Then";
if (true) break;

case 7:
//if
this.state = 10;
if (parent.mostCurrent._starter._hasinternet /*boolean*/ ) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 70;BA.debugLine="InitAfr";
_initafr();
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _complete(boolean _result) throws Exception{
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _getstationlist() throws Exception{
ResumableSub_GetStationList rsub = new ResumableSub_GetStationList(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_GetStationList extends BA.ResumableSub {
public ResumableSub_GetStationList(nl.pdeg.irp1.main parent) {
this.parent = parent;
}
nl.pdeg.irp1.main parent;
String _laststationdownload = "";
int _result = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 86;BA.debugLine="Dim lastStationDownload As String = clsDb.GetPara";
_laststationdownload = parent.mostCurrent._clsdb._getparamvalue /*String*/ ("laststationdownload");
 //BA.debugLineNum = 89;BA.debugLine="If lastStationDownload = \"0\" Then";
if (true) break;

case 1:
//if
this.state = 8;
if ((_laststationdownload).equals("0")) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 90;BA.debugLine="Msgbox2Async(clsI18n.GetI18nValueFromString(\"i18";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence(parent.mostCurrent._clsi18n._geti18nvaluefromstring /*String*/ ("i18n.get_station_list")),BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Application.getLabelName()),parent.mostCurrent._clsi18n._geti18nvaluefromstring /*String*/ ("i18n.btn_yes"),"",parent.mostCurrent._clsi18n._geti18nvaluefromstring /*String*/ ("i18n.btn_no"),(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 91;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 9;
return;
case 9:
//C
this.state = 4;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 92;BA.debugLine="If Result = DialogResponse.NEGATIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.NEGATIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 93;BA.debugLine="Msgbox2Async(clsI18n.GetI18nValueFromString(\"i1";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence(parent.mostCurrent._clsi18n._geti18nvaluefromstring /*String*/ ("i18n.application_will_be_closed")),BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Application.getLabelName()),parent.mostCurrent._clsi18n._geti18nvaluefromstring /*String*/ ("i18n.btn_ok"),"","",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 94;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 10;
return;
case 10:
//C
this.state = 7;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 95;BA.debugLine="Starter.closeApplication";
parent.mostCurrent._starter._closeapplication /*String*/ ();
 if (true) break;

case 7:
//C
this.state = 8;
;
 //BA.debugLineNum = 97;BA.debugLine="clsFtp.Initialize";
parent.mostCurrent._clsftp._initialize /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="clsFtp.DownloadList";
parent.mostCurrent._clsftp._downloadlist /*void*/ ();
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 100;BA.debugLine="Return True";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Private clsI18n As i18nGetSetViews";
mostCurrent._clsi18n = new nl.pdeg.irp1.i18ngetsetviews();
 //BA.debugLineNum = 27;BA.debugLine="Private clsDb As afrDb";
mostCurrent._clsdb = new nl.pdeg.irp1.afrdb();
 //BA.debugLineNum = 28;BA.debugLine="Private clsInetConnected As ClsCheckInternetConne";
mostCurrent._clsinetconnected = new nl.pdeg.irp1.clscheckinternetconnection();
 //BA.debugLineNum = 29;BA.debugLine="Private clsFtp As FtpGetData";
mostCurrent._clsftp = new nl.pdeg.irp1.ftpgetdata();
 //BA.debugLineNum = 30;BA.debugLine="Private clsLoading As LoadingIndicator";
mostCurrent._clsloading = new nl.pdeg.irp1.loadingindicator();
 //BA.debugLineNum = 32;BA.debugLine="Private imgFlag As ImageView";
mostCurrent._imgflag = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private btnCloseApp As Button";
mostCurrent._btncloseapp = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private lblHeader As Label";
mostCurrent._lblheader = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private EditText1 As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private B4XFloatTextField1 As B4XFloatTextField";
mostCurrent._b4xfloattextfield1 = new nl.pdeg.irp1.b4xfloattextfield();
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static void  _initafr() throws Exception{
ResumableSub_InitAfr rsub = new ResumableSub_InitAfr(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_InitAfr extends BA.ResumableSub {
public ResumableSub_InitAfr(nl.pdeg.irp1.main parent) {
this.parent = parent;
}
nl.pdeg.irp1.main parent;
boolean _result = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 76;BA.debugLine="clsLoading.Initialize(Activity)";
parent.mostCurrent._clsloading._initialize /*String*/ (mostCurrent.activityBA,parent.mostCurrent._activity);
 //BA.debugLineNum = 77;BA.debugLine="wait for (GetStationList) Complete (result As Boo";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _getstationlist());
this.state = 1;
return;
case 1:
//C
this.state = -1;
_result = (Boolean) result[0];
;
 //BA.debugLineNum = 79;BA.debugLine="Sleep(3000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (3000));
this.state = 2;
return;
case 2:
//C
this.state = -1;
;
 //BA.debugLineNum = 80;BA.debugLine="StartActivity(selectCountry)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._selectcountry.getObject()));
 //BA.debugLineNum = 81;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
main._process_globals();
starter._process_globals();
selectcountry._process_globals();
xuiviewsutils._process_globals();
httputils2service._process_globals();
b4xcollections._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Type i18n(countryTl As String)";
;
 //BA.debugLineNum = 17;BA.debugLine="Type stationList(station_name As String, station_";
;
 //BA.debugLineNum = 21;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static void  _updateloadingindicator(String _msg) throws Exception{
ResumableSub_UpdateLoadingIndicator rsub = new ResumableSub_UpdateLoadingIndicator(null,_msg);
rsub.resume(processBA, null);
}
public static class ResumableSub_UpdateLoadingIndicator extends BA.ResumableSub {
public ResumableSub_UpdateLoadingIndicator(nl.pdeg.irp1.main parent,String _msg) {
this.parent = parent;
this._msg = _msg;
}
nl.pdeg.irp1.main parent;
String _msg;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 104;BA.debugLine="clsLoading.ShowIndicator(msg)";
parent.mostCurrent._clsloading._showindicator /*void*/ (_msg);
 //BA.debugLineNum = 105;BA.debugLine="If msg = clsI18n.GetI18nValueFromString(\"i18n.dow";
if (true) break;

case 1:
//if
this.state = 4;
if ((_msg).equals(parent.mostCurrent._clsi18n._geti18nvaluefromstring /*String*/ ("i18n.download_done"))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 106;BA.debugLine="Sleep(2000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (2000));
this.state = 5;
return;
case 5:
//C
this.state = 4;
;
 //BA.debugLineNum = 107;BA.debugLine="clsLoading.HideIndicator";
parent.mostCurrent._clsloading._hideindicator /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ ();
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
}
