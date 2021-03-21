B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Private player As SimpleExoPlayer
	Public isPLaying As Boolean
End Sub

Public Sub Initialize
	'clsIcyData.Initialize
End Sub

Private Sub InitializePlayer
	player.Initialize("PLAYER")
	
End Sub

Public Sub playStreamUrl(streamUrl As String)
	Starter.clsIcyData.streamUrl = streamUrl
	If NoStreamUrlPassed = False Then Return
	InitializePlayer
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
		Starter.clsIcyData.lastIcyData = ""
		Starter.clsIcyData.IcyDataHasChanged = False
		
		Starter.clsIcyData.enableTimer(False)
		Sleep(200)
		CallSubDelayed2(searchStation, "SetNowPlaying", "")
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