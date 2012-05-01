package pinktubes;

import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;
import org.zkoss.zhtml.Br;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import org.zkoss.zk.ui.util.Clients;

public class SearchTagButton extends Button {
    ArrayList<Button> selectedButtonList;
    Div searchResultsDiv;
    Connection sql;
    Window mainWindow;   

    String selectedStyle = "font-weight:bold;background-color:#FF0080;font-size:18pt;";
    String unSelectedStyle = "font-weight:bold;background-color:#FF69B4;font-size:14pt;";
    String hoverStyle = "font-weight:bold;background-color:#FC0FC0;font-size:14pt;";
    String selectedHoverStyle = "font-weight:bold;background-color:#000000;font-size:18pt;color:#FF0080;";
    DecimalFormat formatter = new DecimalFormat( "#,###" );

    SearchTagButton( SearchTag _searchTag, ArrayList<Button> _selectedButtonList, Div _searchResultsDiv, Connection _sql, Window _mainWindow ) {
        setLabel( _searchTag.getTag() );
        setOrient("vertical");
        setStyle( unSelectedStyle );
        setId( _searchTag.getTag() + "Button" );
        selectedButtonList = _selectedButtonList;
        searchResultsDiv = _searchResultsDiv;
        sql = _sql;
        mainWindow = _mainWindow;
    }

    public void onClick() {
        //check to see if myButton is in the selected list. if so, then remove it.                 
	for ( int k=0; k<selectedButtonList.size(); k++ ) {
	  if ( selectedButtonList.get(k) == this ) {
		setStyle( unSelectedStyle );
		selectedButtonList.remove( k );
                showSearchResults();
                return;
	  }
        }

	//check three cases based on size of the list.
	if ( selectedButtonList.size() == 0 ) {
	   setStyle( selectedStyle ); 
           selectedButtonList.add( this );
	}
	else if ( selectedButtonList.size() == 1 ) {
            Button firstButton = selectedButtonList.get( 0 );                   
	    selectedButtonList.set( 0, this );
            selectedButtonList.add( firstButton );
	    setStyle( selectedStyle );
	}
	else if ( selectedButtonList.size() == 2 ) {
	    selectedButtonList.get(1).setStyle( unSelectedStyle );
	    selectedButtonList.set( 1, selectedButtonList.get( 0 ) );
	    selectedButtonList.set( 0, this );
	    setStyle( selectedStyle );
	}
 
        showSearchResults();
    }

    public void onMouseOver () {
	if ( getStyle().equals( unSelectedStyle ) ) {
	   setStyle( hoverStyle );
	}
        if ( getStyle().equals( selectedStyle ) ) {
           setStyle( selectedHoverStyle );
        }
    }
 
    public void onMouseOut () {
        if ( getStyle().equals( hoverStyle ) ) {
	  setStyle( unSelectedStyle );
	}
        if ( getStyle().equals( selectedHoverStyle ) ) {
	    setStyle( selectedStyle );
        }
    }
    
    public void runSearchQuery( ArrayList<String> _queryStrings ) {
       String maxDate = "";
       Statement stmt = null;
       try {
         stmt = sql.createStatement();
       }
       catch ( SQLException e ) { e.printStackTrace(); }

       try {
	   ResultSet rs = stmt.executeQuery("SELECT MAX(record_date) AS maxDate FROM video_records WHERE query_string='" + _queryStrings.get(0) + "' OR query_string='" + _queryStrings.get(1) + "'");
          rs.next();
	  maxDate = rs.getString( 1 );         
        
	  rs = stmt.executeQuery("SELECT * FROM video_records WHERE (query_string='" + _queryStrings.get(0) + "' OR query_string='" + _queryStrings.get(1) + "') AND record_date='" + maxDate + "' AND NOT(videoURL LIKE '%tube8%') ORDER BY viewCount DESC LIMIT 40");
          while ( rs.next() ) {
	      searchResultsDiv.appendChild( new SearchPopupButton( rs.getString(2), rs.getString(3), mainWindow, rs.getString(4), rs.getString(5), formatter.format(rs.getInt(6)) ) );
           }
	   for ( int i=0; i<8; i++ ) {
	      searchResultsDiv.appendChild( new Br() );
           }
        }
        catch ( SQLException e ) { e.printStackTrace(); }
    }

    public void showSearchResults() {
        ArrayList<String> queryStrings = buildQueryStrings();

        Div centeringDiv = new Div();
        centeringDiv.setAlign("center");
        centeringDiv.setStyle("font-weight:bold;font-size:16pt;");
        Label queryStringLabel = new Label( queryStrings.get(0) + " search results" );
        queryStringLabel.setStyle("font-weight:bold;font-size:18pt;color:#FFC0CB;");
        centeringDiv.appendChild( queryStringLabel );
       
        searchResultsDiv.getChildren().clear(); 
	searchResultsDiv.appendChild( centeringDiv );
	searchResultsDiv.appendChild( new Br() );

        runSearchQuery( queryStrings );  
    }

    public ArrayList<String> buildQueryStrings() {
       String queryString = "";
       for ( Button selectedButton : selectedButtonList ) {
	   queryString += " " + selectedButton.getLabel();
       }
      
       queryString = queryString.trim();
       String reverseQueryString = "";
       String[] queryElements = queryString.split(" ");
      
       for ( int i=queryElements.length-1; i>=0; i-- ) {
           reverseQueryString += " " + queryElements[i];
       }
       reverseQueryString = reverseQueryString.trim();
      
       ArrayList<String> queryStrings = new ArrayList<String>();
       queryStrings.add( queryString );
       queryStrings.add( reverseQueryString );
 
       return queryStrings;
    }
}