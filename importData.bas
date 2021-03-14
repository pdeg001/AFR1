B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
Sub Class_Globals
	Dim lstStation As List
	Dim sql As SQL
	Dim stName As String
End Sub

Public Sub Initialize
	sql = Starter.rdoSql
End Sub


Public Sub ProcessXls As ResumableSub
	Dim workbook As ReadableWorkbook
	Dim sheet As ReadableSheet
	lstStation.Initialize
	workbook.Initialize(Starter.filesFolder, Starter.xlsFileName)
	sheet = workbook.GetSheet(0)
	
	For row = 1 To sheet.RowsCount -1
		stName = sheet.GetCellValue(0, row)
		If stName = "-" Or stName.IndexOf("http") > -1 Then
			Continue
		End If
		lstStation.Add(CreatestationList(sheet.GetCellValue(0, row), _
										  sheet.GetCellValue(1, row), _
										  sheet.GetCellValue(2, row), _
										  sheet.GetCellValue(3, row), _
										  sheet.GetCellValue(4, row), _
										  sheet.GetCellValue(5, row), _
										  sheet.GetCellValue(6, row), _
										  sheet.GetCellValue(7, row)))
	Next
	Log($"xls done"$)
	wait for (AddStationsToDb) Complete (result As Boolean)
	Log($"db done"$)
	File.Delete(Starter.filesFolder, Starter.xlsFileName)
	Log($"all done"$)
	Return True
End Sub

Private Sub AddStationsToDb As ResumableSub
	Dim qry As String
	sql.BeginTransaction
	sql.ExecNonQuery("DELETE FROM rdolist")
	sql.TransactionSuccessful
	sql.EndTransaction
	Sleep(1000)
	Log($"START : $Time{DateTime.Now}"$)
	
	
	qry = $"INSERT INTO rdolist (stname, description, genre, country, language, stream1, stream2, stream3)
	VALUES (?, ?, ?, ?, ?, ?, ?, ?)"$
	
	sql.BeginTransaction
	For Each station As stationList In lstStation
		
		sql.ExecNonQuery2(qry, Array As String(station.station_name, station.station_descr, _
												station.station_genre, station.station_country, _
												station.station_language, station.station_url1, _
												station.station_url2, station.station_url3))
	Next
	
	sql.TransactionSuccessful
	sql.EndTransaction
	Log($"END : $Time{DateTime.Now}"$)
	
	Return True
End Sub


Public Sub CreatestationList (station_name As String, station_descr As String, _
							    station_genre As String, station_country As String, _
								station_language As String, _
								station_url1 As String, station_url2 As String, station_url3 As String) As stationList
	Dim t1 As stationList
	t1.Initialize
	t1.station_name = station_name
	t1.station_descr = station_descr
	t1.station_genre = station_genre
	t1.station_country = station_country
	t1.station_language = station_language
	t1.station_url1 = station_url1
	t1.station_url2 = station_url2
	t1.station_url3 = station_url3
	Return t1
End Sub