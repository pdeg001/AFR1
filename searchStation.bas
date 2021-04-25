B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=10.6
@EndOfDesignText@
'TODO : Documenteer code
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Private xui As XUI
End Sub

Sub Globals
	Private clsI18n As i18nGetSetViews
	Private clsI18nValue As i18nGetSetVar
	Private clsPlayer As PlayStream
	Private clsDb As afrDb
	
	Private ime As IME

	Private lstStation As List
	Private lblStreamClick As Label
	
	Private pnlStreams, pnlStation, pnlGenreLanguage, pnlGenre, pnlStationStream As Panel
	Private ftSeach As B4XFloatTextField
	Private clvStation, clvGenre, clvLanguage As CustomListView
	Private TabSearch As TabStrip

	Private lblHeader, lblDescription, lblGenre, lblLanguage, lblStationName As Label
	Private lblStreamCount, lblStationFound, lblNo, lblPnlGenre, lblPnlLanguage As Label
	Private lblChoosenGenre, lblChoosenLanguage, lblRemoveGenre, lblRemoveLaguage As Label
	Private lblDefaultCountry,lblChoosenCountry, lblSearch As Label
	Private lblClickStationName As Label
	Private lblStream1, lblStream2, lblStream3 As Label
	Private lblKeepStream1, lblKeepStream2, lblKeepStream3 As Label
	Private pnlBbScroll As Panel
	Private lblPlaying As Label
	Private lblGenrePlaying As Label
	Private lblBitratePlaying As Label
	Private pnlFooter As Panel
	Private lblSelectCountry As Label
	Private edtDummyForFocus As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	clsI18n.Initialize
	clsI18nValue.Initialize
	clsDb.Initialize
	clsPlayer.Initialize
	lblStreamClick.Initialize("")
	
	Activity.LoadLayout("searchStation")
	lblStationFound.Visible = False
	pnlStreams.Top = Activity.Height+300dip
	InitTabView
	clsI18n.GetViewsSeti18N(Activity)
	TabI18n
	GetGenre
	GetLanguage
	lblChoosenCountry.Text = Starter.defaultCountry
	ftSeach.TextField.RequestFocus
	Starter.icycallingActivity = "searchStation"
	Starter.icyCallingActivityCallback = "SetNowPlaying"
	
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
	Return False
End Sub

Private Sub InitTabView
	TabSearch.LoadLayout("tabStations", cmGenFunctions.Geti18NFromString("i18n.stations"))
	TabSearch.LoadLayout("tabGenre", cmGenFunctions.Geti18NFromString("i18n.genre"))
	TabSearch.LoadLayout("tabLanguage", cmGenFunctions.Geti18NFromString("i18n.language"))
	For Each lbl As Label In GetAllTabLabels(TabSearch)
		lbl.Width=33%x
	Next
End Sub

