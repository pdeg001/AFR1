B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
#IgnoreWarnings: 9
Sub Class_Globals
	Private icyTimer As Timer
	Private httpTimeOut As Int = 6*1000
	Private icyTimerTimeOut As Int = 4*1000
	Private lastIcyData As String
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
		Dim IcyData As String = job.GetString
		lastIcyData = IcyData
	Else
		lastIcyData = ""
		End If
		
	job.Release
	
	IcyDataChanged(IcyData)
End Sub

Private Sub IcyDataChanged(icyData As String)
	If icyData <> lastIcyData Then
		cmGenFunctions.logDebug(">>> " & icyData)
	End If
End Sub