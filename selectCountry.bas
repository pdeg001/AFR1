B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.6
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Private xui As XUI
End Sub

Sub Globals
	Private clsI18n As i18nGetSetViews

	Private clvCountryAbbrev As CustomListView
	Private lblHeader As Label
	Private lblCountryAbbrev As Label
	Private pnlAbbrev As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("selectCountry")
	clsI18n.Initialize
	clsI18n.GetViewsSeti18N(Activity)
	GenAbbrevCountries
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub GenAbbrevCountries
	clvCountryAbbrev.Clear
	clvCountryAbbrev.Add(GenAbbrev("A-B"), "A-B")
	clvCountryAbbrev.Add(GenAbbrev("C-D"), "C-D")
	clvCountryAbbrev.Add(GenAbbrev("E-F"), "E-F")
	clvCountryAbbrev.Add(GenAbbrev("G-H"), "G-H")
	clvCountryAbbrev.Add(GenAbbrev("I-J"), "I-J")
	clvCountryAbbrev.Add(GenAbbrev("K-L"), "K-L")
	clvCountryAbbrev.Add(GenAbbrev("M-N"), "M-N")
	clvCountryAbbrev.Add(GenAbbrev("O-P"), "O-P")
	clvCountryAbbrev.Add(GenAbbrev("Q-R"), "Q-R")
	clvCountryAbbrev.Add(GenAbbrev("S-T"), "S-T")
	clvCountryAbbrev.Add(GenAbbrev("U-V"), "U-V")
	clvCountryAbbrev.Add(GenAbbrev("W-X"), "W-X")
	clvCountryAbbrev.Add(GenAbbrev("Y-Z"), "Y-Z")
	
End Sub

Private Sub GenAbbrev(abbrev As String) As Panel
	Dim pnl As B4XView = xui.CreatePanel("")
	pnl.SetLayoutAnimated(2dip, 0, 0, 80dip, 40dip)
	pnl.LoadLayout("pnlCountryAbbrev")
	lblCountryAbbrev.Text = abbrev
	
	Return pnl
End Sub

Private Sub clvCountryAbbrev_ItemClick (Index As Int, Value As Object)
	ToastMessageShow($"GA NU DE LANDEN IN "${Value}" INLEZEN"$, False)
End Sub