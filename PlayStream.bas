B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Private player As SimpleExoPlayer
End Sub

Public Sub Initialize
	player.Initialize("PLAYER")
End Sub

Public Sub playStream(streamUrl As String)
	player.Prepare(player.CreateURISource(streamUrl))
	player.Play
End Sub

Public Sub StopStream
	If player.IsPlaying Then player.Release
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