Private Sub TabI18n
	lblRemoveGenre.Text = cmGenFunctions.Geti18NFromString(lblRemoveGenre.Text)
	lblRemoveLaguage.Text = cmGenFunctions.Geti18NFromString(lblRemoveLaguage.Text)
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
	
	ime.HideKeyboard
	
	ProgressDialogShow2(cmGenFunctions.Geti18NFromString("i18n.seaching_stations"), False)
	Sleep(10)
	lblStationFound.Visible = False
	clvStation.Clear
	lstStation = clsDb.GetStationByQuery(ftSeach.Text)
	
	If lstStation.Size = 0 Then
		ProgressDialogHide
		Msgbox2Async(cmGenFunctions.Geti18NFromString("i18n.nothing_found"), Application.LabelName, cmGenFunctions.Geti18NFromString("i18n.btn_ok"), "", "", Application.Icon, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Return
		End If
	End If
	
	
	Dim rowNo As Int = 1
	
	For Each station As stationList In lstStation
		clvStation.Add(CreateStationList(station, NumberFormat(rowNo, 2, 0)), "")
		rowNo = rowNo + 1
	Next
	clvStation.Refresh
	lblStationFound.Text = $"${cmGenFunctions.Geti18NFromString("i18n.found_count")} ${lstStation.Size}"$
	lblStationFound.SetVisibleAnimated(1000, True)
'	TabSearch.ScrollTo(0, False)
	clvStation.JumpToItem(0)
	ProgressDialogHide
	edtDummyForFocus.RequestFocus
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
	ShowStreamPanel(clickedStation, clvStation.GetItemFromView(pnl))
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
'	If cmGenFunctions.ExoPLayerIsPlaying Then 
'		StopStream
'		GetStreamPlay
'	End If
	
	clsPlayer.IsLabelKnown
	pnlStationStream.Tag = ""
	pnlStreams.SetLayoutAnimated(400, Activity.Width+400dip, 0dip, Activity.Width, Activity.Height)
	Sleep(400)
	pnlStreams.SetLayoutAnimated(0, 0dip, Activity.Height+300dip, Activity.Width, Activity.Height)
End Sub

Private Sub pnlStationStream_Click
	
End Sub

Private Sub PlayLabelEnabled(enabledState As Boolean, lbl As Label)
	lbl.Enabled=enabledState
	lbl.Enabled=enabledState
End Sub

Private Sub ShowStreamPanel(station As stationList, index As Int)
'	lblClickStationName.Text = cmGenFunctions.runMarquee(station.station_name, "MARQUEE")
	cmGenFunctions.runMarquee(lblClickStationName, station.station_name, "MARQUEE")
	
	'set play stream label to blue if there is a stream
	Dim countStream As Int = cmGenFunctions.GetPanelLabelValue("streamCount", index, clvStation)
	If countStream > 0 Then
		lblKeepStream1.TextColor = 0xFF008EFF
		lblStream1.TextColor = 0xFF008EFF
		lblStream1.Tag = station.station_url1
		PlayLabelEnabled(True,lblStream1)
		PlayLabelEnabled(True,lblKeepStream1)
	Else
		lblKeepStream1.TextColor = 0xFFB0B0B0
		lblStream1.TextColor = 0xFFB0B0B0
		PlayLabelEnabled(False,lblStream1)
		PlayLabelEnabled(False,lblKeepStream1)
	End If
	If countStream > 1 Then
		lblKeepStream2.TextColor = 0xFF008EFF
		lblStream2.TextColor = 0xFF008EFF
		lblStream2.Tag = station.station_url2
		PlayLabelEnabled(True,lblStream2)
		PlayLabelEnabled(True,lblKeepStream2)
	Else
		lblKeepStream2.TextColor = 0xFFB0B0B0
		lblStream2.TextColor = 0xFFB0B0B0
		PlayLabelEnabled(False,lblStream2)
		PlayLabelEnabled(False,lblKeepStream2)
	End If
	If countStream > 2 Then
		lblKeepStream3.TextColor = 0xFF008EFF
		lblStream3.TextColor = 0xFF008EFF
		lblStream3.Tag = station.station_url3
		PlayLabelEnabled(True,lblStream3)
		PlayLabelEnabled(True,lblKeepStream3)
	Else
		lblKeepStream3.TextColor = 0xFFB0B0B0
		lblStream3.TextColor = 0xFFB0B0B0
		PlayLabelEnabled(False,lblStream3)
		PlayLabelEnabled(False,lblKeepStream3)
	End If
	
	pnlStreams.SetLayoutAnimated(700, 0dip, 0dip, Activity.Width, Activity.Height)
	Sleep(500)
End Sub

Private Sub lblStream1_Click
	InitStream(Sender)
End Sub

Private Sub lblStream2_Click
	InitStream(Sender)
End Sub

Private Sub lblStream3_Click
	InitStream(Sender)
End Sub

Private Sub InitStream(lbl As Label)
	Dim currLabelTag As String = clsPlayer.IsLabelKnown
'	If clsPlayer.IsLabelKnown = True Then Return
'	clsPlayer.IsLabelKnown
	If lbl.Tag = currLabelTag Then Return
	clsPlayer.CreateLblPlayStation(lbl, "searchStation", "SetNowPlaying", "ResetCurrentPlayingLabels", lbl.Tag)
	GetStreamPlay
End Sub

Private Sub GetStreamPlay
	Try
		'if there is no stream url for the selected play label
		If clsPlayer.lblPlayStationPlaying.act = Null And clsPlayer.lblPlayStationPlaying.lbl.TextColor <> 0xFF008EFF Or Starter.playerStatus = "error" Then
			Starter.playerStatus = "not playing"
			lblStreamClick.Text = cmGenFunctions.Geti18NFromString("i18n.play_stream")
			If cmGenFunctions.ExoPLayerIsPlaying Then StopStream
			Return
		End If
	
		clsPlayer.StartLabelStream
		lblPlaying.Text = cmGenFunctions.Geti18NFromString("i18n.opening_selected_stream")
	Catch
		Log(LastException.Message)
		Return	
	End Try
End Sub

Public Sub GetNowPlaying As String
	Return lblPlaying.Text
End Sub

Public Sub SetNowPlaying(playing As String)
	If playing = Null Or playing = "null" Then
		cmGenFunctions.runMarquee(lblPlaying, clsI18nValue.GetI18nValueFromString("i18n.no_station_information"), "MARQUEE")
'		Return
	Else
		'cmGenFunctions.runMarquee(lblPlaying, playing, "MARQUEE")
	End If

	If Starter.playerStatus = "error" Then
		ErrorPlayingStream
		Return
	End If
	
	If Starter.clsIcyData.lstIcyData.icy_playing <> Null Then
		playing = Starter.clsIcyData.lstIcyData.icy_playing
	Else 
		playing	= clsI18nValue.GetI18nValueFromString("i18n.no_station_information")
	End If
	
	clsPlayer.lblPlayStationPlaying.lbl.Text= cmGenFunctions.Geti18NFromString("i18n.stop_stream")
	If lblPlaying.Text = playing Then Return
	
	cmGenFunctions.runMarquee(lblPlaying, playing, "MARQUEE")
	Try
		If Starter.clsIcyData.lstIcyData.icy_name = "" Or Starter.clsIcyData.lstIcyData.icy_name = Null Then
			lblGenrePlaying.Text = ""
			lblBitratePlaying.Text = ""
		Else
			lblGenrePlaying.Text = $"${clsI18nValue.GetI18nValueFromString("i18n.genre")}: ${Starter.clsIcyData.lstIcyData.icy_genre}"$
			lblBitratePlaying.Text = $"${clsI18nValue.GetI18nValueFromString("i18n.bitrate")}: ${Starter.clsIcyData.lstIcyData.icy_br}"$
		End If
	Catch
		Log(LastException.Message)
	End Try
	
End Sub

'called from clsPlayer, and clears labels
Private Sub ResetCurrentPlayingLabels 'ignore
	lblGenrePlaying.Text = ""
	lblBitratePlaying.Text = ""
	lblPlaying.Text = cmGenFunctions.Geti18NFromString("i18n.click_stream")
	clsPlayer.lblPlayStationPlaying.lbl.Text = cmGenFunctions.Geti18NFromString("i18n.play_stream")
End Sub


Private Sub StopStream
	Starter.clsIcyData.ResetIcyList
	Starter.clsIcyData.lstIcyData.icy_playing = cmGenFunctions.Geti18NFromString("i18n.click_stream")
	If cmGenFunctions.ExoPLayerIsPlaying Then
		clsPlayer.StopStream
	End If
End Sub

Private Sub lblKeepStream1_Click
End Sub

Public Sub ErrorPlayingStream
	GetStreamPlay
	StopStream
	Sleep(1000)
	SetNowPlaying(cmGenFunctions.Geti18NFromString("i18n.unable_to_play_stream"))
End Sub

Private Sub lblSelectCountry_Click
	Msgbox2Async(cmGenFunctions.Geti18NFromString("i18n.select_different_country"), Application.LabelName, cmGenFunctions.Geti18NFromString("i18n.btn_yes"), "", cmGenFunctions.Geti18NFromString("i18n.btn_no"), Application.Icon, False)
	Wait For Msgbox_Result (Result As Int)
	If Result = DialogResponse.NEGATIVE Then
		Return
	End If
	Activity.Finish
	StartActivity(selectCountry)
End Sub