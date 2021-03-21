B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
#IgnoreWarnings: 12, 9
Sub Class_Globals
	Private locale As String = Starter.locale
	Private rs As ResultSet
	Private sql As SQL = Starter.i18nSql
	Private qry As String
	Private i18nReturnvalue As String
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

Public Sub GetI18nValueFromString(value As String) As String
	Return	GetI18nString(value)
End Sub
