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
	Private defaultCountryIndex As Int = 0
	Private defaultCountryNumber As Int = -1
	
	Private lblHeader As Label
	Private lblCountryAbbrev As Label
	Private pnlAbbrev As Panel
	Private lblCountry As Label
	Private pnlSelectCountry As Panel
	Private swDefault As B4XSwitch
	Private clvCountries As CustomListView
	Private ftDefaultCountry As B4XFloatTextField
	Private lblContinue As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("selectCountry")
	ftDefaultCountry.TextField.Enabled = False
	clsDb.Initialize
	clsI18n.Initialize
	clsI18n.GetViewsSeti18N(Activity)
	ShowSelectedCountry
	GenAbbrevCountries
	ShowSelectedCountryList
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Private Sub ShowSelectedCountry
	ftDefaultCountry.Text = Starter.defaultCountry
	If ftDefaultCountry.Text <> "" Then
		ftDefaultCountry.HintText = clsI18n.GetI18nValueFromString("i18n.selected_country")
	Else
		ftDefaultCountry.HintText = clsI18n.GetI18nValueFromString("i18n.select_country")
	End If
	ftDefaultCountry.Update
	SetLblTextButtonColor
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
	pnl.SetLayoutAnimated(0, 2dip, 0, 110dip, 55dip)
	pnl.LoadLayout("pnlCountryAbbrev")
	lblCountryAbbrev.Text = abbrev
	
	Return pnl
End Sub

Private Sub lblCountryAbbrev_Click
	Dim sndr As Label = Sender
	GetCountryList(sndr.Text, sndr.Parent)
End Sub

Private Sub GetCountryList(countryLetters As String, pnl As Panel)
	Dim lst As List
	Dim abbrev() As String = Regex.Split("-", countryLetters)
	
	defaultCountryIndex = -1
	defaultCountryNumber = 0
	
	clvCountries.Clear
	lst = clsDb.GetCountriesByFirstLetter(abbrev)
	
	For Each v As String In lst
		clvCountries.Add(GenCountryList(v), "")
		If defaultCountryIndex = -1 Then
			defaultCountryNumber = defaultCountryNumber + 1
		End If
	Next
	
	clvCountriesScrollTo
	SelectedAbbrevElevate(pnl)
End Sub

Private Sub clvCountriesScrollTo
	clvCountries.Refresh
	Sleep(100)
	If clvCountries.Size = 0 Then Return
	
	If defaultCountryIndex <> -1 And clvCountries.Size >= defaultCountryIndex Then
		clvCountries.ScrollToItem(defaultCountryIndex)
	Else
		clvCountries.ScrollToItem(0)
	End If
	
End Sub

Private Sub GenCountryList(countryName As String) As Panel
	Dim pnl As B4XView = xui.CreatePanel("")
	pnl.SetLayoutAnimated(0, 0, 0, clvCountries.AsView.Width, 70dip)
	pnl.LoadLayout("pnlCountry")
	
	lblCountry.Text = countryName
	
	If countryName = Starter.defaultCountry Then
		swDefault.Value = True
		defaultCountryIndex = defaultCountryNumber
	End If
	
	swDefault.mHaptic = True
	Return pnl
	
End Sub

Private Sub SelectedAbbrevElevate(pnl As Panel)
	Dim parentPnl, childPnl As Panel
	
	parentPnl.Initialize("")
	childPnl.Initialize("")
	For i = 0 To clvCountryAbbrev.Size -1
		parentPnl = clvCountryAbbrev.GetPanel(i)
		For Each v As View In parentPnl.GetAllViewsRecursive
			If v Is Panel Then
				childPnl = v
				childPnl.SetElevationAnimated(200, 1dip)
			End If
		Next
	Next
	
	pnl.SetElevationAnimated(200, 5dip)
End Sub

Private Sub swDefault_ValueChanged (Value As Boolean)
	Dim sw As B4XSwitch = Sender
	Dim index As Int = clvCountries.GetItemFromView(sw.mBase)
	
	
	'SET ALL SWITCHES OFF
	SwValuesToFalse
	If Value = False Then
		Starter.SetDefaultCountry("")
		ShowSelectedCountry
		Return
	End If
	sw.Value = Value
	Dim pnl As Panel = clvCountries.GetPanel(index)
	
	For Each v As View In pnl.GetAllViewsRecursive
		If v.Tag Is B4XSwitch Then Continue

		If v Is Label Then
			If v.Tag = "countryName" Then
				Dim lbl As Label = v
				Starter.SetDefaultCountry(lbl.Text)
				Exit
			End If
		End If
	Next
	ShowSelectedCountry
End Sub

Private Sub SwValuesToFalse
	Dim pnl As Panel
	Dim sw As B4XSwitch
	
	For i = 0 To clvCountries.Size -1
		pnl = clvCountries.GetPanel(i)
		For Each v As View In pnl.GetAllViewsRecursive
			If v.Tag Is B4XSwitch Then
				sw = v.Tag
				sw.Value = False
			End If
		Next
	Next
End Sub

Private Sub ShowSelectedCountryList
	If Starter.defaultCountry = "" Then Return
	
	Dim countryFirstLetter As String = Starter.defaultCountry.SubString2(0,1)
	Dim pnl As Panel
	Dim lbl As Label
	Dim panelFound As Boolean
	Dim i As Int
	Dim lblText As String
	pnl.Initialize("")
	lbl.Initialize("")
	
	'loop country abbrev clv, to get countryLetterIndex
	For i = 0 To clvCountryAbbrev.Size -1
		pnl = clvCountryAbbrev.GetPanel(i)
		For Each v As View In pnl.GetAllViewsRecursive
			If v Is Label Then
				lbl = v
				If lbl.Text.IndexOf(countryFirstLetter) <> -1 Then
					lblText = lbl.Text
					panelFound = True
					Exit
				End If
			End If
		Next
		If panelFound Then Exit
	Next

	If panelFound Then
		GetCountryList(lblText, pnl)
		clvCountryAbbrev.ScrollToItem(i)
		pnl.SetElevationAnimated(200, 5dip)
		clvCountryAbbrev.Refresh
	End If
End Sub

Private Sub SetLblTextButtonColor
	If Starter.defaultCountry <> "" Then
		lblContinue.TextColor = 0xFF008EFF
	Else
		lblContinue.TextColor = 0xFFBDBDBD
			
	End If
End Sub

Private Sub lblContinue_Click
	If ftDefaultCountry.Text = clsI18n.GetI18nValueFromString("i18n.continue") Then
		Return
	End If
	Log("PPP")
End Sub