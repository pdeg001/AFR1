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
	Private clsDb As afrDb
	Private clvCountryAbbrev As CustomListView
	Private lblHeader As Label
	Private lblCountryAbbrev As Label
	Private pnlAbbrev As Panel
	Private lblCountry As Label
	Private pnlSelectCountry As Panel
	Private swDefault As B4XSwitch
	Private clvCountries As CustomListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("selectCountry")
	clsDb.Initialize
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
	pnl.SetLayoutAnimated(0, 2dip, 0, 80dip, 45dip)
	pnl.LoadLayout("pnlCountryAbbrev")
	lblCountryAbbrev.Text = abbrev
	
	Return pnl
End Sub

Private Sub lblCountryAbbrev_Click
	Dim sndr As Label = Sender
	Dim lst As List
	Dim abbrev() As String = Regex.Split("-", sndr.Text)
	
	SelectedAbbrevBgColor(sndr)
	
	clvCountries.Clear
	lst = clsDb.GetCountriesByFirstLetter(abbrev)
	
	For Each v As String In lst
		clvCountries.Add(GenCountryList(v), "")
	Next
	
End Sub

Private Sub GenCountryList(countryName As String) As Panel
	Dim pnl As B4XView = xui.CreatePanel("")
	pnl.SetLayoutAnimated(0, 0, 0, clvCountries.AsView.Width, 65dip)
	pnl.LoadLayout("pnlCountry")
	lblCountry.Text = countryName
	If countryName = Starter.defaultCountry Then
		swDefault.Value = True
	End If
	swDefault.mHaptic = True
	Return pnl
	
End Sub

Private Sub SelectedAbbrevBgColor(lbl As Label)
	Dim pnl As Panel = lbl.Parent
	Dim pnl1 As Panel
	Dim pLbl As Label
	
	pLbl.Initialize("")
	For i = 0 To clvCountryAbbrev.Size -1
		pnl = clvCountryAbbrev.GetPanel(i)
		For Each vl As View In pnl.GetAllViewsRecursive
			If vl Is Label Then
				pLbl = vl
				pLbl.Color = Colors.Transparent
			End If
		Next
	Next
	
	lbl.Color = Colors.LightGray
End Sub


Private Sub swDefault_ValueChanged (Value As Boolean)
	Dim sw As B4XSwitch = Sender
	Dim v As View
	
	
	For i = 0 To sw.mBase.NumberOfViews -1
		v = sw.mBase.GetView(i)
		If v Is Label Then
			Dim lbl As Label = v
			Starter.SetDefaultCountry(lbl.Text)
		End If
	Next
End Sub

