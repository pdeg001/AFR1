B4A=true
Group=classes
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Type lblPlayStation (lbl As Label, act As String, callBackStart As String, callBackStop As String, stream As String)
	Private player As SimpleExoPlayer
	Private msStartStopSTream As Long
	Public lblPlayStationPlaying As lblPlayStation
	Public isPLaying As Boolean
End Sub

Public Sub Initialize
End Sub

Private Sub InitializePlayer
	player.Initialize("player")
	
End Sub

Public Sub playStreamUrl(streamUrl As String)
	streamUrl = lblPlayStationPlaying.lbl.Tag
	Starter.clsIcyData.streamUrl = streamUrl
	If NoStreamUrlPassed = False Then Return
	 
	Try
		InitializePlayer

		If IsExoticExtension(streamUrl) Then
			player.Prepare(player.CreateHLSSource(streamUrl))
		Else
			player.Prepare(player.CreateURISource(streamUrl))
		End If

		player.Play
		Starter.clsIcyData.enableTimer(True)
		Starter.clsIcyData.GetIcyDataFromUrl
	Catch
		cmGenFunctions.logDebug(">>" &LastException.Message)
		Starter.playerStatus = "error"
	End Try
	Sleep(1000)
End Sub

Private Sub IsExoticExtension (url As String) As Boolean
	If url.IndexOf(".m3u") > -1 Then Return True
	If url.IndexOf(".ogg") > -1 Then Return True
'	If url.IndexOf("=pls") > -1 Then Return True
	
	Return False
End Sub

Public Sub StopStream
	If cmGenFunctions.ExoPLayerIsPlaying = False Then Return
	player.Release
	Starter.clsIcyData.enableTimer(False)
	Starter.phReleaseKeepAlive
	Sleep(200)

	'check player status is "error"
	If Starter.playerStatus = "error" Then
		Return
	End If
	
	If player.IsPlaying Then
		playStreamUrl("")
		Starter.clsIcyData.lastIcyData = ""
		Starter.clsIcyData.IcyDataHasChanged = False
		Starter.playerStatus = "not playing"
	Else
		
	End If
End Sub

Sub player_Ready
	Starter.phKeepAlive
	Starter.playerStatus = "ready"
	cmGenFunctions.logDebug("Ready")
End Sub

Sub player_Error (Message As String)
	cmGenFunctions.logDebug(">> "& Message)
	Starter.playerStatus = "error"
	Starter.clsIcyData.ResetIcyList
	Starter.clsIcyData.lstIcyData.icy_playing = "Error playing stream"
	StopStream
	IsLabelKnown
	ToastMessageShow(Starter.clsi18nVar.GetI18nValueFromString("i18n.unable_to_play_stream"), False)
End Sub

Sub player_Complete
	Starter.playerStatus = "complete"
	cmGenFunctions.logDebug("complete")
End Sub

Private Sub NoStreamUrlPassed As Boolean
	If Starter.clsIcyData.streamUrl = "" Then
		If player.IsPlaying Then player.Release
		Starter.clsIcyData.enableTimer(False)
		Return False
	End If
	Return True
End Sub

Public Sub IsLabelKnown As String
	
	Dim msStopStreamDiff ,msStopStreamDuration As Long = DateTime.Now
	
	
	Dim currLabel As Label
	'nothing is playing
	If lblPlayStationPlaying.IsInitialized = False Or lblPlayStationPlaying.lbl.IsInitialized = False Then Return ""
	'reset labels
	CallSub($"${lblPlayStationPlaying.act}"$, $"${lblPlayStationPlaying.callBackStop}"$)
	'stop the ICY timer
	Starter.clsIcyData.enableTimer(False)
	'stop the stream
	StopStream
	msStartStopSTream = DateTime.Now
	Do While cmGenFunctions.ExoPLayerIsPlaying= True Or msStopStreamDiff < 0 Or msStopStreamDiff >= 2000
		msStopStreamDuration = DateTime.Now
		msStopStreamDiff = msStopStreamDuration-msStartStopSTream
	Loop
	Log($"$DateTime{DateTime.Now}  ${msStopStreamDuration-msStartStopSTream}"$)
	
	
	currLabel = lblPlayStationPlaying.lbl
	CreateLblPlayStation (Null, Null, Null, Null, Null)
	Return currLabel.Tag
End Sub

Public Sub StartLabelStream
	If cmGenFunctions.ExoPLayerIsPlaying Then player.Release
	playStreamUrl("")
	CallSub2($"${lblPlayStationPlaying.act}"$, $"${lblPlayStationPlaying.callBackStart}"$, "")
End Sub

Public Sub CreateLblPlayStation (lbl As Label, act As String, callBackStart As String, callBackStop As String, stream As String)
	Dim t1 As lblPlayStation
	t1.Initialize
	t1.lbl = lbl
	t1.act = act
	t1.callBackStart = callBackStart
	t1.callBackStop = callBackStop
	t1.stream = stream
	lblPlayStationPlaying = t1
End Sub