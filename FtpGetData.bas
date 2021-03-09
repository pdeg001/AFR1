B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
Sub Class_Globals
	Private ftp As FTP
	Private clsImportData As ImportData
	Private fileName As String = "station.xls"
	Private dlCount As Int = 1
	Private clsI18n As i18nGetSetViews
End Sub

Public Sub Initialize
	clsI18n.Initialize
	ftp.PassiveMode = True
	ftp.Initialize("FTP","ftp.pdeg.nl", 21, "pdegrootafr", "hkWpXtB1!")
End Sub

Public Sub DownloadList
	Dim sf As Object = ftp.DownloadFile(fileName, False, Starter.filesFolder, fileName)
	Wait For (sf) FTP_DownloadCompleted (ServerPath As String, Success As Boolean)
	ftp.Close
	Log("DONE")
	CallSub2(Main, "UpdateLoadingIndicator", clsI18n.GetI18nValueFromString("i18n.download_done"))
	
End Sub

Sub FTP_DownloadProgress (ServerPath As String, TotalDownloaded As Long, Total As Long)
	Dim s As String
	s = $"${Round(TotalDownloaded / 1000)} KB"$
	If Total > 0 Then s = s & " out of " & Round(Total / 1000) & "KB"
	'Log(s)
	dlCount = dlCount +1
	If dlCount Mod 35 = 0 Then
		CallSub2(Main, "UpdateLoadingIndicator", s)
	End If
End Sub
Sub FTP_DownloadCompleted (ServerPath As String, Success As Boolean)
	If Success Then
		clsImportData.Initialize
		clsImportData.ProcessXls(fileName)
	End If
End Sub


Public Sub CreatestationList (station_name As String, station_descr As String, station_genre As String, station_country As String, station_language As String, station_url1 As String, station_url2 As String, station_url3 As String) As stationList
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