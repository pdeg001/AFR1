B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
#IgnoreWarnings: 9
Sub Class_Globals
	Private icyTimer As Timer
	Private httpTimeOut As Int = 10*1000
	Private icyTimerTimeOut As Int = 4*1000
	Private lastIcyData As String
	Private IcyDataHasChanged As Boolean
	Public streamUrl As String
End Sub

Public Sub Initialize
	icyTimer.Initialize("ICYTIMER", icyTimerTimeOut)
	enableTimer(False)
End Sub

Public Sub enableTimer(enable As Boolean)
	cmGenFunctions.logDebug(">>")
	icyTimer.Enabled = enable
	If enable = False Then
		lastIcyData = ""
	End If
	
End Sub

Private Sub ICYTIMER_Tick
	
	GetIcyDataFromUrl
End Sub

Public Sub GetIcyDataFromUrl
	Dim url As String = $"http://ice.pdeg.nl/getIcy.php?url=${streamUrl}"$
	Dim job As HttpJob
	
	job.Initialize("", Me)
	job.Download(url)
	job.GetRequest.Timeout = httpTimeOut
	
	Wait For (job) JobDone(job As HttpJob)
	If job.Success Then
		Dim icyData As String = job.GetString
		If icyData <> lastIcyData Then
			IcyDataHasChanged = True
		lastIcyData = icyData
		Else
			IcyDataHasChanged =False
		End If
	Else
		lastIcyData = ""
		End If
		
	job.Release
	Sleep(100)
	IcyDataChanged(icyData)
End Sub

Private Sub IcyDataChanged(icyData As String)
	If IcyDataHasChanged Then
		cmGenFunctions.logDebug(parseIcy(lastIcyData))
	End If
End Sub

Public Sub parseIcy(metaData As String) As String
	Dim icy_by, icy_name, icy_playing, icy_genre, icy_br, icy_url, icy_genre As String = ""
	Dim icy_maint As Int = 16000
	
	Dim parser As JSONParser
	parser.Initialize(metaData)

	Try
		Dim root As Map = parser.NextObject
	Catch
		cmGenFunctions.logDebug(LastException.Message)
		Return "-1"
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