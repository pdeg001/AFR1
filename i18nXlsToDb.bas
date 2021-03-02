B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
Sub Class_Globals
	Dim sql As SQL = Starter.i18nSql
End Sub

Public Sub Initialize
	If sql.IsInitialized = False Then
		Starter.InitI18nSql
	End If
	
	TruncateI18nTable
End Sub

Private Sub TruncateI18nTable
	Starter.i18nSql.ExecNonQuery($"DELETE FROM i18n_messages"$)
	Sleep(1000)
End Sub

Public Sub GetXml
	Dim workbook As ReadableWorkbook
	Dim sheet As ReadableSheet
	Dim qry As String
	
	qry = $"INSERT INTO i18n_messages
	(message_key, message_language, message_value) VALUES
	(?, ?, ?)"$
	
	workbook.Initialize(Starter.filesFolder, Starter.i18nXls)
	sheet = workbook.GetSheet(0)
	
	Starter.i18nSql.BeginTransaction
	
	For row = 1 To sheet.RowsCount -1
		Starter.i18nSql.ExecNonQuery2(qry, Array As String(sheet.GetCellValue(0, row), _
														   sheet.GetCellValue(1, row), _
														   sheet.GetCellValue(2, row)))
	Next
	
	Starter.i18nSql.TransactionSuccessful
	Starter.i18nSql.EndTransaction
End Sub