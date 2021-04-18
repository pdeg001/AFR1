B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
	
End Sub

'Get url from m3u8
Public Sub GetUrlFromM3u(url As String) As ResumableSub
	Dim url As String = $"${url}"$
	Dim job As HttpJob
	
	url = "http://playerservices.streamtheworld.com/api/livestream-redirect/TLPSTR20AAC.m3u8"
	
	job.Initialize("", Me)
	job.Download(url)
	
	
	Wait For (job) JobDone(job As HttpJob)
	If job.Success Then
		File.WriteString(Starter.filesFolder, "m3u.pdeg", job.GetString)
	End If
	
	job.Release
	Return ReadM3u
End Sub

Private Sub ReadM3u As String
	If File.Exists(Starter.filesFolder, "m3u.pdeg")=False Then
		Return "UNK"
	End If
	
	Dim m3uReader As TextReader
	Dim m3uLine As String
	
	m3uReader.Initialize(File.OpenInput(Starter.filesFolder, "m3u.pdeg"))
	
	m3uLine=m3uReader.ReadLine
	Do While m3uLine <> Null
		Log(m3uLine)
		m3uLine=m3uReader.ReadLine
	Loop
	
	Return ""
End Sub