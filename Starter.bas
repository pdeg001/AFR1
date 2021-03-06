﻿B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#IgnoreWarnings: 12
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	Public const PLAYERREADY As Int = 1
	Public const PLAYERERROR As Int = 2
	
	Private xui As XUI
	Private rp As RuntimePermissions
	Private clsI18nXls As i18nXlsToDb
	Private clsDb As afrDb
	Private lstCountry As List
	Private phWake As PhoneWakeState
	
	Public dbI18n, i18nXls, dbRdo As String
	Public clsIcyData As GetIcyData
	Public clsi18nVar As i18nGetSetVar
	Public xlsFileName As String = "station.xls"
	Public lstGenre, lstLanguage As List
	Public locale, country, defaultCountry As String
	Public i18nSql, rdoSql As SQL
	Public filesFolder As String
	Public doy As String ="pdegrootafr", moy As String ="hkWpXtB1!", ftp As String = "ftp.pdeg.nl"
	Public hasInternet, enableDebugMessages As Boolean
	Public playerStatus As String = "not playing"
	
	Public icyCallingActivity As String
	Public icyCallingActivityCallback As String

	Public playerCallingActivity As String
	Public playerCallingActivityCallback As String
	Public playerCallingActivityErrorCallback As String
End Sub

Sub Service_Create
	'enableDebugMessages = True
	InitAfr
End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StopAutomaticForeground 'Starter service can start in the foreground state in some edge cases.
End Sub

Sub Service_TaskRemoved
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

Private Sub InitAfr
	lstCountry.Initialize
	lstCountry.Add(Createi18n("EN"))
	lstCountry.Add(Createi18n("NL"))
	clsDb.Initialize
	clsIcyData.Initialize
	dbI18n = "i18n.db"
	i18nXls = "afr_i18n.xls"
	dbRdo = "rdodb.db"
	
	FindLocale2
	SetFilesFolder
	GetSetI18nFiles
	InitI18nSql
	InitRdoDb
	GetDefaultCountry
	clsi18nVar.Initialize
	clsI18nXls.Initialize
	clsI18nXls.GetXml
End Sub

Public Sub GetDefaultCountry
	If File.Exists(filesFolder, "defCountry.pdg") Then
		defaultCountry = File.ReadString(filesFolder, "defCountry.pdg")
	End If
End Sub

Public Sub SetDefaultCountry(strCountry As String)
	File.WriteString(filesFolder, "defCountry.pdg", strCountry)
	defaultCountry = strCountry
End Sub

Private Sub GetSetI18nFiles
'	If File.Exists(filesFolder, dbI18n) = False Then
		File.Copy(File.DirAssets, dbI18n, filesFolder, dbI18n)
'	End If
'	If File.Exists(filesFolder, i18nXls) = False Then
		File.Copy(File.DirAssets, i18nXls, filesFolder, i18nXls)
'	End If
	If File.Exists(filesFolder, dbRdo) = False Then
		File.Copy(File.DirAssets, dbRdo, filesFolder, dbRdo)
	End If
End Sub

Private Sub SetFilesFolder
	filesFolder = rp.GetSafeDirDefaultExternal("afr")
End Sub

Public Sub InitI18nSql
	If i18nSql.IsInitialized = False Then
		i18nSql.Initialize(filesFolder, dbI18n, False)
	End If
	
End Sub

Public Sub InitRdoDb
	If rdoSql.IsInitialized = False Then
		rdoSql.Initialize(filesFolder, dbRdo, False)
	End If
End Sub

Public Sub GetLocale
	FindLocale2
End Sub

Private Sub FindLocale2
	Dim jo As JavaObject
	jo = jo.InitializeStatic("java.util.Locale").RunMethod("getDefault", Null)
	locale = jo.RunMethod("getLanguage", Null)
	country = jo.RunMethod("getCountry", Null)
End Sub

Private Sub GetSeti18nXls
	clsI18nXls.GetXml
End Sub

Public Sub Createi18n (countryTl As String) As i18n
	Dim t1 As i18n
	t1.Initialize
	t1.countryTl = countryTl
	Return t1
End Sub

Public Sub closeApplication
	Dim jo As JavaObject
	jo.InitializeContext
	jo.RunMethod("finishAffinity", Null)
End Sub

Public Sub phKeepAlive
	phWake.PartialLock
End Sub

Public Sub phReleaseKeepAlive
	phWake.ReleasePartialLock
End Sub