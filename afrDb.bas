B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
Sub Class_Globals
	Private sql As SQL
	Private qry As String
	Private rs As ResultSet
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
	sql = Starter.rdoSql
	
End Sub

Public Sub IsDbInitialized
	If sql.IsInitialized = False Then
		Starter.InitRdoDb
	End If
End Sub

Public Sub GetParamValue(param As String) As String
	IsDbInitialized
	Dim result As String
	
	qry = $"SELECT param_value FROM params WHERE param_name = ?;"$
	
	rs = sql.ExecQuery2(qry, Array As String(param))
	
	Do While rs.NextRow
		result = rs.GetString("param_value")
	Loop

	If result = "" Then
		AddParam(param)
		Return "01"
	Else 
		Return result
	End If
	
	
End Sub

Private Sub AddParam(param_name As String)
	IsDbInitialized
	qry = "INSERT INTO params (param_name, param_value) VALUES (?, ?);"
	
	sql.ExecNonQuery2(qry, Array As String(param_name, 0))
	
	
End Sub