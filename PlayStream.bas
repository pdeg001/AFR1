B4A=true
Group=classes
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Private player As SimpleExoPlayer
	Public isPLaying As Boolean
End Sub

Public Sub Initialize
End Sub

Private Sub InitializePlayer
	player.Initialize("player")
	
End Sub

Public Sub playStreamUrl(streamUrl As String)
	cmGenFunctions.logDebug($"Stream url : ${streamUrl}"$)
	Starter.clsIcyData.streamUrl = streamUrl
	If NoStreamUrlPassed = False Then Return
	 
	Try
		InitializePlayer
		cmGenFunctions.logDebug("start stream")
		player.Prepare(player.CreateURISource(streamUrl))
		player.Play
		Starter.clsIcyData.enableTimer(True)
		Starter.clsIcyData.GetIcyDataFromUrl
	Catch
		cmGenFunctions.logDebug(LastException.Message)
		Starter.playerStatus = "error"
	End Try
	Sleep(1000)
End Sub

Public Sub StopStream
	'check player status is "error"
	If Starter.playerStatus = "error" Then
		Starter.clsIcyData.enableTimer(False)
		CallSubDelayed2($"${Starter.icyCallingActivity}"$, $"${Starter.icyCallingActivityCallback}"$, "np")
		player.Release
		Return
		
	End If
	
	If player.IsPlaying Then 
		player.Release
		playStreamUrl("")
		Starter.clsIcyData.lastIcyData = ""
		Starter.clsIcyData.IcyDataHasChanged = False
		Starter.clsIcyData.enableTimer(False)
		Starter.playerStatus = "not playing"
		Sleep(200)
		CallSubDelayed2($"${Starter.icyCallingActivity}"$, $"${Starter.icyCallingActivityCallback}"$, "")
		Starter.phReleaseKeepAlive
	Else
		
	End If
End Sub

Sub player_Ready
	Starter.phKeepAlive
	Starter.playerStatus = "ready"
	cmGenFunctions.logDebug("Ready")
End Sub

Sub player_Error (Message As String)
	cmGenFunctions.logDebug(Message)
	Starter.playerStatus = "error"
	Starter.clsIcyData.ResetIcyList
	Starter.clsIcyData.lstIcyData.icy_playing = "Error playing stream"
	StopStream
	
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