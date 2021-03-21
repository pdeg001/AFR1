B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.7
@EndOfDesignText@
Sub Class_Globals
	Private bbTe As BCTextEngine
	Private bbLabel1 As BBScrollingLabel
	Private bbPnl As Panel
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(pnl As Panel)
	bbPnl = pnl
	bbTe.Initialize(bbPnl)
End Sub

Public Sub AddScrollLabel
	bbLabel1.Initialize(Me, "")
	
	
End Sub