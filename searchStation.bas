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
	Private lstStation As List
	Private pnlStreamsPos As Int
	
	
	Private lblHeader As Label
	Private ftSeach As B4XFloatTextField
	Private lblDescription As Label
	Private lblGenre As Label
	Private lblLanguage As Label
	Private lblStationName As Label
	Private lblStreamCount As Label
	Private pnlStation As Panel
	Private clvStation As CustomListView
	Private lblStationFound As Label
	Private lblNo As Label
	Private TabSearch As TabStrip
	Private clvGenre As CustomListView
	Private clvLanguage As CustomListView
	Private pnlGenreLanguage As Panel
	Private pnlGenre As Panel
	Private lblPnlGenre As Label
	Private lblChoosenGenre As Label
	Private lblChoosenLanguage As Label
	Private lblRemoveGenre As Label
	Private lblPnlLanguage As Label
	Private lblRemoveLaguage As Label
	Private lblDefaultCountry As Label
	Private lblChoosenCountry As Label
	Private lblSearch As Label
	Private pnlStreams As Panel
	Private lblClickStationName As Label
	Private lblClickStationGenre As Label
	Private lblClickStationLanguage As Label
	Private lblStream1 As Label
	Private lblStream2 As Label
	Private lblStream3 As Label
	Private lblKeepStream1 As Label
	Private lblKeepStream2 As Label
	Private lblKeepStream3 As Label
	Private pnlStationStream As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	clsI18n.Initialize
	clsDb.Initialize
	Activity.LoadLayout("searchStation")
	lblStationFound.Visible = False
	pnlStreams.Top = Activity.Height+300dip
	pnlStreamsPos = pnlStreams.Top
	InitTabView
	clsI18n.GetViewsSeti18N(Activity)
	TabI18n
	GetGenre
	GetLanguage
	lblChoosenCountry.Text = Starter.defaultCountry
	ftSeach.TextField.RequestFocus
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If pnlStreams.Top < 10dip Then
			pnlStreams_Click
			Return True			
		End If
	End If
	Return True
End Sub

Private Sub InitTabView
	TabSearch.LoadLayout("tabStations", "stations")
	TabSearch.LoadLayout("tabGenre", "Genre")
	TabSearch.LoadLayout("tabLanguage", "Language")
	For Each lbl As Label In GetAllTabLabels(TabSearch)
		lbl.Width=33%x
	Next
End Sub

Private Sub TabI18n
	lblRemoveGenre.Text = clsI18n.GetI18nValueFromString(lblRemoveGenre.Text)
	lblRemoveLaguage.Text = clsI18n.GetI18nValueFromString(lblRemoveLaguage.Text)
End Sub

Private Sub GetGenre
	If clvGenre.Size <> 0 Then Return
	clvGenre.Clear
	For Each genre As genreList In Starter.lstGenre
		clvGenre.Add(GenGenreList(genre), "")	
	Next
	
End Sub

Private Sub GenGenreList(genre As genreList) As Panel
	Dim pnl As B4XView = xui.CreatePanel("")
	pnl.SetLayoutAnimated(0, 0, 0, clvStation.AsView.Width, 75dip)
	pnl.LoadLayout("pnlGenre")
	lblPnlGenre.Text = genre.genre
	
	Return pnl
End Sub

Private Sub GetLanguage
	If clvLanguage.Size <> 0 Then Return
	clvLanguage.Clear
	For Each language As lanugageList In Starter.lstLanguage
		clvLanguage.Add(GenLanguageList(language), "")
	Next
End Sub

Private Sub GenLanguageList(language As lanugageList) As Panel
	Dim pnl As B4XView = xui.CreatePanel("")
	pnl.SetLayoutAnimated(0, 0, 0, clvStation.AsView.Width, 75dip)
	pnl.LoadLayout("pnlLanguage")
	lblPnlLanguage.Text = language.language
	
	Return pnl
End Sub

