B4A=true
Group=classes
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
Sub Class_Globals
	
End Sub


Public Sub Initialize
	
End Sub

'Check if there is a connection to the internet
public Sub CheckConnected As ResumableSub
	Dim p As Phone
	Wait For (p.ShellAsync("ping", Array As String("-c", "1", "8.8.8.8"))) Complete (Success As Boolean, ExitValue As Int, StdOut As String, StdErr As String)
	If StdErr = "" And StdOut.Contains("Destination Host Unreachable")=False Then
		Return True
	Else
		Return False
	End If
End Sub