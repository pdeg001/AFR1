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
	If Starter.enableDebugMessages = False Then Return
	#if debug
		Dim postMsg As String = $"[Date $DateTime{DateTime.Now}] "$
		Log($"${postMsg}${message}"$)
	#end if	
End Sub