Private Sub ftSeach_EnterPressed
	If ftSeach.Text = "" And clsDb.findGenre = "" And clsDb.findLanguage = "" Then
		Return
	End If
	
	ProgressDialogShow2(clsI18n.GetI18nValueFromString("i18n.seaching_stations"), False)
	Sleep(10)
	lblStationFound.Visible = False
	clvStation.Clear
	lstStation = clsDb.GetStationByQuery(ftSeach.Text)
	Dim rowNo As Int = 1
	
	For Each station As stationList In lstStation
		clvStation.Add(CreateStationList(station, NumberFormat(rowNo, 2, 0)), "")
		rowNo = rowNo + 1
	Next
	clvStation.Refresh
	lblStationFound.Text = $"${clsI18n.GetI18nValueFromString("i18n.found_count")} ${lstStation.Size}"$
	lblStationFound.SetVisibleAnimated(1000, True)
	TabSearch.ScrollTo(0, False)
	ProgressDialogHide
End Sub

Private Sub CreateStationList(station As stationList, rowNo As String) As Panel
	Dim pnl As B4XView = xui.CreatePanel("")
	pnl.SetLayoutAnimated(0, 0, 0, clvStation.AsView.Width, 120dip)
	pnl.LoadLayout("pnlStation")
	
	pnl.Tag = station.id
	lblStationName.Text = station.station_name
	lblGenre.Text = station.station_genre
	lblLanguage.Text = station.station_language
	lblDescription.Text = station.station_descr
	lblStreamCount.Text = station.streamCount
	lblNo.Text = rowNo
	
	Return pnl
End Sub

Private Sub pnlStation_Click
	Dim clickedStation As stationList
	Dim pnl As Panel = Sender
	Dim pnlParent As Panel = pnl.Parent
	Dim stationId As String = pnlParent.Tag
	
	For Each station As stationList In lstStation
		If station.id = stationId Then
			clickedStation = station
			Exit
		End If
	Next
	lblClickStationName.Text = station.station_name
	lblClickStationGenre.Text = station.station_genre
	lblClickStationLanguage.Text = station.station_language
	
	pnlStreams.SetLayoutAnimated(500, 0dip, 0dip, Activity.Width, Activity.Height)
End Sub

Private Sub TabSearch_PageSelected (Position As Int)
	
End Sub

Public Sub GetAllTabLabels (tabstrip As TabStrip) As List
	Dim jo As JavaObject = tabstrip
	Dim r As Reflector
	r.Target = jo.GetField("tabStrip")
	Dim tc As Panel = r.GetField("tabsContainer")
	Dim res As List
	res.Initialize
	For Each v As View In tc
		If v Is Label Then res.Add(v)
	Next
	Return res
   
End Sub

Private Sub pnlGenre_Click
	Dim pnl As Panel = Sender
	
	For Each v As View In pnl.GetAllViewsRecursive
		If v Is Label Then
			Dim lbl As Label = v
			lblChoosenGenre.Text = lbl.Text
			clsDb.findGenre = lbl.Text
			lblRemoveGenre.TextColor = 0xFFFF0000
		End If
	Next
	EnableSearch
End Sub

Private Sub lblRemoveGenre_Click
	lblChoosenGenre.Text = ""
	clsDb.findGenre = ""
	lblRemoveGenre.TextColor = lblGenre.TextColor
	EnableSearch
End Sub

Private Sub pnlLanguage_Click
	Dim pnl As Panel = Sender
	
	For Each v As View In pnl.GetAllViewsRecursive
		If v Is Label Then
			Dim lbl As Label = v
			lblChoosenLanguage.Text = lbl.Text
			clsDb.findLanguage = lbl.Text
			lblRemoveLaguage.TextColor = 0xFFFF0000
		End If
	Next
	EnableSearch
End Sub

Private Sub lblRemoveLaguage_Click
	lblChoosenLanguage.Text = ""
	clsDb.findLanguage = ""
	lblRemoveLaguage.TextColor = lblLanguage.TextColor
	EnableSearch
End Sub

Private Sub ftSeach_TextChanged (Old As String, New As String)
	EnableSearch
End Sub

Private Sub EnableSearch
	If ftSeach.Text <> "" Or clsDb.findGenre <> "" Or clsDb.findLanguage <> "" Then
		lblSearch.TextColor = 0xFF008EFF
	Else
		lblSearch.TextColor = lblGenre.TextColor	
	End If
End Sub

Private Sub lblSearch_Click
	If lblSearch.TextColor = 0xFF008EFF Then
		ftSeach_EnterPressed
	End If
	
End Sub

Private Sub pnlStreams_Click
	pnlStreams.SetLayoutAnimated(500, 0dip, Activity.Height+300dip, Activity.Width, Activity.Height)
End Sub

Private Sub pnlStationStream_Click
	
End Sub