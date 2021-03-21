B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
#IgnoreWarnings: 9, 12
Sub Class_Globals
	Private icyTimer As Timer
	Private httpTimeOut As Int = 6*1000
	Private icyTimerTimeOut As Int = 5*1000
	Public lastIcyData As String
	Public IcyDataHasChanged As Boolean
	Public streamUrl As String
	

End Sub

Public Sub Initialize
	icyTimer.Initialize("ICYTIMER", icyTimerTimeOut)
	enableTimer(False)
End Sub

Public Sub enableTimer(enable As Boolean)
	icyTimer.Enabled = enable
End Sub

Private Sub ICYTIMER_Tick
	GetIcyDataFromUrl
End Sub

Public Sub GetIcyDataFromUrl
	Dim url As String = $"http://ice.pdeg.nl/getIcy.php?url=${streamUrl}"$
	Dim job As HttpJob
	
	IcyDataHasChanged = False
	job.Initialize("", Me)
	job.Download(url)
	job.GetRequest.Timeout = httpTimeOut
	
	Wait For (job) JobDone(job As HttpJob)
	If job.Success Then
		Log("SUCCESS")
		Dim icyData As String = job.GetString 

		If icyData <> lastIcyData Then
			lastIcyData = icyData
			IcyDataHasChanged = True
			job.Release
			IcyDataChanged(icyData)
		Else
			job.Release
		End If
	Else
		lastIcyData = ""
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, "PPP")
		job.Release
	End If
	
End Sub

Private Sub IcyDataChanged(icyMetaData As String)
	cmGenFunctions.logDebug(parseIcy(icyMetaData))
	If IcyDataHasChanged = True Then
		'CallSub2(searchStation, "SetNowPlaying", parseIcy(lastIcyData))
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, parseIcy(icyMetaData))
	Else
		Return
		CallSub2(searchStation, "SetNowPlaying", "")
			
	End If
End Sub

Public Sub parseIcy(metaData As String) As String
	Dim icy_by, icy_name, icy_playing, icy_genre, icy_br, icy_url, icy_genre As String = ""
	Dim icy_maint As Int
	
	Dim parser As JSONParser
	parser.Initialize(metaData)

	Try
		Dim root As Map = parser.NextObject
	Catch
		Return Starter.clsi18nVar.GetI18nValueFromString( "i18n.no_station_information")
	End Try
	
	icy_by = root.Get("icy-by")
	icy_name = root.Get("icy-name")
	icy_playing = root.Get("icy-playing")
	icy_genre = root.Get("icy-genre")
	icy_br  = root.Get("icy-br")
	icy_url = root.Get("icy-url")
	
	If root.ContainsKey("icy-maint") Then
		icy_maint = root.Get("icy-maint")
	End If
	
	Return icy_playing

End Sub