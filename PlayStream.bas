B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Private player As SimpleExoPlayer
	'Private clsIcyData As GetIcyData
End Sub

Public Sub Initialize
	player.Initialize("PLAYER")
	'clsIcyData.Initialize
End Sub

Public Sub playStreamUrl(streamUrl As String)
	Starter.clsIcyData.streamUrl = streamUrl
	If NoStreamUrlPassed = False Then Return
	Starter.phKeepAlive
	 
	player.Prepare(player.CreateURISource(streamUrl))
	player.Play
	Starter.clsIcyData.enableTimer(True)
	Starter.clsIcyData.GetIcyDataFromUrl
	Sleep(1000)
End Sub

Public Sub StopStream
	If player.IsPlaying Then 
		player.Release
		playStreamUrl("")
		Starter.phReleaseKeepAlive
	End If
End Sub

Sub PLAYER_Ready
	cmGenFunctions.logDebug("Ready")
	
End Sub

Sub PLAYER_Error (Message As String)
	cmGenFunctions.logDebug("Error: " & Message)
End Sub

Sub PLAYER_Complete
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