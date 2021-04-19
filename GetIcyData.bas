B4A=true
Group=classes
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
#IgnoreWarnings: 9, 12
Sub Class_Globals
	Type icyDataList(icy_by As String, icy_name As String, icy_playing As String, icy_genre As String, _
				 icy_br As String, icy_url As String, icy_maint As Int)
	
	Public lstIcyData As icyDataList
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
		If getIcyDataTries >= 3 Then
			'enableTimer(False)
			CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, cmGenFunctions.Geti18NFromString( "i18n.no_station_information"))
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
	
	IcyDataHasChanged = False
	job.Initialize("", Me)
	job.Download(url)
	job.GetRequest.Timeout = httpTimeOut
	
	Wait For (job) JobDone(job As HttpJob)
	If job.Success Then
		Dim icyData As String = job.GetString 
		'cmGenFunctions.logDebug(icyData)
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
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, cmGenFunctions.Geti18NFromString("i18n.no_station_information"))
		job.Release
	End If
	
End Sub

Private Sub IcyDataChanged(icyMetaData As String)
	Dim icyArtist As String = parseIcy(icyMetaData)
	'If icyArtist = "err" Then Return
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, lstIcyData.icy_playing)
	If IcyDataHasChanged = True Then
		'the calling activity and callback must be set in Starter as string
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, lstIcyData.icy_playing)
	Else
		Return
	End If
End Sub

Public Sub parseIcy(metaData As String) As String
	Dim icy_by, icy_name, icy_playing, icy_genre, icy_br, icy_url, icy_genre As String = ""
	Dim icy_maint As Int = 0
	
	ResetIcyList	 
	
	If CheckMetaDataValid(metaData) = "err" Then Return "err"
	
	Dim parser As JSONParser
	
	lstIcyData.Initialize
	parser.Initialize(metaData)

	Try
		Dim root As Map = parser.NextObject
	Catch
		Return cmGenFunctions.Geti18NFromString("i18n.no_station_information")
	End Try
	
	hasIcyData = True
	
	icy_by = root.Get("icy-by")
	icy_name = root.Get("icy-name")
	icy_playing = root.Get("icy-playing")
	icy_genre = root.Get("icy-genre")
	icy_br  = root.Get("icy-br")
	icy_url = root.Get("icy-url")
	
	If root.ContainsKey("icy-maint") Then
		icy_maint = root.Get("icy-maint")
	End If
	
	'there is icy data but icy_playing is empty
	If icy_playing = "" Then
		icy_playing = $"${cmGenFunctions.Geti18NFromString("i18n.no_playing_information")}"$
	End If
	
	lstIcyData = CreateicyDataList(icy_br, icy_name,icy_playing, icy_genre, icy_br, icy_url, icy_maint)
	Return icy_playing

End Sub

Public Sub ResetIcyList
	lstIcyData = CreateicyDataList("", "", cmGenFunctions.Geti18NFromString("i18n.no_station_information"), "", "", "", 0)
End Sub

Private Sub CheckMetaDataValid(metaData As String) As String
	If metaData.IndexOf("error") <> -1 And getIcyDataTries >= 3 Then
		CallSub2($"${Starter.icyCallingActivity}"$, Starter.icyCallingActivityCallback, Starter.clsi18nVar.GetI18nValueFromString( "i18n.no_station_information"))
'		hasIcyData = False
'		enableTimer(False)
		Return "err"
		getIcyDataTries = 0
	End If

	Return ""
End Sub

Public Sub CreateicyDataList (icy_by As String, icy_name As String, icy_playing As String, icy_genre As String, icy_br As String, icy_url As String, icy_maint As Int) As icyDataList
	Dim t1 As icyDataList
	t1.Initialize
	t1.icy_by = icy_by
	t1.icy_name = icy_name
	t1.icy_playing = icy_playing
	t1.icy_genre = icy_genre
	t1.icy_br = icy_br
	t1.icy_url = icy_url
	t1.icy_maint = icy_maint
	Return t1
End Sub