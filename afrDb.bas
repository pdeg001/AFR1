B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=10.6
@EndOfDesignText@
Sub Class_Globals
	Private sql As SQL
	Private qry As String
	Private rs As ResultSet
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
	sql = Starter.rdoSql
	
End Sub

Public Sub IsDbInitialized
	If sql.IsInitialized = False Then
		Starter.InitRdoDb
	End If
End Sub

Public Sub GetParamValue(param As String) As String
	IsDbInitialized
	Dim result As String
	
	qry = $"SELECT param_value FROM params WHERE param_name = ?;"$
	
	rs = sql.ExecQuery2(qry, Array As String(param))
	
	Do While rs.NextRow
		result = rs.GetString("param_value")
	Loop

	If result = "" Then
		AddParam(param)
		Return "01"
	Else 
		Return result
	End If
	
	
End Sub

Private Sub AddParam(param_name As String)
	IsDbInitialized
	qry = "INSERT INTO params (param_name, param_value) VALUES (?, ?);"
	
	sql.ExecNonQuery2(qry, Array As String(param_name, 0))
End Sub

Public Sub GetCountriesByFirstLetter(cntLetter() As String) As List
	Dim lst As List
	lst.Initialize
	IsDbInitialized

	qry = $"SELECT DISTINCT(country) FROM rdolist 
	WHERE country LIKE ? OR country LIKE ?
	ORDER BY country"$
	rs = Starter.rdoSql.ExecQuery2(qry, Array As String($"${cntLetter(0)}%"$, $"${cntLetter(1)}%"$))
	
	Do While rs.NextRow
		lst.Add(rs.GetString("country"))
	Loop
	
	rs.Close
	Return lst
End Sub

Public Sub GetStationByQuery(find As String) As List
	IsDbInitialized
	Dim lstStation As List
	
	qry = $"SELECT
rdo_id
,stname
,genre
,description
,language
, stream1, stream2, stream3
,(case when stream1 = '-' then 0 else 1 end)+(case when stream2 = '-' then 0 else 1 end)+(case when stream3 = '-' then 0 else 1 end) as countstream
FROM rdolist
WHERE stname like ? AND country = ?
ORDER BY stname"$

	rs = sql.ExecQuery2(qry, Array As String($"%${find}%"$, Starter.defaultCountry))
	lstStation.Initialize
	
	Do While rs.NextRow
		lstStation.Add(CreatestationList(rs.GetString("rdo_id"), _
										 rs.GetString("stname"), _
										 rs.GetString("description"), _
										 rs.GetString("genre"), _
										 "", _
										 rs.GetString("language"), _
										 rs.GetString("stream1"), _
										 rs.GetString("stream2"), _
										 rs.GetString("stream3"), _
										 rs.GetString("countstream")))
	Loop
	
	rs.Close
	Return lstStation
End Sub

Public Sub CreatestationList (id As String, station_name As String, station_descr As String, station_genre As String, station_country As String, station_language As String, station_url1 As String, station_url2 As String, station_url3 As String, streamCount As String) As stationList
	Dim t1 As stationList
	t1.Initialize
	t1.id = id
	t1.station_name = station_name
	t1.station_descr = station_descr
	t1.station_genre = station_genre
	t1.station_country = station_country
	t1.station_language = station_language
	t1.station_url1 = station_url1
	t1.station_url2 = station_url2
	t1.station_url3 = station_url3
	t1.streamCount = streamCount
	Return t1
End Sub