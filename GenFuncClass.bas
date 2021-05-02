B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Dim drBtnYes, drBtnCancel, drBtnNo As String
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
	
End Sub

Sub GenYNCMess(msg As String, buttons As Int) As ResumableSub
	SetDrButtonText(buttons)
	
	Msgbox2Async(msg, Application.LabelName, drBtnYes, drBtnCancel, drBtnNo, Application.Icon, False)
	Wait For Msgbox_Result (Result As Int)
	If Result = DialogResponse.POSITIVE Then
		Return True
	Else If Result = DialogResponse.NEGATIVE Then
		Return False
	End If
	Return False
End Sub

Private Sub SetDrButtonText(buttons As Int)
	ResetDrButtons
	
	'OK button
	If buttons = 1 Then
		drBtnYes = cmGenFunctions.Geti18NFromString("i18n.btn_ok")
	Else If	buttons = 2 Then
		drBtnYes = cmGenFunctions.Geti18NFromString("i18n.btn_yes")
		drBtnNo = cmGenFunctions.Geti18NFromString("i18n.btn_no")
	Else If	buttons = 3 Then
		drBtnYes = cmGenFunctions.Geti18NFromString("i18n.btn_yes")
		drBtnNo = cmGenFunctions.Geti18NFromString("i18n.btn_no")
		drBtnCancel = cmGenFunctions.Geti18NFromString("i18n.cancel")
	End If
	
End Sub

Private Sub ResetDrButtons
	drBtnYes = ""
	drBtnCancel = ""
	drBtnNo = ""
End Sub