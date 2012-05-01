package pinktubes;

import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;
import org.zkoss.zhtml.Br;
import org.zkoss.zk.ui.util.Clients;
import groovy.sql.Sql;
import java.text.DecimalFormat;

class HotPinkButton extends Button {

   def mainWindow;
   def hotPinkResultsDiv;
   def sql;
   def selectedHotPinkButton;
   def selectedStyle = "font-weight:bold;background-color:#FF0080;font-size:18pt;"
   def unSelectedStyle = "font-weight:bold;background-color:#FF69B4;font-size:14pt;"
   def hoverStyle = "font-weight:bold;background-color:#FC0FC0;font-size:14pt;"
   def selectedHoverStyle = "font-weight:bold;background-color:#000000;font-size:18pt;color:#FF0080;"
   def formatter = new DecimalFormat( "#,###" );

   HotPinkButton( String _label, _selectedHotPinkButton, Sql _sql, Div _hotPinkResultsDiv, Window _mainWindow ) {
    setLabel( _label )
    setStyle( unSelectedStyle )
    selectedHotPinkButton = _selectedHotPinkButton 
    sql = _sql
    hotPinkResultsDiv = _hotPinkResultsDiv
    mainWindow = _mainWindow
    if ( _label == "Today" ) {
       showSearchResults()
    }
  }

  def onClick() {   
    selectedHotPinkButton[0].setStyle( unSelectedStyle )  
    if ( selectedHotPinkButton[0] == this ) {
      selectedHotPinkButton[0] = new Button()
      return
    }
   
    showSearchResults()
  }

  def onMouseOver() {
     if ( getStyle() == unSelectedStyle ) {
	setStyle( hoverStyle )
     }
     if ( getStyle() == selectedStyle ) {
        setStyle( selectedHoverStyle )
     }
  }

  def onMouseOut() {
     if ( getStyle() == hoverStyle ) {
	 setStyle( unSelectedStyle )
     }
     if ( getStyle() == selectedHoverStyle ) {
	 setStyle( selectedStyle )
     }
  }

  def showSearchResults() {
     setStyle( selectedStyle )
     selectedHotPinkButton[0] = this

     def centeringDiv = new Div(align:"center", style:"font-weight:bold;font-size:16pt;")
     centeringDiv.append {
       label(value:"Most viewed pink videos: ${getLabel()}", style:"font-weight:bold;font-size:18pt;color:#FFC0CB;")
     }
     hotPinkResultsDiv.getChildren().clear()
     hotPinkResultsDiv.appendChild(centeringDiv)

     sql.execute("CALL hotPinkTubesProc( ${getLabel()} )")

     sql.eachRow("SELECT * FROM view_count_changes_ii ORDER BY viewCountChange DESC LIMIT 50") { record ->
       sql.eachRow("SELECT * FROM video_records WHERE videoURL='${record.videoURL}' AND NOT(videoURL LIKE '%tube8%') ORDER BY record_date DESC LIMIT 1") {
	 hotPinkResultsDiv.appendChild( new SearchPopupButton( record.videoURL, it.title, mainWindow, it.imgURL, it.duration, formatter.format(record.viewCountChange) ) )
       }
     }
     for ( def i in 1..8 ) {
        hotPinkResultsDiv.appendChild( new Br() )
     }
  }
}