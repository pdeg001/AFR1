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
End Sub

Sub Activity_Create(FirstTime As Boolean)
	clsI18n.Initialize
	clsDb.Initialize
	Activity.LoadLayout("searchStation")
	clsI18n.GetViewsSeti18N(Activity)
	lblStationFound.Visible = False
	TabSearch.LoadLayout("tabStations", "stations")
	TabSearch.LoadLayout("tabGenre", "Genre")
	TabSearch.LoadLayout("tabLanguage", "Language")
	For Each lbl As Label In GetAllTabLabels(TabSearch)
		lbl.Width=33%x
	Next
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub ftSeach_EnterPressed
	lblStationFound.Visible = False
	clvStation.Clear
	Dim lstStation As List = clsDb.GetStationByQuery(ftSeach.Text)
	Dim rowNo As Int = 1
	
	For Each station As stationList In lstStation
		clvStation.Add(CreateStationList(station, NumberFormat(rowNo, 2, 0)), "")
		rowNo = rowNo + 1
	Next
	clvStation.Refresh
	lblStationFound.Text = $"${clsI18n.GetI18nValueFromString("i18n.found_count")} ${lstStation.Size}"$
	lblStationFound.SetVisibleAnimated(1000, True)
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
	
End Sub