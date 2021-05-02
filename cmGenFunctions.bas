B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=10.7
@EndOfDesignText@

Sub Process_Globals
	
End Sub

'E.g.
'GetPanelLabelValue("streamCount", clvStation.GetItemFromView(pnl), clvStation)
'if tag is not found then "-1" is returned
Public Sub GetPanelLabelValue(lblTag As String, index As Int, clv As CustomListView) As String
	Dim pPnl As Panel = clv.GetPanel(index)
	Dim cPnl As Panel
	Dim lbl As Label
	
	'loop clv parent panel
	For Each v As View In pPnl.GetAllViewsRecursive
		If v Is Panel Then
			cPnl = v
			'loop child panel
			For Each v As View In cPnl.GetAllViewsRecursive
				If v.Tag = lblTag Then
					lbl = v
					Return lbl.Text
				End If
			Next
		End If
	Next
	
	Return "-1"
End Sub

'only show log if in debug
Public Sub logDebug(message As String)
'	If Starter.enableDebugMessages = False Then Return
	#if debug
		Dim postMsg As String = $"[Date $DateTime{DateTime.Now}] "$
		Log($"${postMsg} ${message}"$)
	#end if	
End Sub

Sub ExoPLayerIsPlaying As Boolean
	Dim JO As JavaObject
	JO.InitializeContext
	Return JO.RunMethodJO("getSystemService",Array("audio")).RunMethod("isMusicActive",Null)
End Sub

Public Sub runMarquee(view As Label, txt As String, mode As String)
	view.Text = txt
	Dim r As Reflector
	r.Target = view
	r.RunMethod2("setLines", 1, "java.lang.int")
	r.RunMethod2("setHorizontallyScrolling", True, "java.lang.boolean")
	r.RunMethod2("setEllipsize", mode, "android.text.TextUtils$TruncateAt")
	r.RunMethod2("setHorizontalFadingEdgeEnabled", True, "java.lang.boolean")
	r.RunMethod2("setMarqueeRepeatLimit", -1, "java.lang.int")
	r.RunMethod2("setSelected", True, "java.lang.boolean")
End Sub

Public Sub Geti18NFromString(i18nString As String) As String
	Return Starter.clsi18nVar.GetI18nValueFromString(i18nString)
End Sub

Sub createCustomToast(txt As String, color As String)
	Dim cs As CSBuilder
	cs.Initialize.Typeface(Typeface.LoadFromAssets("galanogrotesquemedium.ttf")).Color(Colors.White).Size(16).Append(txt).PopAll
	ShowCustomToast(cs, False, color)
End Sub

Sub ShowCustomToast(Text As Object, LongDuration As Boolean, BackgroundColor As Int)
	Dim ctxt As JavaObject
	ctxt.InitializeContext
	Dim duration As Int
	If LongDuration Then duration = 1 Else duration = 0
	Dim toast As JavaObject
	toast = toast.InitializeStatic("android.widget.Toast").RunMethod("makeText", Array(ctxt, Text, duration))
	Dim v As View = toast.RunMethod("getView", Null)
	Dim cd As ColorDrawable
	cd.Initialize(BackgroundColor, 20dip)
	v.Background = cd
	'uncomment to show toast in the center:
	'  toast.RunMethod("setGravity", Array( _
	' Bit.Or(Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL), 0, 0))
	toast.RunMethod("show", Null)
End Sub

