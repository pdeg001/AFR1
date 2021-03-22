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
	Private getIcyDataTries As Int = 0
	Private hasIcyData As Boolean
	Public lastIcyData As String
	Public IcyDataHasChanged As Boolean
	Public streamUrl As String
End Sub

Public Sub Initialize
	icyTimer.Initialize("ICYTIMER", icyTimerTimeOut)
	enableTimer(False)
End Sub

Public Sub enableTimer(enable As Boolean)
	hasIcyData = False
	icyTimer.Enabled = enable
End Sub

Private Sub ICYTIMER_Tick
	If cmGenFunctions.ExoPLayerIsPlaying = False Then 
		Sleep(500)
		Return
	End If
	If hasIcyData = False Then
		If getIcyDataTries = 3 Then
			enableTimer(False)
			CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, Starter.clsi18nVar.GetI18nValueFromString( "i18n.no_station_information"))
			getIcyDataTries = 0
			Return
		End If
	Else
		'CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, Starter.clsi18nVar.GetI18nValueFromString( "i18n.retrieve_station_information"))
	End If
	
	GetIcyDataFromUrl
	getIcyDataTries = getIcyDataTries + 1
End Sub

Public Sub GetIcyDataFromUrl
	Dim url As String = $"http://ice.pdeg.nl/getIcy.php?url=${streamUrl}"$
	Dim job As HttpJob
	
'	Log(url)
	IcyDataHasChanged = False
	job.Initialize("", Me)
	job.Download(url)
	job.GetRequest.Timeout = httpTimeOut
	
	Wait For (job) JobDone(job As HttpJob)
	If job.Success Then
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
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, Starter.clsi18nVar.GetI18nValueFromString( "i18n.no_station_information"))
		job.Release
	End If
	
End Sub

Private Sub IcyDataChanged(icyMetaData As String)
	cmGenFunctions.logDebug(parseIcy(icyMetaData))
	Dim icyArtist As String = parseIcy(icyMetaData)
	If icyArtist = "err" Then Return
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
	
	hasIcyData = True
	
	If metaData.IndexOf("error") <> -1 Then
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, Starter.clsi18nVar.GetI18nValueFromString( "i18n.no_station_information"))
		hasIcyData = False
		enableTimer(False)
		Return "err"
	End If
	
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