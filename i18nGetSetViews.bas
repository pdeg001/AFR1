B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
#IgnoreWarnings: 12, 9
Sub Class_Globals
'	Private i18nActivity As Activity
	Private locale As String = Starter.locale
	Private rs As ResultSet
	Private sql As SQL = Starter.i18nSql
	Private qry As String
	Private lbl As Label
	Private edt As EditText
	Private pnl As Panel
	Private i18nReturnvalue As String
	Private tmpB4XFloatTextField As B4XFloatTextField
End Sub

Public Sub Initialize
End Sub

Private Sub GetI18nString(i18NString As String) As String
	qry = $"SELECT message_value FROM i18n_messages
WHERE 
LOWER(message_language) = ?
AND
message_key = ?"$

	rs = sql.ExecQuery2(qry, Array As String(locale.ToLowerCase, i18NString))
	i18nReturnvalue = sql.ExecQuerySingleResult2(qry, Array As String(locale.ToLowerCase, i18NString))
	If i18nReturnvalue = Null Then
		Return "i18N Unknown"
	Else
		Return i18nReturnvalue
	End If
End Sub

Public Sub GetViewsSeti18N (act As Activity)
	For Each v As View In act.GetAllViewsRecursive
		If v Is Label Then 'this will catch all of Label subclasses which includes EditText, Button and others
			lbl = v
			If lbl.Text.IndexOf("i18n") > -1 Then
				lbl.Text = GetI18nString(lbl.Text)
				Continue
			End If
		End If
		
		If v Is EditText Then
			edt = v
			If edt.Hint.IndexOf("i18n") > -1 Then
				edt.Hint = GetI18nString(edt.Hint)
				Continue
			End If
		End If
		
		If v Is Panel Then
			pnl = v
			If v.Tag Is B4XFloatTextField Then
				tmpB4XFloatTextField = v.Tag
				tmpB4XFloatTextField.HintText = GetI18nString(tmpB4XFloatTextField.HintText)
				tmpB4XFloatTextField.Update
			End If
		End If
	Next
End Sub

Public Sub GetI18nValueFromString(value As String) As String
	Return	GetI18nString(value)
End Sub
