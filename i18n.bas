B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
#IgnoreWarnings: 12, 9
Sub Class_Globals
	Private i18nActivity As Activity
	Private locale As String = Starter.locale
	Private rs As ResultSet
	Private sql As SQL = Starter.i18nSql
	Private qry As String
	Private lbl As Label
	Private edt As EditText
	Private i18nReturnvalue As String
End Sub

Public Sub Initialize (act As Activity)
	i18nActivity = act
End Sub

Private Sub GetI18nString(i18NString As String) As String
	Log(i18NString)
	
	qry = $"SELECT message_value FROM i18n_messages
WHERE 
LOWER(message_language) = ?
AND
message_key = ?"$

	rs = sql.ExecQuery2(qry, Array As String(locale.ToLowerCase, i18NString))
	i18nReturnvalue = sql.ExecQuerySingleResult2(qry, Array As String(locale.ToLowerCase, i18NString))
	Log($"i18nReturnValue : ${i18nReturnvalue}"$)
	If i18nReturnvalue = Null Then
		Return "i18N Unknown"
	Else
		Return i18nReturnvalue
	End If
'	If rs.RowCount <> 0 Then
'		Do While rs.NextRow
'			Return rs.GetString("message_value")
'		Loop
'	Else
'		Return "i18N Unknown"
'	End If
End Sub

Public Sub GetViewsSetLocale
	
	For Each v As View In i18nActivity.GetAllViewsRecursive
		If v Is Label Then 'this will catch all of Label subclasses which includes EditText, Button and others
			lbl = v
			If lbl.Text.IndexOf("i18n") > -1 Then
				lbl.Text = GetI18nString(lbl.Text)
			End If
		End If
		If v Is EditText Then
			edt = v
			If edt.Hint.IndexOf("i18n") > -1 Then
				edt.Hint = GetI18nString(edt.Hint)
			End If
		End If
	Next
End Sub

Public Sub GetFlag As Bitmap
	Return LoadBitmap(File.DirAssets, $"${Starter.country.ToLowerCase}.png"$)
End Sub