B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Dim urlFound As Boolean
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
End Sub

Public Sub GetPlsStream(url As String) As ResumableSub
	Dim url As String = $"${url}"$
	Dim job As HttpJob
	Dim pls As String
	
	job.Initialize("", Me)
	job.Download(url)
	
	Wait For (job) JobDone(job As HttpJob)
	
	If job.Success Then
		pls = job.GetString
		job.Release
		Return GetUrlFromPls(pls)
	Else
		job.Release
		pls = "err"
	End If
	
End Sub

Private Sub GetUrlFromPls(pls As String) As String
	Dim txtReader As TextReader
	Dim line, foundUrl As String
	
	
	File.WriteString(Starter.filesFolder, "pls.rdo", pls)
	txtReader.Initialize(File.OpenInput(Starter.filesFolder, "pls.rdo"))
	
	line = txtReader.ReadLine
	
	Do While line <> Null Or urlFound = True
		foundUrl = CheckHttp(line)
		If urlFound Then 
			txtReader.Close
			Return foundUrl
		End If
		line = txtReader.ReadLine
	Loop
	
	Return ""
End Sub

Private Sub CheckHttp(line As String) As String
	If line.IndexOf("http") > -1 Then
		urlFound = True
		Return line.SubString2(line.IndexOf("http"), line.Length)
	End If
	Return ""
End Sub

